package com.exceptions.department;

import com.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnableToDeleteDepartmentException extends RuntimeException {

    public UnableToDeleteDepartmentException() {
        super(Values.UNABLE_TO_DELETE_DEPARTMENT_EXCEPTION_STR);
    }
}
