package com.CRMSystem.exceptions.workerOnPosition;

import com.CRMSystem.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoWorkerOnPositionWithSuchIdException extends RuntimeException {

    public NoWorkerOnPositionWithSuchIdException(Long workerId, Long positionId, Date startDate) {
        super(Values.NO_WORKER_ON_POSITION_WITH_SUCH_ID_EXCEPTION_STR + workerId + ", " + positionId + ", " + startDate);
    }

}
