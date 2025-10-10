package com.espark.adarsh.client.component.resolver;

import com.espark.adarsh.client.bean.AbstractApiDetails;

public interface ConfigArgumentsResolver<T> {

    default void resolvePathParam(AbstractApiDetails apiDetails, T config){
    }

    default void resolveQueryParam(AbstractApiDetails apiDetails, T config){
    }

    default void resolveRequestParam(AbstractApiDetails apiDetails, T config){
    }
}
