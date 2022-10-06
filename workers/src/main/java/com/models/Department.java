package com.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Department {

    @Id
    @SequenceGenerator(name = "DEPARTMENT_S", sequenceName = "DEPARTMENT_S", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "DEPARTMENT_S" )
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    //description ?

    @OneToMany(mappedBy = "department")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Position> positions = new HashSet<>();

    public Department(String name) {
        this.name = name;
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department department = (Department) o;
        return getId().equals(department.getId()) && getName().equals(department.getName());
    }
}
