package com.espark.adarsh.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
 //@ImportResource({ "classpath:/configuration/applicationContext-security.xml" })
public class SecurityConfiguraton extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.ldapAuthentication().userSearchBase("ou=people")
                .userSearchFilter("(uid={0})").groupSearchBase("ou=groups")
                .groupSearchFilter("(member={0})")
                .contextSource()
                .root("dc=espark,dc=org")
                .ldif("classpath:/ldap/esparkLdapScript.ldif");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated();
        httpSecurity.formLogin().loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/");
    }

}
