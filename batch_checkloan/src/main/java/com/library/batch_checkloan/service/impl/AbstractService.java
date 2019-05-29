package com.library.batch_checkloan.service.impl;

import com.library.batch_checkloan.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractService {

    protected ApplicationProperties properties;

    @Autowired
    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }
}
