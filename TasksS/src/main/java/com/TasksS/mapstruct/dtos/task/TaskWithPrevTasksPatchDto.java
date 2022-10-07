package com.TasksS.mapstruct.dtos.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TaskWithPrevTasksPatchDto {

    @JsonProperty("task")
    TaskPatchDto task;

    @JsonProperty("previousTasks")
    private Set<TaskSlimGetDto> previousTasks;

}
