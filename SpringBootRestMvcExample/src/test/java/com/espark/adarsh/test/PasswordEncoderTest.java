package com.espark.adarsh.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Adarsh on 2/2/16.
 */
public class PasswordEncoderTest {
    public static void main(String[] args) {
        System.out.println("Encryption of Adarsh pwd "+new BCryptPasswordEncoder().encode("adarsh"));
        System.out.println("Encryption of Admin pwd "+new BCryptPasswordEncoder().encode("admin"));
        System.out.println("Encryption of User pwd "+new BCryptPasswordEncoder().encode("user"));
    }
}
