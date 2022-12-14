package com.WorkersS.mapstruct.dtos.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.WorkersS.models.PIB;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WorkerSlimGetDto {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("pib")
    @NotNull
    private PIB pib;

}
