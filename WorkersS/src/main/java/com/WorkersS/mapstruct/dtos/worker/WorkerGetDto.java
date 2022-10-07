package com.WorkersS.mapstruct.dtos.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.WorkersS.mapstruct.dtos.workerOnPosition.WorkerOnPositionGetDto;
import com.WorkersS.models.Contacts;
import com.WorkersS.models.PIB;
import com.WorkersS.models.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class WorkerGetDto {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("pib")
    @NotNull
    private PIB pib;

    @JsonProperty("contacts")
    @NotNull
    private Contacts contacts;

    @JsonProperty("role")
    @NotNull
    private Role role;

    @JsonProperty("positions")
    @NotNull
    private Set<WorkerOnPositionGetDto> positions;
}
