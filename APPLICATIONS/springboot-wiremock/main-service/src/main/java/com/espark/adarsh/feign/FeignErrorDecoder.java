package com.espark.adarsh.feign;

import feign.Response;
import feign.codec.ErrorDecoder;

import static feign.FeignException.errorStatus;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            return new IntegrationException(
                    response.status(),
                    (response.reason()!=null && !response.reason().isEmpty()?response.reason()
                            :"Request Failed to External System Request Validation Failed "+response.status()+" for "+methodKey)
                    ,(response.reason()!=null && !response.reason().isEmpty()?response.reason()
                    :"Request Failed to External System Request Validation Failed "+response.status()+" for "+methodKey)
            );
        }

        if (response.status() >= 500 && response.status() <= 599) {
            return new IntegrationException(
                    response.status(),
                    (response.reason()!=null && !response.reason().isEmpty()?response.reason()
                            :"Request Failed to External System "+response.status()+" for "+methodKey)
                    ,(response.reason()!=null && !response.reason().isEmpty()?response.reason()
                    :"Request Failed to External System Request Validation Failed "+response.status()+" for "+methodKey)
            );
        }
        return errorStatus(methodKey, response);
    }
}