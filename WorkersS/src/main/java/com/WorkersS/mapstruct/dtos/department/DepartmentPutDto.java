package com.WorkersS.mapstruct.dtos.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentPutDto {

    @JsonProperty("name")
    @NotNull
    private String name;

}
