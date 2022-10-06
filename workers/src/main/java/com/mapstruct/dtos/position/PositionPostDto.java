package com.mapstruct.dtos.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mapstruct.dtos.department.DepartmentGetDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PositionPostDto {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("isHead")
    @NotNull
    private boolean isHead;

    @JsonProperty("department")
    private DepartmentGetDto department;
}
