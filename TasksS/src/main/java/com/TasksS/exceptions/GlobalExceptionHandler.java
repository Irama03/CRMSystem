package com.TasksS.exceptions;

import com.TasksS.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Map<String,String>> handleException(Exception e) {
        return new ResponseEntity<>(Utils.getExceptionResponse(e), HttpStatus.BAD_REQUEST);
    }

}
