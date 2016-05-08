package com.espark.adarsh.test;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
public class TestCredential {

    public static void main(String[] args) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        System.out.println(encoder.encodePassword("adarsh",null));
        System.out.println(encoder.encodePassword("radha",null));
    }
}
