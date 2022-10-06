package com.exceptions.flow;

import com.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoFlowWithSuchIdException extends RuntimeException {

    public NoFlowWithSuchIdException(Long id) {
        super(Values.NO_FLOW_WITH_SUCH_ID_EXCEPTION_STR + id);
    }

}
