package com.TasksS.mapstruct.dtos.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TaskWithPrevTasksPostDto {

    @JsonProperty("task")
    TaskPostDto task;

    @JsonProperty("previousTasks")
    private Set<TaskSlimGetDto> previousTasks;

}
