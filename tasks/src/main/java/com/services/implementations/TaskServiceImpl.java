package com.services.implementations;

import com.exceptions.task.NoTaskWithSuchIdException;
import com.exceptions.task.TaskIllegalArgumentException;
import com.exceptions.task.UnableToPerformActionOnTaskException;
import com.models.*;
import com.repositories.TaskRepository;
import com.services.interfaces.TaskService;
import com.utils.Utils;
import com.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private TaskAfterTaskServiceImpl taskAfterTaskService;
    @Autowired
    private WorkerServiceImpl workerService;
    @Autowired
    private WorkerOnPositionServiceImpl workerOnPositionService;

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoTaskWithSuchIdException(id));
    }

    @Override
    public Task getFirstTaskOfFlow(Long flowId) {
        return taskRepository.getFirstTaskOfFlow(flowId).orElse(null);
    }

    @Override
    public Iterable<Task> getLastTasksOfFlow(Long flowId) {
        return taskRepository.getLastTasksOfFlow(flowId);
    }

    @Override
    public Iterable<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public Iterable<Task> getTasksOfFlow(Long flowId) {
        return taskRepository.findAllTasksOfFlow(flowId);
    }

    @Override
    public Iterable<Task> getAllAllowedTasks(Long workerId) {
        WorkerOnPosition curPosition = workerOnPositionService.getCurrentPositionOfWorker(workerId);
        return taskRepository.getAllAllowedTasks(workerId,
                workerService.getHeadOfDepartment(curPosition.getPosition().getDepartment().getId()).getId());
    }

    @Override
    public Task addTask(Task task, Set<Task> previousTasks) {
        task.setId(-1L);
        processTextCommonFields(task, task);
        if (!task.isTemplate()) {
            processNotTemplateCommonFields(task, task, previousTasks);
            //state
            if (task.isFinished())
                throw new TaskIllegalArgumentException(Values.INVALID_TASK_STATE);
            //flow
            if (task.getFlow() == null)
                throw new TaskIllegalArgumentException(Values.TASK_SHOULD_BE_IN_FLOW);
            //start_date
            if (task.getState() == StateType.IN_PROGRESS)
                task.setStartDate(new Date());
            else task.setStartDate(null);
            //end_date
            task.setEndDate(null);
            //creatorId
            if (task.getCreator() == null)
                throw new TaskIllegalArgumentException(Values.TASK_NEEDS_CREATOR);
        }
        else {
            task.resetFieldsOfTemplate();
        }
        task = taskRepository.save(task);
        if (previousTasks != null && !previousTasks.isEmpty()) {
            for (Task prevTask: previousTasks) {
                taskAfterTaskService.addTaskAfterTaskNoCheck(prevTask, task);
            }
        }
        return task;
    }

    @Override
    public Task updateTask(Task task, Set<Task> previousTasks) {
        Long id = task.getId();
        Task t = getTaskById(id);
        if (t.getState() != StateType.NEW) {
            t.setNotes(utils.processString(task.getNotes()));
        }
        else {
            processTextCommonFields(t, task);
            if (!t.isTemplate()) {
                processNotTemplateCommonFields(t, task, previousTasks);
                t.setType(task.getType());
                t.setCostOfWork(task.getCostOfWork());
                t.setResponsibleWorker(task.getResponsibleWorker());
                t.setPerformer(task.getPerformer());
            }
            //If isTemplate, reset do not needed. Just not update fields in t
            if (previousTasks != null) {
                Set<TaskAfterTask> prevPrevTasks = t.getPreviousTasks();
                if (!previousTasks.isEmpty()) {
                    for (TaskAfterTask tat: prevPrevTasks) {
                        if (!prevTasksContainTaskAfterTask(previousTasks, tat)) {
                            taskAfterTaskService.deleteTaskAfterTaskNoCheck(tat.getId());
                            prevPrevTasks.remove(tat);
                        }
                    }
                    for (Task prevTask: previousTasks) {
                        if (!prevTasksAfterTasksContainTask(prevPrevTasks, prevTask)) {
                            taskAfterTaskService.addTaskAfterTaskNoCheck(prevTask, t);
                        }
                    }
                }
                else {
                    for (TaskAfterTask tat: prevPrevTasks) {
                        taskAfterTaskService.deleteTaskAfterTaskNoCheck(tat.getId());
                        prevPrevTasks.remove(tat);
                    }
                }
            }
        }
        return taskRepository.save(t);
    }

    private void processTextCommonFields(Task task, Task source) {
        //name
        String name = utils.processString(source.getName());
        utils.checkName(name);
        task.setName(name);
        //description
        task.setDescription(utils.processString(source.getDescription()));
        //notes
        task.setNotes(utils.processString(source.getNotes()));
    }

    private void processNotTemplateCommonFields(Task task, Task source, Set<Task> previousTasks) {
        //deadline
        utils.checkDateToBeAfterNow(source.getDeadline());
        //document
        task.setDocument(utils.processString(source.getDocument()));
        //task_type
        if (source.getType() == TaskType.DOCUMENT) {
            if (source.getDocument() != null)
                throw new TaskIllegalArgumentException(Values.NOT_ALLOWED_DOCUMENT);
        }
        //cost_of_work
        if (source.getCostOfWork() < 0)
            source.setCostOfWork(0.0);
        //responsibleWorker
        if (source.getResponsibleWorker() == null)
            throw new TaskIllegalArgumentException(Values.TASK_NEEDS_RESPONSIBLE_WORKER);
        Position responsibleWorkerCurPos = workerOnPositionService.getCurrentPositionOfWorker(source.getResponsibleWorker().getId()).getPosition();
        if (!responsibleWorkerCurPos.isHead())
            throw new TaskIllegalArgumentException(Values.ONLY_HEAD_CAN_BE_RESPONSIBLE_FOR_TASK);
        //performer
        if (source.getPerformer() != null) {
            Position performerCurPos = workerOnPositionService.getCurrentPositionOfWorker(source.getPerformer().getId()).getPosition();
            if (!performerCurPos.getDepartment().getId().equals(responsibleWorkerCurPos.getDepartment().getId()))
                throw new TaskIllegalArgumentException(Values.PERFORMER_SHOULD_BE_IN_DEPARTMENT_OF_RESPONSIBLE_WORKER);
        }
        //previousTasks
        if (previousTasks == null || previousTasks.isEmpty()) {
            if (source.getType() == TaskType.APPROVEMENT)
                throw new TaskIllegalArgumentException(Values.APPROVEMENT_TASK_CAN_NOT_BE_FIRST);
            Task firstTask = getFirstTaskOfFlow(task.getFlow().getId());
            //Prev first task becomes second task with new connection taskAfterTask
            if (firstTask != null && !firstTask.getId().equals(task.getId()))
                taskAfterTaskService.addTaskAfterTaskNoCheck(task, firstTask);
        }
    }

    private boolean prevTasksAfterTasksContainTask(Set<TaskAfterTask> prevTasks, Task task) {
        for (TaskAfterTask tat: prevTasks) {
            if (tat.getPreviousTask().equals(task)) {
                return true;
            }
        }
        return false;
    }

    private boolean prevTasksContainTaskAfterTask(Set<Task> previousTasks, TaskAfterTask tat) {
        for(Task prevTask: previousTasks) {
            if (prevTask.equals(tat.getPreviousTask())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        if (task.getState() != StateType.NEW)
            throw new UnableToPerformActionOnTaskException(Values.FORBIDDEN_TO_DELETE_NOT_NEW_TASKS);
        for (TaskAfterTask prevTAT: task.getPreviousTasks()) {
            for (TaskAfterTask nextTAT: task.getNextTasks()) {
                taskAfterTaskService.addTaskAfterTaskNoCheck(prevTAT.getPreviousTask(), nextTAT.getNextTask());
                taskAfterTaskService.deleteTaskAfterTaskNoCheck(prevTAT.getId());
                taskAfterTaskService.deleteTaskAfterTaskNoCheck(nextTAT.getId());
            }
        }
        taskRepository.deleteById(id);
    }

    @Override
    public boolean taskExistsById(Long id) {
        return taskRepository.existsById(id);
    }

    @Override
    public Task updateTaskState(Long id, StateType state) {
        Task task = getTaskById(id);
        if (task.isTemplate() || task.getState() == state)
            return task;
        if (task.isFinished())
            throw new TaskIllegalArgumentException(Values.FORBIDDEN_TO_CHANGE_STATE_OF_FINISHED_TASK);
        if (state == StateType.NEW ||
                (task.getState() == StateType.NEW && state != StateType.IN_PROGRESS) ||
                (task.getState() == StateType.IN_PROGRESS && !state.isFinished()))
            throw new TaskIllegalArgumentException(Values.INVALID_STATE);
        task.setState(state);
        if (state == StateType.IN_PROGRESS)
            task.setStartDate(new Date());
        task = taskRepository.save(task);
        if (state.isFinished()) {
            for (TaskAfterTask tat: task.getNextTasks()) {
                Task nextTask = tat.getNextTask();
                if (allPreviousTasksAreFinished(nextTask)) {
                    nextTask.setState(StateType.IN_PROGRESS);
                    nextTask.setStartDate(new Date());
                    taskRepository.save(nextTask);
                }
            }
        }
        return task;
    }

    private boolean allPreviousTasksAreFinished(Task task) {
        for (TaskAfterTask prevTask: task.getPreviousTasks()) {
            if (!prevTask.getPreviousTask().isFinished())
                return false;
        }
        return true;
    }

    @Override
    public void approvePreviousTasks(Long id) {
        Task task = getTaskById(id);
        if (task.getType() != TaskType.APPROVEMENT)
            throw new UnableToPerformActionOnTaskException(Values.FORBIDDEN_TO_APPROVE_PREV_TASKS_OF_TASK);
        finishTask(task, true);
    }

    @Override
    public void rejectPreviousTasks(Long id) {
        Task task = getTaskById(id);
        if (task.getType() != TaskType.APPROVEMENT)
            throw new UnableToPerformActionOnTaskException(Values.FORBIDDEN_TO_REJECT_PREV_TASKS_OF_TASK);
        finishTask(task, false);
        for (TaskAfterTask prevTAT: task.getPreviousTasks()) {
            prevTAT.getPreviousTask().setState(StateType.FAILED);
            taskRepository.save(prevTAT.getPreviousTask());
            //Then:
            // 1) New task needs to be added after this task
            //OR
            // 2) Flow becomes failed.
            //-------
            // In both cases: states of next existing tasks stay NEW
        }
    }

    private void finishTask(Task task, boolean nextBecomeInProgress) {
        if (task.getState() != StateType.IN_PROGRESS)
            throw new UnableToPerformActionOnTaskException(Values.FORBIDDEN_TO_FINISH_NOT_IN_PROGRESS_TASK);
        task.setState(StateType.DONE);
        task.setEndDate(new Date());
        taskRepository.save(task);
        if (nextBecomeInProgress) {
            for (TaskAfterTask nextTAT: task.getNextTasks()) {
                nextTAT.getNextTask().setState(StateType.IN_PROGRESS);
                taskRepository.save(nextTAT.getNextTask());
            }
        }
    }

    @Override
    public void finishTask(Long id) {
        Task task = getTaskById(id);
        finishTask(task, true);
    }

}
