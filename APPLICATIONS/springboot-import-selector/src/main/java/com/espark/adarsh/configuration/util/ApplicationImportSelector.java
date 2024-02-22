package com.espark.adarsh.configuration.util;

import com.espark.adarsh.configuration.LocalConfiguration;
import com.espark.adarsh.configuration.RemoteConfiguration;
import com.espark.adarsh.configuration.annotation.EnableConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class ApplicationImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(
                        EnableConfiguration.class.getName(), false));
        if ("local".equals(attributes.get("value"))) {
            return new String[]{LocalConfiguration.class.getName()};
        } else {
            return new String[]{RemoteConfiguration.class.getName()};
        }
    }
}
