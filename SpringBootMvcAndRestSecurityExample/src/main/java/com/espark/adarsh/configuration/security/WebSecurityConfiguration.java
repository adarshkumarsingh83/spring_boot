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


import com.espark.adarsh.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({"com.espark.adarsh.security.*"})
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private static final String baseResource = "/static/**";
    private static final String cssHome = "*.css";
    private static final String jsHome = "*.js";
    private static final String imageHome = "*.ico";

    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity webSecurity) throws Exception {
            webSecurity.ignoring()
                    .antMatchers(baseResource)
                    .antMatchers(jsHome)
                    .antMatchers(cssHome)
                    .antMatchers(imageHome)
                    .antMatchers("/rest/authenticate/login");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/rest/*")
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(baseResource).access("permitAll")
                    .antMatchers("/css/**").access("permitAll")
                    .antMatchers("/js/**").access("permitAll")
                    .antMatchers("/rest/authenticate/login").permitAll()
                    .antMatchers("/rest/welcome").hasAnyAuthority(new String[]{"ROLE_SUPERADMIN", "ROLE_ADMIN", "ROLE_USER"})
                    .antMatchers("/rest/**").authenticated()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().disable()
                    .formLogin().disable()
                    .rememberMe().disable()
                    .requestCache().disable()
                    .x509().disable()
                    .logout()
                    .logoutUrl("/rest/logout")
                    .logoutSuccessHandler(this.getRestLogoutSuccessHandler())
                    .and()
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
        public AuthenticationProvider getRestApiAuthProvider() {
            return new RestApiAuthProvider();
        }

        @Bean
        public AuthenticationTokenProcessingFilter getAuthenticationTokenProcessingFilter() throws Exception {
            return new AuthenticationTokenProcessingFilter(this.authenticationManager(), this.tokenService());
        }

        @Bean(name = "entryPoint")
        public ApiServerAuthenticationEntryPoint getEntryPointBean() {
            return new ApiServerAuthenticationEntryPoint();
        }

        @Bean(name = "accessDeniedHandler")
        public RestAccessDeniedHandler getAccessDeniedHandler() {
            return new RestAccessDeniedHandler();
        }

        @Bean(name = "restLogoutSuccessHandler")
        public RestLogoutSuccessHandler getRestLogoutSuccessHandler() {
            return new RestLogoutSuccessHandler();
        }

        @Bean(name = "md5PasswordEncoder")
        public Md5PasswordEncoder getEncoder() {
            return new Md5PasswordEncoder();
        }

        @Bean
        public TokenService tokenService() {
            return new TokenService();
        }

    }

    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired(required = true)
        @Qualifier(value = "userDetailsService")
        private UserDetailsService userDetailsService;

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers(baseResource)
                    .antMatchers(jsHome)
                    .antMatchers(cssHome)
                    .antMatchers(imageHome);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(baseResource).access("permitAll")
                    .antMatchers("/css/**").access("permitAll")
                    .antMatchers("/js/**").access("permitAll")
                    .antMatchers("/login**").access("permitAll")
                    .antMatchers("/logout**").access("permitAll")
                    .antMatchers("/user/**").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
                    .antMatchers("/sadmin/**").access("hasRole('ROLE_SUPERADMIN')")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/welcome")
                    .failureUrl("/login?error")
                    .usernameParameter("username").passwordParameter("password")
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .and()
                    .exceptionHandling().accessDeniedPage("/accessDenied");

        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            // with encryption
            //auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
            // without encryption
            auth.userDetailsService(userDetailsService);
        }
    }

}
