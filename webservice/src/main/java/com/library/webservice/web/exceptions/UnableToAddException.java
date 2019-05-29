package com.library.webservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToAddException extends RuntimeException {

    public UnableToAddException(String message) {
        super(message);
    }
}
