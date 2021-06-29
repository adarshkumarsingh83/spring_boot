package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
@Slf4j
public class FileReaderService {

    @Value("${app.prefix}")
    String prefix;
    @Value("${app.suffix}")
    String suffix;
    @Value("${app.repo.url}")
    String repoUrl;

    private ResourceLoader resourceLoader;

    @Autowired
    public FileReaderService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        try {

            Resource resource = resourceLoader.getResource("classpath:url.txt");
            InputStream fileInputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(fileInputStream, "UTF-8"));
            String currentLine = null;
            do{
                currentLine = reader.readLine();
                if(currentLine!=null) {
                    String name = currentLine;
                    String prefix1 = prefix.replace("NAME", ""+name);
                    System.out.println();
                    System.out.print(prefix1);
                    System.out.print(repoUrl);
                    System.out.print(currentLine);
                    System.out.print(suffix);
                }
            }while(currentLine != null);
            System.out.println();
            System.out.println();
            reader.close();


        } catch (IOException | NullPointerException e) {
            log.error(" reader could not be initialized. ", e);
        }
    }
}
