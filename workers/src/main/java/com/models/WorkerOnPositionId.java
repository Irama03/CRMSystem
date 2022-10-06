package com.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class WorkerOnPositionId implements Serializable {

    private Long workerId;

    private Long positionId;
    /*@ManyToOne
    @JoinColumn(name="worker_id")
    private Worker workerId;

    @ManyToOne
    @JoinColumn(name="position_id")
    private Position positionId;*/

    @Temporal(TemporalType.DATE)
    private Date startDate = new Date();

    /*public WorkerOnPositionId(Long workerId, Long positionId, Date startDate) {
        this.workerId = workerId;
        this.positionId = positionId;
        this.startDate = startDate;
    }*/

    /*public WorkerOnPositionId(Worker workerId, Position positionId, Date startDate) {
        this.workerId = workerId;
        this.positionId = positionId;
        this.startDate = startDate;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkerOnPositionId)) return false;
        WorkerOnPositionId that = (WorkerOnPositionId) o;
        return this.getWorkerId().equals(that.getWorkerId()) &&
                this.getPositionId().equals(that.getPositionId()) &&
                this.getStartDate().equals(that.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getWorkerId(), this.getPositionId(), this.getStartDate());
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}

