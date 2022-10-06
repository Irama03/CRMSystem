package com.exceptions.position;

import com.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoPositionWithSuchIdException extends RuntimeException {

    public NoPositionWithSuchIdException(Long id) {
        super(Values.NO_POSITION_WITH_SUCH_ID_EXCEPTION_STR + id);
    }

}
