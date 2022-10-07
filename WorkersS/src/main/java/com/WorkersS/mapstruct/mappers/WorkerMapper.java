package com.WorkersS.mapstruct.mappers;
import com.WorkersS.mapstruct.dtos.worker.WorkerGetDto;
import com.WorkersS.mapstruct.dtos.worker.WorkerPostDto;
import com.WorkersS.mapstruct.dtos.worker.WorkerPutDto;
import com.WorkersS.mapstruct.dtos.worker.WorkerSlimGetDto;
import com.WorkersS.models.Worker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

    WorkerGetDto workerToWorkerGetDto(Worker worker);

    Iterable<WorkerGetDto> workersToWorkersGetDto(Iterable<Worker> workers);

    WorkerSlimGetDto workerToWorkerSlimGetDto(Worker worker);

    Iterable<WorkerSlimGetDto> workersToWorkersSlimGetDto(Iterable<Worker> workers);

    Worker workerPutDtoToWorker(WorkerPutDto workerPutDto);

    Worker workerPostDtoToWorker(WorkerPostDto workerPostDto);

    Worker workerSlimGetDtoToWorker(WorkerSlimGetDto worker);
}
