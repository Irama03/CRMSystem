package com.mapstruct.mappers;
import com.mapstruct.dtos.task.TaskGetDto;
import com.mapstruct.dtos.task.TaskPatchDto;
import com.mapstruct.dtos.task.TaskPostDto;
import com.mapstruct.dtos.task.TaskSlimGetDto;
import com.models.Task;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskGetDto taskToTaskGetDto(Task task);
    Iterable<TaskGetDto> tasksToTasksGetDto(Iterable<Task> tasks);

    TaskSlimGetDto taskToTaskSlimGetDto(Task task);
    Set<Task> tasksSlimGetDtoToTasks(Set<TaskSlimGetDto> tasks);

    Task taskPostDtoToTask(TaskPostDto taskPostDto);
    Task taskPatchDtoToTask(TaskPatchDto taskPatchDto);

}
