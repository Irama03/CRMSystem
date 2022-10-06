package com.utils;

public class Values {

    public static final int PORT_OF_FRONTEND = 3000;

    public static final String NO_DEPARTMENT_WITH_SUCH_ID_EXCEPTION_STR = "No department with such id: ";
    public static final String NO_POSITION_WITH_SUCH_ID_EXCEPTION_STR = "No position with such id: ";
    public static final String NO_WORKER_WITH_SUCH_ID_EXCEPTION_STR = "No worker with such id: ";
    public static final String NO_WORKER_ON_POSITION_WITH_SUCH_ID_EXCEPTION_STR = "No worker on position with such id: ";
    public static final String NO_CURRENT_POSITION_OF_WORKER_WITH_SUCH_ID_EXCEPTION_STR = "No current position of worker with such id: ";

    public static final String POSITION_WITH_SUCH_NAME_ALREADY_EXISTS = "Position with such name already exists in this department!";
    public static final String HEAD_POSITION_ALREADY_EXISTS_IN_THIS_DEPARTMENT = "Head position already exists in this department!";
    public static final String DEPARTMENT_WITH_SUCH_NAME_ALREADY_EXISTS = "Department with such name already exists!";
    public static final String WORKER_WITH_SUCH_EMAIL_ALREADY_EXISTS = "Worker with such email already exists!";

    public static final String UNABLE_TO_DELETE_DEPARTMENT_EXCEPTION_STR = "Department can not be deleted, because there are workers in this department!";
    public static final String UNABLE_TO_DELETE_POSITION_EXCEPTION_STR = "Position can not be deleted, because there are workers on this position!";

    public static final String INVALID_NAME = "Incorrect name! Name shouldn't be empty!";
    public static final String INVALID_TIME_BEFORE_NOW = "Incorrect date! This date should be after now!";

    public static final String INVALID_CONTACTS = "Incorrect contacts!";
    public static final String INVALID_DATE = "Incorrect date!";
    public static final String INVALID_PHONE_NUMBER = "Incorrect phone number format: ";

    //public static final int MIN_AGE_OF_PERSON_TO_HIRE = 17;
    //public static final int MAX_AGE_OF_PERSON_TO_HIRE = 120;
    public static final int MAX_QUANTITY_OF_YEARS_AGO_TO_BE_HIRED = 100;
    public static final int MAX_QUANTITY_OF_YEARS_AFTER_TO_BE_FIRED = 100;
    
}
