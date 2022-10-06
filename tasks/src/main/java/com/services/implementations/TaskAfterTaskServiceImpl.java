package com.services.implementations;

import com.exceptions.task.NoTaskWithSuchIdException;
import com.exceptions.taskAfterTask.NoTaskAfterTaskWithSuchIdException;
import com.exceptions.taskAfterTask.TaskAfterTaskIllegalArgumentException;
import com.exceptions.taskAfterTask.UnableToPerformActionOnTaskAfterTaskException;
import com.models.StateType;
import com.models.Task;
import com.models.TaskAfterTask;
import com.repositories.TaskAfterTaskRepository;
import com.repositories.TaskRepository;
import com.services.interfaces.TaskAfterTaskService;
import com.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAfterTaskServiceImpl implements TaskAfterTaskService {

    @Autowired
    private TaskAfterTaskRepository taskAfterTaskRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskAfterTask getTaskAfterTaskById(Long id) {
        return taskAfterTaskRepository.findById(id).orElseThrow(() -> new NoTaskAfterTaskWithSuchIdException(id));
    }

    @Override
    public Iterable<TaskAfterTask> getTasksAfterTasksOfFlow(Long flowId) {
        return taskAfterTaskRepository.findAllTasksAfterTasksOfFlow(flowId);
    }

    @Override
    public TaskAfterTask addTaskAfterTask(TaskAfterTask taskAfterTask) {
        taskAfterTask.setId(-1L);
        Task prevTask = taskAfterTask.getPreviousTask();
        Task nextTask = taskAfterTask.getNextTask();
        if (!taskRepository.existsById(prevTask.getId()))
            throw new NoTaskWithSuchIdException(prevTask.getId());
        if (!taskRepository.existsById(nextTask.getId()))
            throw new NoTaskWithSuchIdException(nextTask.getId());
        if (taskAfterTaskRepository.findTaskAfterTasksByPreviousTaskAndNextTask(prevTask, nextTask).iterator().hasNext())
            throw new TaskAfterTaskIllegalArgumentException(Values.SUCH_TASK_AFTER_TASK_ALREADY_EXISTS);
        if (nextTask.getState() != StateType.NEW)
            throw new UnableToPerformActionOnTaskAfterTaskException(Values.FORBIDDEN_TO_ADD_AND_DELETE_CONNECTION_WITH_PREV_TASKS_WITH_NOT_NEW_TASKS);
        return taskAfterTaskRepository.save(taskAfterTask);
    }

    @Override
    public void deleteTaskAfterTask(Long id) {
        TaskAfterTask tat = getTaskAfterTaskById(id);
        if (tat.getNextTask().getState() != StateType.NEW)
            throw new UnableToPerformActionOnTaskAfterTaskException(Values.FORBIDDEN_TO_ADD_AND_DELETE_CONNECTION_WITH_PREV_TASKS_WITH_NOT_NEW_TASKS);
        if (tat.getNextTask().getPreviousTasks().size() == 1)
            throw new UnableToPerformActionOnTaskAfterTaskException(Values.TASK_CAN_NOT_STAY_WITHOUT_PREV_TASKS);
        deleteTaskAfterTaskNoCheck(id);
    }

    @Override
    public void addTaskAfterTaskNoCheck(Task previousTask, Task nextTask) {
        taskAfterTaskRepository.save(new TaskAfterTask(previousTask, nextTask));
    }

    @Override
    public void deleteTaskAfterTaskNoCheck(Long id) {
        taskAfterTaskRepository.deleteById(id);
    }

    @Override
    public boolean taskAfterTaskExistsById(Long id) {
        return taskAfterTaskRepository.existsById(id);
    }

}
