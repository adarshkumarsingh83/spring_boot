package com.espark.adarsh.service;

import com.espark.adarsh.entity.Account;
import com.espark.adarsh.entity.Person;

import java.util.List;
import java.util.Optional;

public interface MethodService {

    Optional<Person>  createPerson(Person person);

    List<Person> getAllPerson();

    Optional<Account> createAccount(Account account);

    List<Account> getAllAccount();


}
