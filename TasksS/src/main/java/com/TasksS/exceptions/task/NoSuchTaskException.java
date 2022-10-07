package com.TasksS.exceptions.task;

import com.TasksS.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoSuchTaskException extends RuntimeException {
    public NoSuchTaskException() {
        super(Values.NO_SUCH_TASK_EXCEPTION_STR);
    }

}
