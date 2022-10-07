package com.TasksS.mapstruct.mappers;
import com.TasksS.mapstruct.dtos.taskAfterTask.TaskAfterTaskGetDto;
import com.TasksS.mapstruct.dtos.taskAfterTask.TaskAfterTaskPostDto;
import com.TasksS.models.TaskAfterTask;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskAfterTaskMapper {
    TaskAfterTaskGetDto taskAfterTaskToTaskAfterTaskGetDto(TaskAfterTask taskAfterTask);
    Iterable<TaskAfterTaskGetDto> tasksAfterTasksToTasksAfterTasksGetDto(Iterable<TaskAfterTask> tasksAfterTasks);

    TaskAfterTask taskAfterTaskPostDtoToTaskAfterTask(TaskAfterTaskPostDto taskAfterTaskPostDto);

}
