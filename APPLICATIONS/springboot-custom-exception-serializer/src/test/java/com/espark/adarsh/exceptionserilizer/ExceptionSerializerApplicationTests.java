package com.espark.adarsh.exceptionserilizer;

import com.espark.adarsh.exceptionserilizer.util.ApplicationExceptionSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

@SpringBootTest
class ExceptionSerializerApplicationTests {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testExceptionExceptionSerializer() throws IOException {
		String response ="somethings went wrong";
		Exception e = new Exception(){
			@Override
			public String getMessage() {
				return "somethings went wrong";
			}
		};
		Writer jsonWriter = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
		SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
		new ApplicationExceptionSerializer.ExceptionSerializer().serialize(e,jsonGenerator,serializerProvider);
		jsonGenerator.flush();
		System.out.println(jsonWriter.toString());
		Assert.isTrue(jsonWriter.toString().contains(response),"Response is not Excepted");
	}

	@Test
	public void testExceptionExceptionDeSerializer() throws IOException {
		String response ="somethings went wrong";
		String exceptionJsonString ="{\"class\":\"java.lang.Exception\",\"message\":\"somethings went wrong\"}";
		Exception e = objectMapper.readValue(exceptionJsonString,Exception.class);
		System.out.println(e.toString());
		Assert.isTrue(e.getMessage().contains(response),"Response is not Excepted");
	}

}
