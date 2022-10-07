package com.WorkersS.repositories;

import com.WorkersS.models.WorkerOnPosition;
import com.WorkersS.models.WorkerOnPositionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface WorkerOnPositionRepository extends JpaRepository<WorkerOnPosition, WorkerOnPositionId> {

    @Query(value =
            "SELECT * " +
            "FROM worker_on_position " +
            "WHERE worker_id = :workerId AND position_id = :positionId AND start_date = :startDate",nativeQuery = true)
    Optional<WorkerOnPosition> getWorkerOnPositionById(@Param("workerId") Long workerId, @Param("positionId") Long positionId, @Param("startDate") Date startDate);

    @Query(value =
            "SELECT * " +
            "FROM worker_on_position " +
            "WHERE worker_id = :workerId AND (end_date IS NULL OR end_date > CURRENT_DATE)",nativeQuery = true)
    Optional<WorkerOnPosition> findCurrentPositionOfWorker(@Param("workerId") Long workerId);

    @Query(value =
            "SELECT * " +
            "FROM worker_on_position " +
            "WHERE worker_id = :workerId " +
            "ORDER BY start_date DESC",nativeQuery = true)
    Iterable<WorkerOnPosition> findAllPositionsOfWorker(@Param("workerId") Long workerId);

}
