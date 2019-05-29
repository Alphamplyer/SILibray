package com.library.webservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToModifyException extends RuntimeException{

    public UnableToModifyException(String message) {
        super(message);
    }
}
