package com.mapstruct.mappers;
import com.mapstruct.dtos.taskAfterTask.TaskAfterTaskGetDto;
import com.mapstruct.dtos.taskAfterTask.TaskAfterTaskPostDto;
import com.models.TaskAfterTask;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskAfterTaskMapper {
    TaskAfterTaskGetDto taskAfterTaskToTaskAfterTaskGetDto(TaskAfterTask taskAfterTask);
    Iterable<TaskAfterTaskGetDto> tasksAfterTasksToTasksAfterTasksGetDto(Iterable<TaskAfterTask> tasksAfterTasks);

    TaskAfterTask taskAfterTaskPostDtoToTaskAfterTask(TaskAfterTaskPostDto taskAfterTaskPostDto);

}
