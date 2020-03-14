package com.espark.adarsh.repository;

import com.espark.adarsh.bean.PersonProfile;

import java.util.Set;

public interface ProfileRepository {

    void save(PersonProfile personProfile);

    Set<PersonProfile> getProfiles();
}
