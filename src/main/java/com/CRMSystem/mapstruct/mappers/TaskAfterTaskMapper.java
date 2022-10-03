package com.CRMSystem.mapstruct.mappers;
import com.CRMSystem.mapstruct.dtos.taskAfterTask.TaskAfterTaskGetDto;
import com.CRMSystem.mapstruct.dtos.taskAfterTask.TaskAfterTaskPostDto;
import com.CRMSystem.models.TaskAfterTask;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskAfterTaskMapper {
    TaskAfterTaskGetDto taskAfterTaskToTaskAfterTaskGetDto(TaskAfterTask taskAfterTask);
    Iterable<TaskAfterTaskGetDto> tasksAfterTasksToTasksAfterTasksGetDto(Iterable<TaskAfterTask> tasksAfterTasks);

    TaskAfterTask taskAfterTaskPostDtoToTaskAfterTask(TaskAfterTaskPostDto taskAfterTaskPostDto);

}
