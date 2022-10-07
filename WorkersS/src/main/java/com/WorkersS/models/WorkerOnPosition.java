package com.WorkersS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "worker_on_position")
public class WorkerOnPosition {

    @EmbeddedId
    private WorkerOnPositionId id;

    @MapsId(value = "workerId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne
    private Worker worker;

    @MapsId(value = "positionId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne
    private Position position;

    //@MapsId(value = "startDate")
    //@Temporal(TemporalType.DATE)
    //@Column(name = "start_date")//, nullable = false)
    //private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    public WorkerOnPosition(Worker worker, Position position, Date endDate) {
        this.worker = worker;
        this.position = position;
        //this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkerOnPosition)) return false;
        WorkerOnPosition that = (WorkerOnPosition) o;
        return this.getWorker().equals(that.getWorker()) &&
                this.getPosition().equals(that.getPosition()) &&
                this.getId().getStartDate().equals(that.getId().getStartDate()) &&
                this.getEndDate().equals(that.getEndDate());
    }
}
