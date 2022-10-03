package com.CRMSystem.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Flow {

    @Id
    @SequenceGenerator(name = "FLOW_S", sequenceName = "FLOW_S", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "FLOW_S" )
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private boolean isFailed = false;

    @NotNull
    @Column(nullable = false)
    private boolean isTemplate;

    @OneToMany(mappedBy = "flow")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Task> tasks = new HashSet<>();

    public Flow(Long id, String name, boolean isTemplate) {
        this.id = id;
        this.name = name;
        this.isTemplate = isTemplate;
    }

    public Flow(String name, boolean isTemplate) {
        this.name = name;
        this.isTemplate = isTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flow)) return false;
        Flow flow = (Flow) o;
        return getId().equals(flow.getId()) && getName().equals(flow.getName())
                && isFailed() == flow.isFailed() && isTemplate() == flow.isTemplate();
    }

}
