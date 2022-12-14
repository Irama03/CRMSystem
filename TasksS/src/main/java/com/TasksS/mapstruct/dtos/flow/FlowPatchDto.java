package com.TasksS.mapstruct.dtos.flow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FlowPatchDto {

    @JsonProperty("name")
    @NotNull
    private String name;

}
