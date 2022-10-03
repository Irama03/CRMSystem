package com.CRMSystem.exceptions.other;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidTimeException extends RuntimeException {

    public InvalidTimeException(String explanation) {
        super(explanation);
        System.out.println(explanation);
    }
}
