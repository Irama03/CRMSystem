package com.WorkersS.exceptions.position;

import com.WorkersS.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnableToDeletePositionException extends RuntimeException {

    public UnableToDeletePositionException() {
        super(Values.UNABLE_TO_DELETE_POSITION_EXCEPTION_STR);
    }
}
