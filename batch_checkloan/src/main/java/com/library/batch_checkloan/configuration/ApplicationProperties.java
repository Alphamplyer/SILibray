package com.library.batch_checkloan.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("library")
public class ApplicationProperties {

    // VARIABLES /////////////////////////////////////////////////

    private String serviceURL;
    private String customMessage;
    private String titleMessage;

    // GETTERS ///////////////////////////////////////////////////

    public String getServiceURL() {
        return serviceURL;
    }
    public String getCustomMessage() {
        return customMessage;
    }
    public String getTitleMessage() {
        return titleMessage;
    }

    // SETTERS ///////////////////////////////////////////////////

    public void setServiceURL(String service_url) {
        this.serviceURL = service_url;
    }
    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }
    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }
}
