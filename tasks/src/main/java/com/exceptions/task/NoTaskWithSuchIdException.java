package com.exceptions.task;

import com.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoTaskWithSuchIdException extends RuntimeException {

    public NoTaskWithSuchIdException(Long id) {
        super(Values.NO_TASK_WITH_SUCH_ID_EXCEPTION_STR + id);
    }

}
