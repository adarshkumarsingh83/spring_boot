package com.espark.adarsh;

import com.espark.adarsh.bean.SearchBean;
import com.espark.adarsh.bean.SearchCriteria;
import com.espark.adarsh.entity.DbEntity;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.GenericRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationMainTests {

	@Autowired
	GenericRepositoryImpl genericRepository;

	@Test
	public void contextLoads() {
		List<Employee> employees= this.genericRepository.getAllEmployee();
		log.info(employees.toString());

	}

	//[Employee{id=1, name='adarsh', email='adarsh@kumar.singh', createdAt=2018-04-06 22:03:16.0, updatedAt=2018-04-06 22:06:19.0}
	// , Employee{id=2, name='radha', email='radha@singh', createdAt=2018-04-06 22:03:31.0, updatedAt=2018-04-06 22:03:31.0}]

	@Test
	public void searchEntity()throws Exception{
		List<SearchCriteria> andSearchCriteria = new ArrayList<SearchCriteria>();
		andSearchCriteria.add(new SearchCriteria("name", "::", "adarsh"));
		andSearchCriteria.add(new SearchCriteria("email", "::", "adarsh@kumar.singh"));
		SearchBean<Employee> searchBean=new SearchBean<>();
		searchBean.setClassType(Employee.class);
		searchBean.setAndSearchCriteria(andSearchCriteria);
		List<DbEntity> list= genericRepository.search(searchBean);
		log.info(list.toString());
	}

	@Test
	public void andSearchCriteria() throws Exception {
		List<SearchCriteria> andSearchCriteria = new ArrayList<SearchCriteria>();
		andSearchCriteria.add(new SearchCriteria("id", ">", "0"));
		andSearchCriteria.add(new SearchCriteria("id", "<", "2"));
		SearchBean<Employee> searchBean=new SearchBean<>();
		searchBean.setClassType(Employee.class);
		searchBean.setAndSearchCriteria(andSearchCriteria);
		List<DbEntity> list= genericRepository.search(searchBean);
		log.info(list.toString());
	}

	}
