package com.TasksS.mapstruct.dtos.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.TasksS.mapstruct.dtos.flow.FlowSlimGetDto;
import com.TasksS.models.StateType;
import com.TasksS.models.TaskType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class TaskPostDto {

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("flow")
    private FlowSlimGetDto flow;

    @JsonProperty("description")
    private String description;

    @JsonProperty("deadline")
    private Date deadline;

    //NEW or IN_PROGRESS
    @JsonProperty("state")
    private StateType state;

    @JsonProperty("type")
    @NotNull
    private TaskType type;

    @JsonProperty("document")
    private String document;

    @JsonProperty("costOfWork")
    private Double costOfWork;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("creatorId")
    private Long creatorId;

    @JsonProperty("responsibleWorkerId")
    private Long responsibleWorkerId;

    @JsonProperty("performerId")
    private Long performerId;

    @NotNull
    @JsonProperty("isTemplate")
    private boolean isTemplate;

}
