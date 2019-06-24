package com.espark.adarsh.repository;

import com.espark.adarsh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

}
