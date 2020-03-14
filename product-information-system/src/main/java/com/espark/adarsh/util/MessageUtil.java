package com.espark.adarsh.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Slf4j
@Service
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

    public String get(String code, Object[] args) {
        return accessor.getMessage(code, args);
    }

    public String get(String code, Object[] args, String defaultMsg) {
        return accessor.getMessage(code, args, defaultMsg);
    }
}
