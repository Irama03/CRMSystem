package com.mapstruct.dtos.taskAfterTask;

import com.mapstruct.dtos.task.TaskSlimGetDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class TaskAfterTaskPostDto {

    @JsonProperty("previousTask")
    @NotNull
    private TaskSlimGetDto previousTask;

    @JsonProperty("nextTask")
    @NotNull
    private TaskSlimGetDto nextTask;

}
