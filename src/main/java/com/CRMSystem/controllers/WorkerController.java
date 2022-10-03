package com.CRMSystem.controllers;

import com.CRMSystem.exceptions.worker.NoWorkerWithSuchIdException;
import com.CRMSystem.exceptions.worker.WorkerIllegalArgumentException;
import com.CRMSystem.mapstruct.dtos.WorkerAndPositionPostDto;
import com.CRMSystem.mapstruct.dtos.worker.*;
import com.CRMSystem.mapstruct.mappers.PositionMapper;
import com.CRMSystem.mapstruct.mappers.WorkerMapper;
import com.CRMSystem.models.Worker;
import com.CRMSystem.models.WorkerOnPosition;
import com.CRMSystem.services.implementations.WorkerOnPositionServiceImpl;
import com.CRMSystem.services.implementations.WorkerServiceImpl;
import com.CRMSystem.utils.Utils;
import com.CRMSystem.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:" + Values.PORT_OF_FRONTEND})
@RestController
@Validated
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerServiceImpl workerService;

    @Autowired
    private WorkerOnPositionServiceImpl workerOnPositionService;

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private PositionMapper positionMapper;

    @GetMapping
    public Iterable<WorkerGetDto> getWorkers(){
        return workerMapper.workersToWorkersGetDto(workerService.getAll());
    }

    @GetMapping("/slim")
    public Iterable<WorkerSlimGetDto> getSlimWorkers() {
        return workerMapper.workersToWorkersSlimGetDto(
                workerService.getAll());
    }

    @GetMapping("/{id}")
    public WorkerGetDto getWorker(@PathVariable("id") Long id) {
        return workerMapper.workerToWorkerGetDto(workerService.getWorkerById(id));
    }

    @GetMapping("/for_projects/{id}")
    public WorkerForProjectsGetDto getWorkerForProjects(@PathVariable("id") Long id) {
        WorkerOnPosition currentPositionOfWorker = workerOnPositionService.getCurrentPositionOfWorker(id);
        return new WorkerForProjectsGetDto(currentPositionOfWorker.getWorker().getId(),
                currentPositionOfWorker.getPosition().getDepartment().getId(),
                currentPositionOfWorker.getPosition().isHead());
    }

    @GetMapping("/head_of_department/{id}")
    public WorkerGetDto getHeadOfDepartment(@PathVariable("id") Long id) {
        return workerMapper.workerToWorkerGetDto(workerService.getHeadOfDepartment(id));
    }

    @GetMapping("/of_department/{id}")
    public Iterable<WorkerGetDto> getWorkersOfDepartment(@PathVariable("id") Long id) {
        return workerMapper.workersToWorkersGetDto(workerService.getWorkersOfDepartment(id));
    }

    @PatchMapping("/fire/{id}")
    public void fireWorker(@PathVariable("id") Long id) {
        workerService.fireWorker(id);
    }

    @PostMapping
    public WorkerGetDto addWorker(@Valid @RequestBody WorkerAndPositionPostDto workerAndPositionPostDto) { //@Valid @RequestBody WorkerPostDto workerPostDto, @Valid @RequestBody PositionPutDto positionPutDto){
        return workerMapper.workerToWorkerGetDto(workerService.addWorker(workerMapper.workerPostDtoToWorker(workerAndPositionPostDto.getWorkerPostDto()), positionMapper.positionGetDtoToPosition(workerAndPositionPostDto.getPositionGetDto())));
    }

    @PutMapping("/{id}")
    public WorkerGetDto updateWorker(@PathVariable("id") Long id, @Valid @RequestBody WorkerPutDto workerPutDto) {
        Worker worker = workerMapper.workerPutDtoToWorker(workerPutDto);
        worker.setId(id);
        return workerMapper.workerToWorkerGetDto(workerService.updateWorker(worker));
    }

    @ExceptionHandler(NoWorkerWithSuchIdException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoWorkerWithSuchIdException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {WorkerIllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(WorkerIllegalArgumentException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

}
