package com.TasksS.mapstruct.dtos.task;

import com.TasksS.models.TaskType;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("responsibleWorkerId")
    private Long responsibleWorkerId;

    @JsonProperty("performerId")
    private Long performerId;

    @JsonProperty("itemsIds")
    private String itemsIds;

}
