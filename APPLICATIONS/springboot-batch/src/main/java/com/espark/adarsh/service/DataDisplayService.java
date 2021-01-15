package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataDisplayService {

    public void showData() {
        log.info("data is safe in the db");
    }
}
