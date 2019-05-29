package com.library.client.controller;

import com.library.client.configurations.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractController {

    protected ApplicationProperties properties;

    @Autowired
    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }
}
