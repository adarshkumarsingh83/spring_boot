package com.espark.adarsh.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
public class PasswordGenerator {
      public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("radha@adarsh"));
        System.out.println(new BCryptPasswordEncoder().encode("adarsh@radha"));
    }
}
