package com.espark.adarsh.util;

import com.espark.adarsh.annotation.EsparkQualifier2;
import org.springframework.stereotype.Service;

@Service
@EsparkQualifier2
public class Service2UtilImpl implements ServiceUtil {

    @Override
    public String getServiceName(){
        return  "Espark Service2 Util Supplier Implementation";
    };
}
