package com.mapstruct.dtos.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.Contacts;
import com.models.PIB;
import com.models.Role;
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
