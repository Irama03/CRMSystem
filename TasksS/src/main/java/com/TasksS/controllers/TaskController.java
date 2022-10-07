package com.TasksS.controllers;

import com.TasksS.exceptions.task.NoSuchTaskException;
import com.TasksS.exceptions.task.NoTaskWithSuchIdException;
import com.TasksS.exceptions.task.TaskIllegalArgumentException;
import com.TasksS.exceptions.task.UnableToPerformActionOnTaskException;
import com.TasksS.mapstruct.dtos.task.TaskGetDto;
import com.TasksS.mapstruct.dtos.task.TaskStateDto;
import com.TasksS.mapstruct.dtos.task.TaskWithPrevTasksPatchDto;
import com.TasksS.mapstruct.dtos.task.TaskWithPrevTasksPostDto;
import com.TasksS.mapstruct.mappers.TaskMapper;
import com.TasksS.models.Task;
import com.TasksS.services.implementations.TaskServiceImpl;
import com.TasksS.utils.Utils;
import com.TasksS.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:" + Values.PORT_OF_FRONTEND})
@RestController
@Validated
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;
    @Autowired
    TaskMapper taskMapper;

    @GetMapping("/{id}")
    public TaskGetDto getTask(@PathVariable("id") Long id) {
        return taskMapper.taskToTaskGetDto(taskService.getTaskById(id));
    }

    @GetMapping("/of_flow/{id}/first")
    public TaskGetDto getFirstTaskOfFlow(@PathVariable("id") Long id){
        return taskMapper.taskToTaskGetDto(taskService.getFirstTaskOfFlow(id));
    }

    @GetMapping("/of_flow/{id}/last")
    public Iterable<TaskGetDto> getLastTasksOfFlow(@PathVariable("id") Long id){
        return taskMapper.tasksToTasksGetDto(taskService.getLastTasksOfFlow(id));
    }

    @GetMapping()
    public Iterable<TaskGetDto> getTasks(){
        return taskMapper.tasksToTasksGetDto(taskService.getAll());
    }

    @GetMapping("/of_flow/{id}")
    public Iterable<TaskGetDto> getTasksOfFlow(@PathVariable("id") Long id){
        return taskMapper.tasksToTasksGetDto(taskService.getTasksOfFlow(id));
    }

    @GetMapping("/all_allowed/{workerId}")
    public Iterable<TaskGetDto> getAllAllowedTasks(@PathVariable("workerId") Long workerId){
        return taskMapper.tasksToTasksGetDto(taskService.getAllAllowedTasks(workerId));
    }

    @PostMapping
    public TaskGetDto addTask(@Valid @RequestBody TaskWithPrevTasksPostDto taskWithPrevTasksPostDto){
        return taskMapper.taskToTaskGetDto(taskService.addTask(
                        taskMapper.taskPostDtoToTask(taskWithPrevTasksPostDto.getTask()),
                        taskMapper.tasksSlimGetDtoToTasks(taskWithPrevTasksPostDto.getPreviousTasks())));
    }

    @PatchMapping("/{id}")
    public TaskGetDto updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskWithPrevTasksPatchDto taskWithPrevTasksPatchDto) {
        Task task = taskMapper.taskPatchDtoToTask(taskWithPrevTasksPatchDto.getTask());
        task.setId(id);
        return taskMapper.taskToTaskGetDto(taskService.updateTask(task, taskMapper.tasksSlimGetDtoToTasks(taskWithPrevTasksPatchDto.getPreviousTasks())));
    }

    @PatchMapping("/{id}/state")
    public TaskGetDto updateTaskState(@PathVariable("id") Long id, @Valid @RequestBody TaskStateDto taskStateDto) {
        return taskMapper.taskToTaskGetDto(taskService.updateTaskState(id, taskStateDto.getState()));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/{id}/approve_previous")
    public void approvePreviousTasks(@PathVariable("id") Long id) {
        taskService.approvePreviousTasks(id);
    }

    @PostMapping("/{id}/reject_previous")
    public void rejectPreviousTasks(@PathVariable("id") Long id) {
        taskService.rejectPreviousTasks(id);
    }

    @PostMapping("/{id}/finish")
    public void finishTask(@PathVariable("id") Long id) {
        taskService.finishTask(id);
    }

    @ExceptionHandler(NoTaskWithSuchIdException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoTaskWithSuchIdException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TaskIllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(TaskIllegalArgumentException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchTaskException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoSuchTaskException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnableToPerformActionOnTaskException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(UnableToPerformActionOnTaskException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

}
