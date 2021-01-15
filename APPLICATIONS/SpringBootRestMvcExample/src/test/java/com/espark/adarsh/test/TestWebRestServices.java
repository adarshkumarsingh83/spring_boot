package com.espark.adarsh.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestWebRestServices extends AbstractWebServicesTest {

    @Before
    public void init() throws JsonProcessingException {
        LOG.info("init TestWebRestServices");
    }

    @Test
    public void testLoadApplication() throws Exception {
        final String token = this.generateTokenWithAuthenticatedUser();
        LOG.info(token);
        Assert.notNull(token, "Token is Null");
    }

    @Test
    public void testLoginForUser() throws Exception {
        final String jsonString=super.generateTokenWithAuthenticatedUser();
        LOG.debug("controller returns : {}", jsonString);
        Assert.notNull(jsonString, "Resulted Json is Null");
    }


    @Test
    public void testGetWelcomeMsgForUser() throws Exception {
        final String token = super.generateTokenWithAuthenticatedUser();
        final String url = "/rest/welcome";
        final ResultActions apiResultActions = this.mockMvc.perform(get(url)
                .header("Authentication-token", token)
                .contentType(MediaType.APPLICATION_JSON));
        final String jsonString = apiResultActions.andReturn().getResponse().getContentAsString();
        LOG.debug("controller returns : {}", jsonString);
        Assert.notNull(jsonString, "Resulted Json is Null");
    }

    @After
    public void destroy() {
        LOG.info("destroy TestWebRestServices");
    }
}
