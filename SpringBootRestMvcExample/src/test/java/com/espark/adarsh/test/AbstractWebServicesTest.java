package com.espark.adarsh.test;

import com.espark.adarsh.Application;
import com.espark.adarsh.bean.ApiServerViewBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AbstractWebServicesTest {

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractWebServicesTest.class);

    private static final String X_AUTH_USERNAME = "username";
    private static final String X_AUTH_PASSWORD = "password";
    private static final String X_AUTH_TOKEN = "Authentication-token";

    @Value("${local.server.port}")
    int port;
    protected MockMvc mockMvc;
    protected static final  String USERNAME = "adarsh";
    protected static final  String PASSWORD = "adarsh";

    @Autowired(required = true)
    protected WebApplicationContext wac;

    @Autowired(required = true)
    protected FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() throws JsonProcessingException {
        this.mockMvc = webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }


    protected static String getJsonString(Object object) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    protected static ApiServerViewBean jsonToObject(String jsonString) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, new TypeReference<ApiServerViewBean>() { });
    }

    protected boolean compareJson(String actualJsonString, String filePath) throws JsonProcessingException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualTree = mapper.readTree(actualJsonString);

        Resource expectedJsonFileResource = new ClassPathResource(filePath);
        JsonNode expectedTree = mapper.readTree(expectedJsonFileResource.getFile());
        return actualTree.equals(expectedTree);
    }

    protected String generateTokenWithAuthenticatedUser() throws Exception {
        final String url="/rest/authenticate/login";
          return this.generateTokenWithAuthenticatedUser(url,USERNAME,PASSWORD);
      }

    protected String generateTokenWithAuthenticatedUser(final  String url,final String USERNAME,final String PASSWORD) throws Exception {
        final Map<String,String> map = new HashMap<String,String>();
        map.put("username", USERNAME);
        map.put("password", PASSWORD);
        final ResultActions authResultActions = this.mockMvc.perform(post(
                url).content(getJsonString(map))
                .contentType(MediaType.APPLICATION_JSON));
        final String jsonString=authResultActions.andReturn().getResponse().getContentAsString();
        LOG.debug("controller returns : {}", jsonString);
        final ApiServerViewBean apiServerViewBean =  jsonToObject(jsonString);
        return apiServerViewBean.getToken().trim();
    }


}
