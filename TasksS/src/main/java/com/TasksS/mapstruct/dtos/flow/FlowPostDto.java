package com.TasksS.mapstruct.dtos.flow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class FlowPostDto {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("isTemplate")
    @NotNull
    private boolean isTemplate;

}
