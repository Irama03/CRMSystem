package com.CRMSystem.exceptions.department;

import com.CRMSystem.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NoDepartmentWithSuchIdException extends RuntimeException {

    public NoDepartmentWithSuchIdException(Long id) {
        super(Values.NO_DEPARTMENT_WITH_SUCH_ID_EXCEPTION_STR + id);
    }

}
