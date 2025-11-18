package com.espark.adarsh.transformer;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@ToString
@Component
@Qualifier("requestTransformer")
public class RequestTransformer implements ApiTransformer {

    String name;

    public RequestTransformer() {
        this.name = "RequestTransformer";
    }

    @Override
    public String getName() {
        return this.name;
    }

}
