package com.TasksS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Task {

    @Id
    @SequenceGenerator(name = "TASK_S", sequenceName = "TASK_S", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TASK_S" )
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "flow_id")
    private Flow flow;

    @Column(length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private StateType state;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Column(length = 500)
    private String document;

    @Column
    private Double costOfWork;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @Column(length = 500)
    private String notes;

    @OneToMany(mappedBy = "nextTask")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TaskAfterTask> previousTasks = new HashSet<>();

    @OneToMany(mappedBy = "previousTask")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<TaskAfterTask> nextTasks = new HashSet<>();

    @Column
    private Long creatorId;

    @Column
    private Long responsibleWorkerId;

    @Column
    private Long performerId;

    @NotNull
    @Column(nullable = false)
    private boolean isTemplate;

    public Task(Long id, String name, Flow flow, String description, Date deadline,
                StateType state, TaskType type, String document, Double costOfWork,
                Date startDate, Date endDate, String notes, Set<TaskAfterTask> previousTasks,
                Long creatorId, Long responsibleWorkerId, Long performerId, boolean isTemplate) {
        this.id = id;
        this.name = name;
        this.flow = flow;
        this.description = description;
        this.deadline = deadline;
        this.state = state;
        this.type = type;
        this.document = document;
        this.costOfWork = costOfWork;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.previousTasks = previousTasks;
        this.creatorId = creatorId;
        this.responsibleWorkerId = responsibleWorkerId;
        if (performerId == null)
            this.performerId = responsibleWorkerId;
        else this.performerId = performerId;
        this.isTemplate = isTemplate;
    }

    public Task(String name, Flow flow, String description, Date deadline,
                StateType state, TaskType type, String document, Double costOfWork,
                Date startDate, Date endDate, String notes, Set<TaskAfterTask> previousTasks,
                Long creatorId, Long responsibleWorkerId, Long performerId, boolean isTemplate) {
        this.name = name;
        this.flow = flow;
        this.description = description;
        this.deadline = deadline;
        this.state = state;
        this.type = type;
        this.document = document;
        this.costOfWork = costOfWork;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.previousTasks = previousTasks;
        this.creatorId = creatorId;
        this.responsibleWorkerId = responsibleWorkerId;
        if (performerId == null)
            this.performerId = responsibleWorkerId;
        else this.performerId = performerId;
        this.isTemplate = isTemplate;
    }

    public void resetFieldsOfTemplate() {
        this.deadline = null;
        this.state = StateType.NEW;
        this.document = null;
        this.costOfWork = 0.0;
        this.startDate = null;
        this.endDate = null;
        this.creatorId = null;
        this.responsibleWorkerId = null;
        this.performerId = null;
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getId().equals(task.getId()) && getName().equals(task.getName())
                && getFlow().equals(task.getFlow());
    }
}
