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
package com.espark.adarsh.controller;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.entity.User;
import com.espark.adarsh.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@RestController
@EnableAutoConfiguration
@ComponentScan("com.espark.adarsh.*")
@RequestMapping("/application")
public class ApplicationControllerImpl implements ApplicationController {

    @Value("${message}")
    private String message;

    @Qualifier(value = "dataBaseService")
    @Autowired(required = true)
    private DataBaseService dataBaseService;

    //url=>  http://localhost:8080/application/message
    @Override
    @RequestMapping(value = "/message"
            ,produces = {"text/html"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    String welcomeMessage() {
        return "<h1>" +
                "<br/><br/>" +
                "<center>" +
                "Hello " + System.getProperty("user.name") +" "+ message +
                "</center>" +
                "</h1>";
    }

    //url=>  http://localhost:8080/application/user/list
    @Override
    @RequestMapping(value = "/user/list"
            , produces = {"application/json", "application/xml"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getUserData() {
        return dataBaseService.getUserList();
    }

    //url=>  http://localhost:8080/application/user/save
    @Override
    @RequestMapping(value = "/user/save"
            , produces = {"application/json", "application/xml"}
            , consumes = {"application/json"}
            , method = RequestMethod.POST)
    public ResponseBean saveUser(@RequestBody User user) {
        final ResponseBean responseBean = new ResponseBean();
        if (dataBaseService.saveUser(user)) {
            responseBean.setMessage("User Saved ");
            responseBean.setHttpStatus("200");
        } else {
            responseBean.setMessage("User Not Saved ");
            responseBean.setHttpStatus("500");
        }
        return responseBean;
    }

}
