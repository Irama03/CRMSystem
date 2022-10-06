package com.exceptions.taskAfterTask;

import com.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoTaskAfterTaskWithSuchIdException extends RuntimeException {

    public NoTaskAfterTaskWithSuchIdException(Long id) {
        super(Values.NO_TASK_AFTER_TASK_WITH_SUCH_ID_EXCEPTION_STR + id);
    }

}
