package com.CRMSystem.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.CRMSystem.mapstruct.dtos.position.PositionGetDto;
import com.CRMSystem.mapstruct.dtos.worker.WorkerPostDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class WorkerAndPositionPostDto {

    @JsonProperty("worker")
    @NotNull
    WorkerPostDto workerPostDto;

    @JsonProperty("position")
    @NotNull
    PositionGetDto positionGetDto;
}
