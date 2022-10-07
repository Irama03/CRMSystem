package com.WorkersS.mapstruct.dtos.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.WorkersS.models.Contacts;
import com.WorkersS.models.PIB;
import com.WorkersS.models.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class WorkerPostDto {

    @JsonProperty("pib")
    @NotNull
    private PIB pib;

    @JsonProperty("contacts")
    @NotNull
    private Contacts contacts;

    @JsonProperty("role")
    @NotNull
    private Role role;

}
