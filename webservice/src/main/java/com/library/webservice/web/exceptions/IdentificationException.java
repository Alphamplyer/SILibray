package com.library.webservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IdentificationException extends RuntimeException {

    public IdentificationException (String message) {
        super(message);
    }
}
