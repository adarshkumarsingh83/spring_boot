package com.espark.adarsh.util;

import com.espark.adarsh.annotation.EsparkQualifier1;
import org.springframework.stereotype.Service;

@Service
@EsparkQualifier1
public class Service1UtilImpl implements ServiceUtil {

    public String getServiceName() {
        return "Espark Service1 Util Supplier Implementation";
    }

    ;
}
