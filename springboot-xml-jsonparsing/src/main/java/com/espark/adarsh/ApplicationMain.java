package com.espark.adarsh;

import com.jayway.jsonpath.JsonPath;
import com.jcabi.xml.XMLDocument;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import  net.minidev.json.JSONArray;
import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ApplicationMain.class, args);

        com.jcabi.xml.XML xml = new XMLDocument(new File("src/main/resources/employes.xml"));
        String xmlString = xml.toString();

        try {
            JSONObject contentJson = XML.toJSONObject(xmlString);
            String jsonString = contentJson.toString();
            JSONArray indiaEmployeeDir = JsonPath.parse(jsonString).read("$..india");
            for (int i = 0; i < indiaEmployeeDir.size(); i++) {
				System.out.println(indiaEmployeeDir.get(i));
            }

        } catch (JSONException e) {

        }
    }

}
