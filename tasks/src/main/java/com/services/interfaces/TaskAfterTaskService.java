package com.services.interfaces;

import com.models.Task;
import com.models.TaskAfterTask;

public interface TaskAfterTaskService {

    TaskAfterTask getTaskAfterTaskById(Long id);
    Iterable<TaskAfterTask> getTasksAfterTasksOfFlow(Long flowId);

    TaskAfterTask addTaskAfterTask(TaskAfterTask taskAfterTask);
    void deleteTaskAfterTask(Long id);

    void addTaskAfterTaskNoCheck(Task previousTask, Task nextTask);
    void deleteTaskAfterTaskNoCheck(Long id);

    boolean taskAfterTaskExistsById(Long id);

}
