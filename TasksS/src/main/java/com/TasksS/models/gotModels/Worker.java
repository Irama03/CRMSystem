package com.TasksS.models.gotModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Worker {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("departmentId")
    private Long departmentId;

    @JsonProperty("isHead")
    private boolean isHead;

}
