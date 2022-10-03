package com.CRMSystem.mapstruct.dtos.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.CRMSystem.mapstruct.dtos.workerOnPosition.WorkerOnPositionGetDto;
import com.CRMSystem.models.Contacts;
import com.CRMSystem.models.PIB;
import com.CRMSystem.models.Role;
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
