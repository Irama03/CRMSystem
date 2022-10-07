package com.WorkersS.mapstruct.mappers;

import com.WorkersS.mapstruct.dtos.workerOnPosition.WorkerOnPositionPutDto;
import com.WorkersS.mapstruct.dtos.workerOnPosition.WorkerOnPositionGetDto;
import com.WorkersS.models.WorkerOnPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerOnPositionMapper {

    WorkerOnPositionGetDto workerOnPositionToWorkerOnPositionGetDto(WorkerOnPosition workerOnPosition);

    Iterable<WorkerOnPositionGetDto> workersOnPositionsToWorkersOnPositionsGetDto(Iterable<WorkerOnPosition> workersOnPositions);

    WorkerOnPosition workerOnPositionPutDtoToWorkerOnPosition(WorkerOnPositionPutDto workerOnPositionPutDto);

    //WorkerOnPosition workerOnPositionPostDtoToWorkerOnPosition(WorkerOnPositionPostDto workerOnPositionPostDto);
}
