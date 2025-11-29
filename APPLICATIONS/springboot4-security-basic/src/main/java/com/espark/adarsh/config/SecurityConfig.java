package com.espark.adarsh.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;

import java.io.IOException;

@Configuration
public class SecurityConfig {


    @Bean
    PasswordEncoder passwordEncoder (){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){
      return new InMemoryUserDetailsManager(User.withUsername("root")
              .roles("USER")
              .password(passwordEncoder.encode("root"))
              .build()
       );
    };

    @Bean
    Customizer<HttpSecurity> httpSecurityCustomizer(){
        return http -> http
              //  .authorizeHttpRequests( a -> a.anyRequest().authenticated())
                .oneTimeTokenLogin(oneTimeTokenLogin -> oneTimeTokenLogin
                        .tokenGenerationSuccessHandler(new OneTimeTokenGenerationSuccessHandler() {
                            @Override
                            public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken token) throws IOException {
                                // Custom logic after successful token generation
                                response.getWriter().println("you have successfully logged in using One-Time Token.");
                                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                response.getWriter()
                                        .write("One-Time Token Generated: " + token.getTokenValue());

                                IO.println("please click on link http://localhost:8080/login/ott?token=" + token.getTokenValue());
                            }
                        })
                );
    }

   @Bean
    SecurityFilterChain securityWebFilterChain(HttpSecurity http) {
        return http.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests( a -> a.anyRequest().authenticated())
                .build();
    }
}
