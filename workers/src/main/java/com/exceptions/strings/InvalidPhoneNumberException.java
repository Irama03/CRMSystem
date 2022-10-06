package com.exceptions.strings;

import com.utils.Values;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException(String phone) {
        super(Values.INVALID_PHONE_NUMBER+phone);
    }
}
