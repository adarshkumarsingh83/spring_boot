package com.espark.adarsh;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by adarsh_k on 4/6/2017.
 */
interface UserBeanRepository extends JpaRepository<UserBean,Long> {

    Optional<UserBean> findByUsername(String username);
}
