package com.CRMSystem.mapstruct.dtos.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class DepartmentPostDto {

    @JsonProperty("name")
    @NotNull
    private String name;

}
