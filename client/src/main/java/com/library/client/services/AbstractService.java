package com.library.client.services;

import com.library.client.configurations.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AbstractService {

    protected ApplicationProperties properties;
    protected RestTemplate restTemplate;

    @Autowired
    public void setProperties(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
