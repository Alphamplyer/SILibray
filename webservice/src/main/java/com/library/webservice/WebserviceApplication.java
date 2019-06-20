package com.library.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:${APP_HOME}/webservice.properties")
@SpringBootApplication
public class WebserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}
}
