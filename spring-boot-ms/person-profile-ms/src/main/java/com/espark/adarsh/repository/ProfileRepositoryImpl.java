package com.espark.adarsh.repository;

import com.espark.adarsh.bean.Address;
import com.espark.adarsh.bean.Person;
import com.espark.adarsh.bean.PersonProfile;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    Logger logger = LoggerFactory.getLogger(getClass());
    private Set<PersonProfile> personProfiles = Sets.newConcurrentHashSet();

    @PostConstruct
    public void init(){
        Address address=new Address("frisco","tx","usa");
        Person person = new Person("adarsh","adarsh@kumar","99999");
        PersonProfile personProfile=new PersonProfile("100",person,address);
        personProfiles.add(personProfile);
    }

    @Override
    public void save(PersonProfile personProfile) {
        logger.info("Actual save() in ProfileRepositoryImpl");
        this.personProfiles.add(personProfile);
    }

    @Override
    public Set<PersonProfile> getProfiles(){
        logger.info("Actual getProfiles() in ProfileRepositoryImpl");
        return this.personProfiles;
    }
}
