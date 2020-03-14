package com.espark.adarsh.service;

import com.espark.adarsh.bean.PersonProfile;

import java.util.Set;

public interface ProfileService {

    void save(PersonProfile personProfile);

    Set<PersonProfile> getProfiles();
}
