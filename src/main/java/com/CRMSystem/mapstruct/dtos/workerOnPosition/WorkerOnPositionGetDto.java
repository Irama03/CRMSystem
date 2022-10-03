package com.CRMSystem.mapstruct.dtos.workerOnPosition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.CRMSystem.mapstruct.dtos.position.PositionGetDto;
import com.CRMSystem.mapstruct.dtos.worker.WorkerSlimGetDto;
import com.CRMSystem.models.WorkerOnPositionId;
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
