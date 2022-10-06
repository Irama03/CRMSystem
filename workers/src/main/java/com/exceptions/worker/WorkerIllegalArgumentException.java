package com.exceptions.worker;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WorkerIllegalArgumentException extends RuntimeException{

    public WorkerIllegalArgumentException(String explanation) {
        super(explanation);
    }
}
