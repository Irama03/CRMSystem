package com.CRMSystem.services.interfaces;

import com.CRMSystem.models.StateType;
import com.CRMSystem.models.Task;

import java.util.Set;

public interface TaskService {

    Task getTaskById(Long id);
    Task getFirstTaskOfFlow(Long flowId);
    Iterable<Task> getLastTasksOfFlow(Long flowId);

    Iterable<Task> getAll();
    Iterable<Task> getTasksOfFlow(Long flowId);
    Iterable<Task> getAllAllowedTasks(Long workerId);

    Task addTask(Task task, Set<Task> previousTasks);
    Task updateTask(Task task, Set<Task> previousTasks);
    void deleteTask(Long id);

    boolean taskExistsById(Long id);

    Task updateTaskState(Long id, StateType state);

    void approvePreviousTasks(Long id);
    void rejectPreviousTasks(Long id);
    void finishTask(Long id);

}
