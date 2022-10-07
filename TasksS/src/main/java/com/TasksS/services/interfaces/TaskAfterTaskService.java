package com.TasksS.services.interfaces;

import com.TasksS.models.Task;
import com.TasksS.models.TaskAfterTask;

public interface TaskAfterTaskService {

    TaskAfterTask getTaskAfterTaskById(Long id);
    Iterable<TaskAfterTask> getTasksAfterTasksOfFlow(Long flowId);

    TaskAfterTask addTaskAfterTask(TaskAfterTask taskAfterTask);
    void deleteTaskAfterTask(Long id);

    void addTaskAfterTaskNoCheck(Task previousTask, Task nextTask);
    void deleteTaskAfterTaskNoCheck(Long id);

    boolean taskAfterTaskExistsById(Long id);

}
