package com.espark.adarsh.feign;


import java.util.LinkedList;
import java.util.List;

class IntegrationException extends Exception {
	int code;
	List<Error> details;



    IntegrationException(int code, String message, String detail) {
		super(message);
		this.code = code;
		if(this.details==null){
			details=new LinkedList<>();
			details.add(new Error(detail,code,message));
		}else{
			details.add(new Error(detail,code,message));
		}
	}


	static class Error {
		String name;
		int code;
		String message;

		public Error(String name, int code, String message) {
			this.name = name;
			this.code = code;
			this.message = message;
		}
	}
}
