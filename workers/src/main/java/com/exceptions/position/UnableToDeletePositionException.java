package com.exceptions.position;

import com.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnableToDeletePositionException extends RuntimeException {

    public UnableToDeletePositionException() {
        super(Values.UNABLE_TO_DELETE_POSITION_EXCEPTION_STR);
    }
}
