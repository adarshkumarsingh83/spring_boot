package com.espark.adarsh.service;

import com.espark.adarsh.dao.GenericDao;
import com.espark.adarsh.entity.Account;
import com.espark.adarsh.entity.Person;
import com.espark.adarsh.processor.annotation.DataAccess;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MethodServiceImpl implements MethodService {

    private GenericDao<Person> personGenericDao;
    private GenericDao<Account> accountGenericDao;

    @DataAccess(entity=Person.class)
    public void setPersonGenericDao( GenericDao<Person> personGenericDao){
        this.personGenericDao=personGenericDao;
    }

    @DataAccess(entity=Account.class)
    private void setAccountGenericDao(GenericDao<Account> accountGenericDao){
        this.accountGenericDao=accountGenericDao;
    }

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
