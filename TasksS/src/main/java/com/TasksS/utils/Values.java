package com.TasksS.utils;

public class Values {

    public static final int PORT_OF_FRONTEND = 3000;

    public static final String NO_FLOW_WITH_SUCH_ID_EXCEPTION_STR = "No flow with such id: ";
    public static final String NO_TASK_WITH_SUCH_ID_EXCEPTION_STR = "No task with such id: ";
    public static final String NO_TASK_AFTER_TASK_WITH_SUCH_ID_EXCEPTION_STR = "No task after task with such id: ";

    public static final String FLOW_WITH_SUCH_NAME_ALREADY_EXISTS = "Flow with such name already exists!";

    public static final String NO_SUCH_TASK_EXCEPTION_STR = "No such task!";

    public static final String FORBIDDEN_TO_DELETE_NOT_NEW_TASKS = "It is forbidden to delete tasks, which state is not NEW!";
    public static final String FORBIDDEN_TO_APPROVE_PREV_TASKS_OF_TASK = "It is forbidden to approve previous tasks of task, which type is not APPROVEMENT!";
    public static final String FORBIDDEN_TO_REJECT_PREV_TASKS_OF_TASK = "It is forbidden to reject previous tasks of task, which type is not APPROVEMENT!";
    public static final String FORBIDDEN_TO_FINISH_NOT_IN_PROGRESS_TASK = "It is forbidden to finish tasks, which state is not IN_PROGRESS!";

    public static final String INVALID_NAME = "Incorrect name! Name shouldn't be empty!";
    public static final String INVALID_TIME_BEFORE_NOW = "Incorrect date! This date should be after now!";

    public static final String INVALID_TASK_STATE = "State of created task should be NEW or IN_PROGRESS!";
    public static final String TASK_SHOULD_BE_IN_FLOW = "Flow of the task should not be null!";
    public static final String NOT_ALLOWED_DOCUMENT = "It is not allowed to add document when creating task of type DOCUMENT (because document will be added when completing task)";
    public static final String NECESSARY_DOCUMENT = "Document-contract is necessary when there is a partner-customer!";
    public static final String APPROVEMENT_TASK_CAN_NOT_BE_FIRST = "Task of type APPROVEMENT can not be first, because it should approve previous tasks!";
    public static final String TASK_NEEDS_CREATOR = "Task necessarily needs a creator!";
    public static final String TASK_NEEDS_RESPONSIBLE_WORKER = "Task necessarily needs a responsible worker!";
    public static final String ONLY_HEAD_CAN_BE_RESPONSIBLE_FOR_TASK = "Responsible worker is not a head of department! Only head of department can be responsible for a task!";
    public static final String PERFORMER_SHOULD_BE_IN_DEPARTMENT_OF_RESPONSIBLE_WORKER = "Worker-performer needs to be in the department of responsible worker of the task!";

    public static final String INVALID_STATE = "Incorrect change of state!";
    public static final String FORBIDDEN_TO_CHANGE_STATE_OF_FINISHED_TASK = "It is forbidden to change state of finished task!";

    public static final String SUCH_TASK_AFTER_TASK_ALREADY_EXISTS = "Such task after task already exists!";

    public static final String TASK_CAN_NOT_STAY_WITHOUT_PREV_TASKS = "Unable to delete task after task because next task can not stay without connections with previous tasks!";
    public static final String FORBIDDEN_TO_ADD_AND_DELETE_CONNECTION_WITH_PREV_TASKS_WITH_NOT_NEW_TASKS = "It is forbidden to add and delete connections with previous tasks of tasks, which states are not NEW!";
    
}
