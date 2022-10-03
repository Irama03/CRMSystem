package com.CRMSystem.mapstruct.mappers;

import com.CRMSystem.mapstruct.dtos.workerOnPosition.WorkerOnPositionGetDto;
import com.CRMSystem.mapstruct.dtos.workerOnPosition.WorkerOnPositionPutDto;
import com.CRMSystem.models.WorkerOnPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerOnPositionMapper {

    WorkerOnPositionGetDto workerOnPositionToWorkerOnPositionGetDto(WorkerOnPosition workerOnPosition);

    Iterable<WorkerOnPositionGetDto> workersOnPositionsToWorkersOnPositionsGetDto(Iterable<WorkerOnPosition> workersOnPositions);

    WorkerOnPosition workerOnPositionPutDtoToWorkerOnPosition(WorkerOnPositionPutDto workerOnPositionPutDto);

    //WorkerOnPosition workerOnPositionPostDtoToWorkerOnPosition(WorkerOnPositionPostDto workerOnPositionPostDto);
}
