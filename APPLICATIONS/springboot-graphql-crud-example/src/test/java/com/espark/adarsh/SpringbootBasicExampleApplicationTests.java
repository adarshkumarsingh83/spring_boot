package com.espark.adarsh;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/data.sql"})
@Slf4j
class SpringbootBasicExampleApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testGraphQLGetEmployeeById() throws Exception {
		String document = """
				{
				  "query": "query{getEmployee(id:1){  id  firstName  lastName  salary}}"
				}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(notNullValue()))
				.andReturn();
		mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk());
		log.info(mvcResult.getResponse().getContentAsString());
	}

	@Test
	void testGraphQLGetAllEmployee() throws Exception {
		String document = """
				{"query":"query{getAllEmployee{  id  firstName  lastName  salary}}"}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(notNullValue()))
				.andReturn();
		mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk());
		log.info(mvcResult.getResponse().getContentAsString());
	}

}
