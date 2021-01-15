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
package com.espark.adarsh.web.controller.rest;


import com.espark.adarsh.bean.ApiServerViewBean;
import com.espark.adarsh.web.security.TokenProvider;
import com.espark.adarsh.persistence.entites.impl.User;
import com.espark.adarsh.web.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */@Lazy(value = false)
@Scope(value = "singleton")
@RestController
public class RestAuthenticationServiceImpl
 implements RestAuthenticationService{

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationServiceImpl.class);

    public static final String LOGIN_URL="/rest/authenticate/login";

    @Autowired(required = true)
    private UserManager userManager;

    @Autowired(required = true)
    private TokenProvider tokenProvider;

    private final String USERNAME = "username";
    private final String PASSWORD = "password";


    // http://localhost:8080/
    @RequestMapping(value = LOGIN_URL, method = RequestMethod.POST)
    public
    @ResponseBody
    ApiServerViewBean authenticateUser(@RequestBody Map<String, String> map, HttpServletResponse httpServletResponse) {
        final ApiServerViewBean apiServer = new ApiServerViewBean();
        logger.debug("Inside-AuthenticationController ");

        final String loginSuccessMsg="LOGIN SUCCESSFUL";
        final String loginFailureMsg="LOGIN UNSUCCESSFUL";


        final String username = map.get(USERNAME);
        final String password = map.get(PASSWORD);

        try {
            final User user = userManager.getUserByName(new User(username));
            if(user!=null){
                if(!user.getUserPwd().equals(password)){
                    throw new BadCredentialsException("Invalid User Credentials ");
                }
               final String token = tokenProvider.getToken(user);
                httpServletResponse.setHeader("Authentication-token", token);
                apiServer.setToken(token);
                apiServer.setUserName(username);
                apiServer.setUserPassword(password);
                apiServer.setMessage(loginSuccessMsg);
                apiServer.setHttpStatus(HttpServletResponse.SC_OK);
            } else {
               throw new RuntimeException("User Not Found ");
            }
            return apiServer;

        } catch (Exception e) {
            logger.error("Exception Generated "+e.getMessage());
            apiServer.setMessage(loginFailureMsg + " - " + e.getMessage());
            apiServer.setHttpStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return apiServer;
        }
    }
}