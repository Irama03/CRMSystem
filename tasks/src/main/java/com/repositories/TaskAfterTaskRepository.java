package com.repositories;

import com.models.Task;
import com.models.TaskAfterTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAfterTaskRepository extends JpaRepository<TaskAfterTask, Long> {

    @Query(value =
            "SELECT * " +
            "FROM task_after_task INNER JOIN task ON task_after_task.previous_task_id = task.id " +
            "WHERE flow_id = :flowId", nativeQuery = true)
    Iterable<TaskAfterTask> findAllTasksAfterTasksOfFlow(@Param("flowId") Long flowId);

    Iterable<TaskAfterTask> findTaskAfterTasksByPreviousTaskAndNextTask(Task previousTask, Task nextTask);
}
