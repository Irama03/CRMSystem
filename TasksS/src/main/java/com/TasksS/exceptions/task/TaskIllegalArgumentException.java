package com.TasksS.exceptions.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TaskIllegalArgumentException extends RuntimeException{

    public TaskIllegalArgumentException(String explanation) {
        super(explanation);
    }
}
