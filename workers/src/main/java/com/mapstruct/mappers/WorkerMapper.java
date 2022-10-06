package com.mapstruct.mappers;
import com.mapstruct.dtos.worker.WorkerGetDto;
import com.mapstruct.dtos.worker.WorkerPostDto;
import com.mapstruct.dtos.worker.WorkerPutDto;
import com.mapstruct.dtos.worker.WorkerSlimGetDto;
import com.models.Worker;
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
