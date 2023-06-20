/*
package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/schema.sql","/data.sql"})
@Slf4j
class EmployeeControllerMockTests {


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	EmployeeService employeeService;


	@Test
	void contextLoads() {
	}

	@Test
	void testGraphQLGetEmployeeById() throws Exception {
		when(employeeService.getEmployee(anyLong())).thenReturn(new Employee(1L,"adarsh","kumar","it",220L));
		String document = """
				{
				  "query": "query{getEmployee(id:1){  id  firstName  lastName  salary}}"
				}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
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

		when(employeeService.getAllEmployee()).thenReturn(Arrays.asList(
				new Employee(1L,"adarsh","kumar","it",220L),
				new Employee(2L,"radha","singh","it",300L))
		);
		String document = """
				{"query":"query{getAllEmployee{  id  firstName  lastName  salary}}"}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(notNullValue()))
				.andReturn();
		mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk());
		log.info(mvcResult.getResponse().getContentAsString());
	}

	@Test
	void testGraphQLSavingEmployee() throws Exception {
		Employee savedEmployee = new Employee(10L,"sonu","singh","it",3L);
		when(employeeService.saveEmployee(ArgumentMatchers.any())).thenReturn(savedEmployee);
		String document = """
				{"query":"mutation{saveEmployee(employeeBean:{ id:10,firstName:\\"sonu\\",lastName:\\"singh\\",career:\\"it\\", salary: 3}) {  id firstName lastName career salary}}"}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(notNullValue()))
				.andReturn();
		mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk());
		log.info(mvcResult.getResponse().getContentAsString());
	}


	@Test
	void testGraphQLUpdatingEmployee() throws Exception {
		Employee updateEmployee = new Employee(1L,"adarsh","kumar singh","it",3L);
		when(employeeService.updateEmployee(anyLong(),ArgumentMatchers.any())).thenReturn(updateEmployee);
		String document = """
				{"query":"mutation{updateEmployee(employeeBean:{ id:1,firstName:\\"adarsh\\",lastName:\\"kumar singh\\",career:\\"it\\", salary: 3}) {  id firstName lastName career salary}\\n}"}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(notNullValue()))
				.andReturn();
		mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk());
		log.info(mvcResult.getResponse().getContentAsString());
	}


	@Test
	void testGraphQLDeleteEmployee() throws Exception {
		Employee deleteEmployee = new Employee(1L,"adarsh","kumar singh","it",3L);
		when(employeeService.removeEmployee(anyLong())).thenReturn(deleteEmployee);
		String document = """
				{
					"query": "mutation{removeEmployee(id: 1){  id  firstName  lastName  career}\\n}"
				}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(notNullValue()))
				.andReturn();
		mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk());
		log.info(mvcResult.getResponse().getContentAsString());
	}


	@Test
	void testGraphQLFilterEmployee() throws Exception {

		when(employeeService.employeesFilter(ArgumentMatchers.any())).thenReturn(Arrays.asList(
				new Employee(1L,"adarsh","kumar","it",10L),
				new Employee(2L,"radha","singh","it",10L),
				new Employee(4L,"amit","kumar","it",8L))
		);
		String document = """
				{"query":"query {  employeesFilter(filter: { salary: { operator: \\"gt\\" value: \\"5\\" } }) { id firstName lastName salary } }"}
				            """;
		MvcResult mvcResult = mockMvc.perform(post("/graphql")
						.content(document)
						.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(notNullValue()))
				.andReturn();
		mockMvc.perform(asyncDispatch(mvcResult))
				.andDo(print())
				.andExpect(status().isOk());
		log.info(mvcResult.getResponse().getContentAsString());
	}

}
*/
