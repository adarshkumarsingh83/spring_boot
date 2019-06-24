package com.espark.adarsh.processor;

import com.espark.adarsh.annotation.EnableEspark;
import org.springframework.cloud.commons.util.SpringFactoryImportSelector;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnableEsparkServiceSelector extends SpringFactoryImportSelector<EnableEspark> {

    @Override
    protected boolean isEnabled() {
        return super.getEnvironment().getProperty("espark.enabled", Boolean.class, Boolean.TRUE);
    }
}
