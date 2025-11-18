package com.espark.adarsh.web;

import com.espark.adarsh.annotation.EsparkQualifier1;
import com.espark.adarsh.annotation.EsparkQualifier2;
import com.espark.adarsh.service.EsparkService;
import com.espark.adarsh.util.ServiceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {


    EsparkService esparkService1;
    EsparkService esparkService2;
    ServiceUtil serviceUtil1;
    ServiceUtil serviceUtil2;


    public ApplicationController(@EsparkQualifier1 EsparkService esparkService1,
                                 @EsparkQualifier2 EsparkService esparkService2,
                                 @EsparkQualifier1 ServiceUtil serviceUtil1,
                                 @EsparkQualifier2 ServiceUtil serviceUtil2) {
        this.esparkService1 = esparkService1;
        this.esparkService2 = esparkService2;
        this.serviceUtil1 = serviceUtil1;
        this.serviceUtil2 = serviceUtil2;
    }

    @GetMapping("/service1")
    public String getService1() {
        return "Service1 :: " + esparkService1.getServiceName() +
                " :: ServiceUtil1 :: " + serviceUtil1.getServiceName();
    }

    @GetMapping("/service2")
    public String getService2() {
        return "Service2 :: " + esparkService2.getServiceName() +
                " :: ServiceUtil2 :: " + serviceUtil2.getServiceName();
    }
}
