package com.TasksS.mapstruct.dtos.flow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.TasksS.mapstruct.dtos.task.TaskSlimGetDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class FlowGetDto {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("isFailed")
    private boolean isFailed;

    @JsonProperty("isTemplate")
    @NotNull
    private boolean isTemplate;

    @JsonProperty("tasks")
    private Set<TaskSlimGetDto> tasks;

}
