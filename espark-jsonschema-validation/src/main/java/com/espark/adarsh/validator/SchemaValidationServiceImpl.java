package com.espark.adarsh.validator;


import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class SchemaValidationServiceImpl implements SchemaValidationService {

    private FileInputStream fileInputStream=null;

    @PostConstruct
    public void init()throws IOException{
        File schemaFile = ResourceUtils.getFile("classpath:request.schema");
        this.fileInputStream=new FileInputStream(schemaFile);
    }

    @Override
    public void validateRequest(String requestData) throws IOException {
        JSONObject jsonSchema = new JSONObject(new JSONTokener(fileInputStream));
        JSONObject jsonRequest = new JSONObject( new JSONTokener(requestData));
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonRequest);
    }
}
