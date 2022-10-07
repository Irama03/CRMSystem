package com.WorkersS.mapstruct.dtos.workerOnPosition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.WorkersS.mapstruct.dtos.position.PositionGetDto;
import com.WorkersS.mapstruct.dtos.worker.WorkerSlimGetDto;
import com.WorkersS.models.WorkerOnPositionId;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class WorkerOnPositionGetDto {

    @JsonProperty("id")
    @NotNull
    private WorkerOnPositionId id;

    @JsonProperty("worker")
    @NotNull
    private WorkerSlimGetDto worker;

    @JsonProperty("position")
    @NotNull
    private PositionGetDto position;

    @JsonProperty("endDate")
    private Date endDate;

}
