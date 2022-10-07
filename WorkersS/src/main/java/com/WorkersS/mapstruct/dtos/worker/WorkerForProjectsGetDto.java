package com.WorkersS.mapstruct.dtos.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerForProjectsGetDto{

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("departmentId")
    @NotNull
    private Long departmentId;

    @JsonProperty("isHead")
    @NotNull
    private boolean isHead;

}
