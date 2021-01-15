package com.espark.adarsh

import org.springframework.stereotype.Service

@Service
class DataMaskingService {

    def dataMaskingProcessor(def inputJSON ) {
        inputJSON?.each{
            it?.contact.mobile='*****'
            it?.address.apt='*****'
        }
        return inputJSON;
    }
}
