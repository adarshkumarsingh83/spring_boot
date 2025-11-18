package com.espark.adarsh.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfigs {

    @Bean(name = "apiBeanOne")
    public ApiBean apiBeanOne() {
        return new ApiBean() {;
            @Override
            public String getName() {
                return "API Bean One";
            }
        };
    }

    @Bean(name = "apiBeanTwo")
    public ApiBean apiBeanTwo() {
        return new ApiBean() {
            @Override
            public String getName() {
                return "API Bean Two";
            }
        };
    }

    @Bean(name = "apiBeanThree")
    public ApiBean apiBeanThree() {
        return new ApiBean() {
            @Override
            public String getName() {
                return "API Bean Three";

            }

        };


    }
}
