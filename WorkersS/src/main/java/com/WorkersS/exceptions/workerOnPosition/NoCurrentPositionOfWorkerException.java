package com.WorkersS.exceptions.workerOnPosition;

import com.WorkersS.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoCurrentPositionOfWorkerException extends RuntimeException {
    public NoCurrentPositionOfWorkerException(Long workerId) {
        super(Values.NO_CURRENT_POSITION_OF_WORKER_WITH_SUCH_ID_EXCEPTION_STR + workerId);
    }
}
