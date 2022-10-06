package com.controllers;

import com.exceptions.taskAfterTask.NoTaskAfterTaskWithSuchIdException;
import com.exceptions.taskAfterTask.TaskAfterTaskIllegalArgumentException;
import com.exceptions.taskAfterTask.UnableToPerformActionOnTaskAfterTaskException;
import com.mapstruct.dtos.taskAfterTask.TaskAfterTaskGetDto;
import com.mapstruct.dtos.taskAfterTask.TaskAfterTaskPostDto;
import com.mapstruct.mappers.TaskAfterTaskMapper;
import com.services.implementations.TaskAfterTaskServiceImpl;
import com.utils.Utils;
import com.utils.Values;
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
@RequestMapping("/tasks_after_tasks")
public class TaskAfterTaskController {

    @Autowired
    TaskAfterTaskServiceImpl taskAfterTaskService;
    @Autowired
    TaskAfterTaskMapper taskAfterTaskMapper;

    @GetMapping("/{id}")
    public TaskAfterTaskGetDto getTaskAfterTask(@PathVariable("id") Long id) {
        return taskAfterTaskMapper.taskAfterTaskToTaskAfterTaskGetDto(taskAfterTaskService.getTaskAfterTaskById(id));
    }

    @GetMapping("/of_flow/{id}")
    public Iterable<TaskAfterTaskGetDto> getTasksAfterTasksOfFlow(@PathVariable("id") Long id){
        return taskAfterTaskMapper.tasksAfterTasksToTasksAfterTasksGetDto(taskAfterTaskService.getTasksAfterTasksOfFlow(id));
    }

    @PostMapping
    public TaskAfterTaskGetDto addTaskAfterTask(@Valid @RequestBody TaskAfterTaskPostDto taskAfterTaskPostDto){
        return taskAfterTaskMapper.taskAfterTaskToTaskAfterTaskGetDto(taskAfterTaskService.addTaskAfterTask(
                taskAfterTaskMapper.taskAfterTaskPostDtoToTaskAfterTask(taskAfterTaskPostDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteTaskAfterTask(@PathVariable("id") Long id) {
        taskAfterTaskService.deleteTaskAfterTask(id);
    }

    @ExceptionHandler(NoTaskAfterTaskWithSuchIdException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoTaskAfterTaskWithSuchIdException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TaskAfterTaskIllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(TaskAfterTaskIllegalArgumentException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnableToPerformActionOnTaskAfterTaskException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(UnableToPerformActionOnTaskAfterTaskException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

}
