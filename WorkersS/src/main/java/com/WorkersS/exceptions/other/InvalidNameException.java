package com.WorkersS.exceptions.other;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNameException extends RuntimeException {

    public InvalidNameException(String explanation) {
        super(explanation);
        System.out.println(explanation);
    }
}
