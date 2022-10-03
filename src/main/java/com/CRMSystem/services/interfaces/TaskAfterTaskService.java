package com.CRMSystem.services.interfaces;

import com.CRMSystem.models.Task;
import com.CRMSystem.models.TaskAfterTask;

public interface TaskAfterTaskService {

    TaskAfterTask getTaskAfterTaskById(Long id);
    Iterable<TaskAfterTask> getTasksAfterTasksOfFlow(Long flowId);

    TaskAfterTask addTaskAfterTask(TaskAfterTask taskAfterTask);
    void deleteTaskAfterTask(Long id);

    void addTaskAfterTaskNoCheck(Task previousTask, Task nextTask);
    void deleteTaskAfterTaskNoCheck(Long id);

    boolean taskAfterTaskExistsById(Long id);

}
