package com.TasksS.mapstruct.dtos.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.TasksS.models.StateType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStateDto {

    @JsonProperty("state")
    StateType state;
}
