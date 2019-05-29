package com.library.webservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SQLConflitcException extends RuntimeException {

    public SQLConflitcException (String message) {
        super(message);
    }
}
