package com.mapstruct.mappers;

import com.mapstruct.dtos.workerOnPosition.WorkerOnPositionPutDto;
import com.mapstruct.dtos.workerOnPosition.WorkerOnPositionGetDto;
import com.models.WorkerOnPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerOnPositionMapper {

    WorkerOnPositionGetDto workerOnPositionToWorkerOnPositionGetDto(WorkerOnPosition workerOnPosition);

    Iterable<WorkerOnPositionGetDto> workersOnPositionsToWorkersOnPositionsGetDto(Iterable<WorkerOnPosition> workersOnPositions);

    WorkerOnPosition workerOnPositionPutDtoToWorkerOnPosition(WorkerOnPositionPutDto workerOnPositionPutDto);

    //WorkerOnPosition workerOnPositionPostDtoToWorkerOnPosition(WorkerOnPositionPostDto workerOnPositionPostDto);
}
