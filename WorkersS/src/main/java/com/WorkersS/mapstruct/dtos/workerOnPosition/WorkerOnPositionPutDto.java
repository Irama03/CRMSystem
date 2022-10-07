package com.WorkersS.mapstruct.dtos.workerOnPosition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WorkerOnPositionPutDto {

    @JsonProperty("endDate")
    private Date endDate;

}
