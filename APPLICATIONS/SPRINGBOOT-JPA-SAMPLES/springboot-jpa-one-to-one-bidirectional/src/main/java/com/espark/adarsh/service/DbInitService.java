package com.espark.adarsh.service;

import com.espark.adarsh.entities.Gender;
import com.espark.adarsh.entities.User;
import com.espark.adarsh.entities.UserProfile;
import com.espark.adarsh.respository.UserProfileRepository;
import com.espark.adarsh.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Calendar;

@Service
public class DbInitService {


    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @PostConstruct
    public void init() {
        User adarsh = new User("Adarsh"
                , "Singh"
                , "adarsh@espark"
                , "@123@123");

        Calendar adarshDob = Calendar.getInstance();
        adarshDob.set(2020, 1, 1);
        UserProfile adarshProfile = new UserProfile("+1-1122334455"
                , Gender.MALE
                , adarshDob.getTime()
                , "111"
                , "Indian Street"
                , "Indian Point"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");

        adarsh.setUserProfile(adarshProfile);
        adarshProfile.setUser(adarsh);
        userRepository.save(adarsh);


        User radha = new User("Radha"
                , "Singh"
                , "radha@espak"
                , "@123@123");

        Calendar radhaDob = Calendar.getInstance();
        radhaDob.set(2020, 1, 1);
        UserProfile radhaProfile = new UserProfile("+1-55443322"
                , Gender.MALE
                , radhaDob.getTime()
                , "111"
                , "Indian Street"
                , "Indian Point"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");

        radha.setUserProfile(radhaProfile);
        radhaProfile.setUser(radha);
        userRepository.save(radha);


       /* Resource initSchema = new ClassPathResource("db.sql_bkp");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);*/
    }

}
