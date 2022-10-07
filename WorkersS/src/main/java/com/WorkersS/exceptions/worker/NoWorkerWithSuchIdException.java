package com.WorkersS.exceptions.worker;

import com.WorkersS.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoWorkerWithSuchIdException extends RuntimeException {

    public NoWorkerWithSuchIdException(Long id) {
        super(Values.NO_WORKER_WITH_SUCH_ID_EXCEPTION_STR + id);
    }

}
