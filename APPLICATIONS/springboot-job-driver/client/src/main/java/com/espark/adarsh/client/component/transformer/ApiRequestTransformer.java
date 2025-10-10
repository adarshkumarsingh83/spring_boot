package com.espark.adarsh.client.component.transformer;

import com.espark.adarsh.client.bean.AbstractApiDetails;

public interface ApiRequestTransformer <T> {

    default void transformRequest(AbstractApiDetails apiDetails , T config){

    }
}
