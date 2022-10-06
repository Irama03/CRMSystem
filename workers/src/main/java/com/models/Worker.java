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
public class Worker {

    @Id
    @SequenceGenerator(name = "WORKER_S", sequenceName = "WORKER_S", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "WORKER_S" )
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Embedded
    private PIB pib;

    @NotNull
    @Embedded
    private Contacts contacts;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "worker")
    private Set<WorkerOnPosition> positions = new HashSet<>();

    //info about tasks removed

    public Worker(PIB pib, Contacts contacts, Role role){
        this.pib = pib;
        this.contacts = contacts;
        this.role = role;
    }

    public Worker(Long id, PIB pib, Contacts contacts, Role role){
        this.id = id;
        this.pib = pib;
        this.contacts = contacts;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return getId().equals(worker.getId()) && getPib().equals(worker.getPib());
    }
}
