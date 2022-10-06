package com.exceptions.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentIllegalArgumentException extends RuntimeException{

    public DepartmentIllegalArgumentException(String explanation) {
        super(explanation);
    }
}
