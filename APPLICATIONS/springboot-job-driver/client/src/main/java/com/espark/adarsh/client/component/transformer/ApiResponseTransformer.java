package com.espark.adarsh.client.component.transformer;

import com.espark.adarsh.client.bean.AbstractApiDetails;

public interface ApiResponseTransformer <T> {

    default void transformResponse(AbstractApiDetails apiDetails , T config){

    }
}
