package com.mapstruct.dtos.taskAfterTask;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapstruct.dtos.task.TaskSlimGetDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskAfterTaskGetDto {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("previousTask")
    private TaskSlimGetDto previousTask;

    @JsonProperty("nextTask")
    private TaskSlimGetDto nextTask;

}
