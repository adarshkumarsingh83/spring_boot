package com.espark.adarsh.service;

import com.espark.adarsh.bean.PersonProfile;
import com.espark.adarsh.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {


    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void save(PersonProfile personProfile) {
        this.profileRepository.save(personProfile);
    }

    @Override
    public Set<PersonProfile> getProfiles(){
        return this.profileRepository.getProfiles();
    }
}
