package com.exceptions.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnableToPerformActionOnTaskException extends RuntimeException {

    public UnableToPerformActionOnTaskException(String explanation) {
        super(explanation);
    }
}
