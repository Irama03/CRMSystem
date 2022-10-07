package com.WorkersS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Position {

    @Id
    @SequenceGenerator(name = "POSITION_S", sequenceName = "POSITION_S", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "POSITION_S" )
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 100)
    private String name;

    //description ?

    @NotNull
    @Column(nullable = false)
    private boolean isHead;

    // if it is main boss - department is null
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "position")
    private Set<WorkerOnPosition> workers = new HashSet<>();

    public Position(String name, boolean isHead, Department department) {
        this.name = name;
        this.isHead = isHead;
        this.department = department;
    }

    public Position(Long id, String name, boolean isHead, Department department) {
        this.id = id;
        this.name = name;
        this.isHead = isHead;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getId().equals(position.getId()) && getName().equals(position.getName()) &&
                isHead() == position.isHead() && getDepartment().equals(position.getDepartment());
    }
}
