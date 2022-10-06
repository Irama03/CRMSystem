package com.mapstruct.dtos.task;

import com.mapstruct.dtos.worker.WorkerSlimGetDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.TaskType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class TaskPatchDto {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("deadline")
    private Date deadline;

    @JsonProperty("type")
    @NotNull
    private TaskType type;

    @JsonProperty("document")
    private String document;

    @JsonProperty("costOfWork")
    private Double costOfWork;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("responsibleWorker")
    private WorkerSlimGetDto workerResponsibleId;

    @JsonProperty("performer")
    private WorkerSlimGetDto performer;

    @JsonProperty("itemsIds")
    private String itemsIds;

}
