package com.CRMSystem.exceptions.taskAfterTask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TaskAfterTaskIllegalArgumentException extends RuntimeException{

    public TaskAfterTaskIllegalArgumentException(String explanation) {
        super(explanation);
    }
}
