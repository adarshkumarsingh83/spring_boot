package com.espark.adarsh.transformer;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@ToString
@Component
@Qualifier("responseTransformer")
public class ResponseTransformer implements ApiTransformer {

    String name;

    public ResponseTransformer() {
        this.name = "ResponseTransformer";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
