package com.CRMSystem.repositories;

import com.CRMSystem.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value =
            "SELECT * " +
            "FROM task " +
            "ORDER by name",nativeQuery = true)
    Iterable<Task> getAll();

    @Query(value =
            "SELECT * " +
            "FROM task " +
            "WHERE flow_id = :flowId", nativeQuery = true)
    Iterable<Task> findAllTasksOfFlow(@Param("flowId") Long flowId);

    @Query(value =
            "SELECT * " +
            "FROM task " +
            "WHERE flow_id = :flowId " +
            "AND 0 = (SELECT COUNT(*) " +
                    "FROM task_after_task " +
                    "WHERE next_task_id = task.id)", nativeQuery = true)
    Optional<Task> getFirstTaskOfFlow(Long flowId);

    @Query(value =
            "SELECT * " +
            "FROM task " +
            "WHERE flow_id = :flowId " +
            "AND 0 = (SELECT COUNT(*) " +
                    "FROM task_after_task " +
                    "WHERE previous_task_id = task.id)", nativeQuery = true)
    Iterable<Task> getLastTasksOfFlow(Long flowId);

    @Query(value =
            "SELECT * " +
            "FROM task " +
            "WHERE performer_id = :workerId " +
            "OR worker_creator_id = :workerId " +
            "OR worker_responsible_id = :workerId " +
            "OR worker_responsible_id = :headId ", nativeQuery = true)
    Iterable<Task> getAllAllowedTasks(Long workerId, Long headId);

}
