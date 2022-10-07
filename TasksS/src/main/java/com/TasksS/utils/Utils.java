package com.TasksS.utils;

import com.TasksS.exceptions.other.InvalidNameException;
import com.TasksS.exceptions.other.InvalidTimeException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Utils {

    public static Map<String, String> getExceptionResponse(Exception e) {
        Map<String,String> map = new HashMap<>();
        map.put("success", "false");
        map.put("error", e.getMessage());
        return map;
    }

    public String processString(String str) {
        return str == null ? null : str.replaceAll("\\s+", " ").trim();
    }

    public boolean isInvalidName(String name) {
        return name == null || name.isEmpty();
    }

    public void checkName(String name) {
        if (isInvalidName(name)) {
            throw new InvalidNameException(Values.INVALID_NAME);
        }
    }

    public void checkDateToBeAfterNow(Date date) {
        if (!date.after(new Date()))
            throw new InvalidTimeException(Values.INVALID_TIME_BEFORE_NOW);
    }

}
