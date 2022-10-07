package com.WorkersS.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.WorkersS.mapstruct.dtos.position.PositionGetDto;
import com.WorkersS.mapstruct.dtos.worker.WorkerPostDto;
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
