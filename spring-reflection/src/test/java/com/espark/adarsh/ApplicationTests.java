package com.espark.adarsh;

import com.espark.adarsh.processor.annotation.DataAccess;
import com.espark.adarsh.dao.GenericDao;
import com.espark.adarsh.entity.Account;
import com.espark.adarsh.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@DataAccess(entity=Person.class)
	private GenericDao<Person> personGenericDao;

	@DataAccess(entity=Account.class)
	private GenericDao<Account> accountGenericDao;

	private GenericDao<Person> personGenericDaoMethod;
	private GenericDao<Account> accountGenericDaoMethod;

	@DataAccess(entity=Person.class)
	public void setPersonGenericDaoMethod(GenericDao<Person> personGenericDaoMethod){
		this.personGenericDaoMethod = personGenericDaoMethod;
	}

	@DataAccess(entity=Account.class)
	private void setAccountGenericDaoMethod(GenericDao<Account> accountGenericDaoMethod){
		this.accountGenericDaoMethod = accountGenericDaoMethod;
	}


	@Test
	public void contextLoads() {
		System.out.println("welcome to the appliation ");
	}

}
