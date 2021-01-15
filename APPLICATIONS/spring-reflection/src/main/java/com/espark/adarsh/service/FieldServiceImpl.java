package com.espark.adarsh.service;

import com.espark.adarsh.entity.Account;
import com.espark.adarsh.processor.annotation.DataAccess;
import com.espark.adarsh.dao.GenericDao;
import com.espark.adarsh.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService {

    @DataAccess(entity=Person.class)
    private GenericDao<Person> personGenericDao;

    @DataAccess(entity=Account.class)
    private GenericDao<Account> accountGenericDao;

    @Override
    public Optional<Person> createPerson(Person person) {
        return this.personGenericDao.persist(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return this.personGenericDao.findAll();
    }

    @Override
    public Optional<Account> createAccount(Account account) {
        return this.accountGenericDao.persist(account);
    }

    @Override
    public List<Account> getAllAccount() {
        return this.accountGenericDao.findAll();
    }
}
