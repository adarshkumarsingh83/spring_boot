package com.espark.adarsh.service;

import org.springframework.stereotype.Service;

@Service(value = "infoService")
public class InfoServiceImpl implements InfoService {

    public String getMsg() {
		return "Hello "+System.getProperty("user.name");
	}

}
