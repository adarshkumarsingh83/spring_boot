package com.espark.adarsh.custom;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomApiConfig {

    @MyApiBean(name = "customApiBeanOne")
    public CustomApiBean customApiBeanOne() {
        return new CustomApiBean() {
            @Override
            public String getName() {
                return "Custom API Bean One";
            }
        };
    }

    @MyApiBean(name = "customApiBeanTwo")
    public CustomApiBean customApiBeanTwo() {
        return new CustomApiBean() {
            @Override
            public String getName() {
                return "Custom API Bean Two";
            }
        };
    }

    @MyApiBean(name = "customApiBeanThree")
    public CustomApiBean customApiBeanThree() {
        return new CustomApiBean() {
            @Override
            public String getName() {
                return "Custom API Bean Three";

            }
        };
    }


}
