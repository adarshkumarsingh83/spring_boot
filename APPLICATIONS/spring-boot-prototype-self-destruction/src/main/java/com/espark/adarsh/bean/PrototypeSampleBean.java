package com.espark.adarsh.bean;

import com.espark.adarsh.util.PrototypeMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

@Slf4j
public class PrototypeSampleBean implements PrototypeMarker, DisposableBean {

    private boolean protoType = true;

    public PrototypeSampleBean() {
        log.info("PrototypeSampleBean Object Created {}", this.hashCode());
    }

    @Override
    public void setProtoType(boolean protoTypeValue) {
        this.protoType = protoTypeValue;
    }

    @Override
    public boolean getProtoType() {
        return this.protoType;
    }

    @Override
    public void destroy() throws Exception {
        log.info("destroyed method called {}", this.hashCode());
    }
}
