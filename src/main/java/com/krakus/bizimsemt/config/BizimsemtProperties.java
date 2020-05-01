package com.krakus.bizimsemt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BizimsemtProperties {

    private final Environment environment;

    @Autowired
    public BizimsemtProperties(Environment environment) {
        this.environment = environment;
    }

    public String getMongoUri() {
        return environment.getProperty("spring.data.mongodb.uri");
    }
}
