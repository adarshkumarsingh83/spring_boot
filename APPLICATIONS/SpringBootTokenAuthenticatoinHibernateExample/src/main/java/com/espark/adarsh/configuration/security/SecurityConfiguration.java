/*
 * Copyright (c) 2015 Espark And ©Adarsh Development Services @copyright All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Espark nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.espark.adarsh.configuration.security;

import com.espark.adarsh.web.controller.ApplicationController;
import com.espark.adarsh.web.controller.RestAuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ComponentScan("com.espark.adarsh")
class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity webSecurity)throws Exception{
        webSecurity.ignoring().antMatchers("/js/**")
                .antMatchers("/css/**")
                .antMatchers("/imaages/**")
                .antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http .csrf().disable()
                // never use server side sessions (stateless mode)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                 .antMatchers("/").permitAll()
                 .antMatchers("/js/**").permitAll()
                 .antMatchers("/css/**").permitAll()
                 .antMatchers("/images/**").permitAll()
                 .antMatchers("/favicon.ico").permitAll()
                 .antMatchers(RestAuthenticationServiceImpl.LOGIN_URL).permitAll()
                 .antMatchers(ApplicationController.WELCOME_URL).hasAnyAuthority(new String[]{"ROLE_SUPERADMIN", "ROLE_SUPERADMIN", "ROLE_USER"})
                 .antMatchers("/rest/**").authenticated()
                 .anyRequest().authenticated()
                 .and()
                .httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .requestCache().disable()
                .x509().disable()
                .logout().disable()
                //.anonymous().disable()
                // add custom authentication filter
                .addFilterBefore(this.getAuthenticationTokenProcessingFilter(), BasicAuthenticationFilter.class)
                        // register custom authentication exception handler
                .exceptionHandling().authenticationEntryPoint(this.getEntryPointBean())
                .accessDeniedHandler(this.getAccessDeniedHandler());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getRestApiAuthProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "authenticationProvider")
    public AuthenticationProvider getRestApiAuthProvider(){
        return new RestApiAuthProvider();
    }

    @Bean
    public AuthenticationTokenProcessingFilter getAuthenticationTokenProcessingFilter()throws Exception{
        return new AuthenticationTokenProcessingFilter(this.authenticationManager(),this.tokenService());
    }

    @Bean(name = "entryPoint")
    public ApiServerAuthenticationEntryPoint getEntryPointBean(){
        return new ApiServerAuthenticationEntryPoint();
    }

    @Bean(name = "accessDeniedHandler")
    public RestAccessDeniedHandler getAccessDeniedHandler(){
        return new RestAccessDeniedHandler();
    }

    @Bean(name="md5PasswordEncoder")
    public Md5PasswordEncoder getEncoder(){
        return new Md5PasswordEncoder();
    }

    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

}
