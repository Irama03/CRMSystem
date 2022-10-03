package com.CRMSystem.mapstruct.mappers;
import com.CRMSystem.mapstruct.dtos.worker.WorkerGetDto;
import com.CRMSystem.mapstruct.dtos.worker.WorkerPostDto;
import com.CRMSystem.mapstruct.dtos.worker.WorkerPutDto;
import com.CRMSystem.mapstruct.dtos.worker.WorkerSlimGetDto;
import com.CRMSystem.models.Worker;
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
