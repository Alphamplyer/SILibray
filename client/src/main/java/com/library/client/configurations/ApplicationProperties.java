package com.library.client.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("library")
public class ApplicationProperties {

    // VARIABLES /////////////////////////////////////////////////

    private int defaultReservationWeeks;
    private int extendReservationWeeks;
    private int numberElementByPage;
    private String serviceURL;

    // GETTERS ///////////////////////////////////////////////////

    public int getDefaultReservationWeeks() {
        return defaultReservationWeeks;
    }

    public int getExtendReservationWeeks() {
        return extendReservationWeeks;
    }

    public int getNumberElementByPage() {
        return numberElementByPage;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    // SETTERS ///////////////////////////////////////////////////

    public void setDefaultReservationWeeks(int defaultReservationWeeks) {
        this.defaultReservationWeeks = defaultReservationWeeks;
    }

    public void setExtendReservationWeeks(int extendReservationWeeks) {
        this.extendReservationWeeks = extendReservationWeeks;
    }

    public void setNumberElementByPage(int numberElementByPage) {
        this.numberElementByPage = numberElementByPage;
    }

    public void setServiceURL(String service_url) {
        this.serviceURL = service_url;
    }
}
