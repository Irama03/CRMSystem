package com.CRMSystem.mapstruct.dtos.task;

import com.CRMSystem.mapstruct.dtos.worker.WorkerSlimGetDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.CRMSystem.mapstruct.dtos.flow.FlowSlimGetDto;
import com.CRMSystem.mapstruct.dtos.taskAfterTask.TaskAfterTaskGetDto;
import com.CRMSystem.models.StateType;
import com.CRMSystem.models.TaskType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TaskGetDto {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("flow")
    private FlowSlimGetDto flow;

    @JsonProperty("description")
    private String description;

    @JsonProperty("deadline")
    private Date deadline;

    @JsonProperty("state")
    private StateType state;

    @JsonProperty("type")
    @NotNull
    private TaskType type;

    @JsonProperty("document")
    private String document;

    @JsonProperty("costOfWork")
    private Double costOfWork;

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("endDate")
    private Date endDate;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("previousTasks")
    private Set<TaskAfterTaskGetDto> previousTasks;

    @JsonProperty("nextTasks")
    private Set<TaskAfterTaskGetDto> nextTasks;

    @JsonProperty("creator")
    private WorkerSlimGetDto creator;

    @JsonProperty("responsibleWorker")
    private WorkerSlimGetDto workerResponsibleId;

    @JsonProperty("performer")
    private WorkerSlimGetDto performer;

    @NotNull
    @JsonProperty("isTemplate")
    private boolean isTemplate;
}
