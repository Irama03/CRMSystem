package com.WorkersS.services.interfaces;

import com.WorkersS.mapstruct.dtos.workerOnPosition.WorkerOnPositionPostDto;
import com.WorkersS.models.WorkerOnPosition;

import java.util.Date;

public interface WorkerOnPositionService {

    WorkerOnPosition getWorkerOnPositionById(Long workerId, Long positionId, Date startDate);
    Iterable<WorkerOnPosition> getHistoryOfPositionsOfWorker(Long workerId);
    WorkerOnPosition getCurrentPositionOfWorker(Long workerId);

    WorkerOnPosition addWorkerOnPosition(WorkerOnPositionPostDto workerOnPositionPostDto);
    WorkerOnPosition updateEndDateOfWorkerOnPosition(Long workerId, Long positionId, Date startDate, Date endDate);
}
