package com.espark.adarsh

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class MessageServiceImpl implements MessageService {

    @Autowired
    ReaderService reader

    @Override
    def getMessage() {
        return this.reader.readJsonData();
    }


}
