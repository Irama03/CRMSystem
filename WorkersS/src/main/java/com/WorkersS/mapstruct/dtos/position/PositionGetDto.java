package com.WorkersS.mapstruct.dtos.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.WorkersS.mapstruct.dtos.department.DepartmentGetDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PositionGetDto {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("isHead")
    @NotNull
    private boolean isHead;

    @JsonProperty("department")
    private DepartmentGetDto department;

}
