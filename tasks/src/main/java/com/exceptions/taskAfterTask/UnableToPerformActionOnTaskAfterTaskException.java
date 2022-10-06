package com.exceptions.taskAfterTask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnableToPerformActionOnTaskAfterTaskException extends RuntimeException {

    public UnableToPerformActionOnTaskAfterTaskException(String explanation) {
        super(explanation);
    }
}
