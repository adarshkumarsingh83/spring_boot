package com.espark.adarsh.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoteApplicationService {

    public Map<String,String> wish(String name , String type) throws InterruptedException {
       if(type.equals("DELAY")){
           Thread.currentThread().sleep(1000);
           return new HashMap<String,String>(){
               {
                   put("msg","welcome to espark ");
                   put("name",name);
               }
           };
       }else{
           return new HashMap<String,String>(){
               {
                put("msg","welcome to espark ");
                put("name",name);
               }
           };
       }
    }
}
