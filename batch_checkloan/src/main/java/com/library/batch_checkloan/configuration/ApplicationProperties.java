package com.library.batch_checkloan.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("library")
public class ApplicationProperties {

    // VARIABLES /////////////////////////////////////////////////

    private String serviceURL;

    // GETTERS ///////////////////////////////////////////////////

    public String getServiceURL() {
        return serviceURL;
    }

    // SETTERS ///////////////////////////////////////////////////

    public void setServiceURL(String service_url) {
        this.serviceURL = service_url;
    }
}
