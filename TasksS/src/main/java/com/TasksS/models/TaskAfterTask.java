package com.TasksS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class TaskAfterTask {

    @Id
    @SequenceGenerator(name = "TASK_AFTER_TASK_S", sequenceName = "TASK_AFTER_TASK_S", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TASK_AFTER_TASK_S" )
    @Column(updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "previous_task_id")
    private Task previousTask;

    @ManyToOne
    @JoinColumn(name = "next_task_id")
    private Task nextTask;

    public TaskAfterTask(Long id, Task previousTask, Task nextTask) {
        this.id = id;
        this.previousTask = previousTask;
        this.nextTask = nextTask;
    }

    public TaskAfterTask(Task previousTask, Task nextTask) {
        this.previousTask = previousTask;
        this.nextTask = nextTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskAfterTask)) return false;
        TaskAfterTask taskAfterTask = (TaskAfterTask) o;
        return getId().equals(taskAfterTask.getId()) && getPreviousTask().equals(taskAfterTask.getPreviousTask())
                && getNextTask().equals(taskAfterTask.getNextTask());
    }
}
