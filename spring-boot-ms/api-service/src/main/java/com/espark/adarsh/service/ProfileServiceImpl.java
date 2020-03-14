package com.espark.adarsh.service;

import com.espark.adarsh.bean.PersonProfile;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService{

    Logger logger = LoggerFactory.getLogger(getClass());

    private Set<PersonProfile> personProfiles = Sets.newConcurrentHashSet();

    @Override
    public void save(PersonProfile personProfile) {
        logger.info("Fallback save() in ProfileServiceImpl");
        personProfiles.add(personProfile);
    }

    @Override
    public Set<PersonProfile> getProfiles() {
        logger.info("Fallback getProfiles() in ProfileServiceImpl");
        return personProfiles;
    }
}
