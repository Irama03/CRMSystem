package com.WorkersS.exceptions.position;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PositionIllegalArgumentException extends RuntimeException{

    public PositionIllegalArgumentException(String explanation) {
        super(explanation);
    }
}
