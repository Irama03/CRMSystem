package com.CRMSystem.controllers;

import com.CRMSystem.exceptions.workerOnPosition.NoCurrentPositionOfWorkerException;
import com.CRMSystem.exceptions.workerOnPosition.NoWorkerOnPositionWithSuchIdException;
import com.CRMSystem.mapstruct.dtos.workerOnPosition.WorkerOnPositionGetDto;
import com.CRMSystem.mapstruct.dtos.workerOnPosition.WorkerOnPositionPostDto;
import com.CRMSystem.mapstruct.dtos.workerOnPosition.WorkerOnPositionPutDto;
import com.CRMSystem.mapstruct.mappers.WorkerOnPositionMapper;
import com.CRMSystem.services.implementations.WorkerOnPositionServiceImpl;
import com.CRMSystem.utils.Utils;
import com.CRMSystem.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:" + Values.PORT_OF_FRONTEND})
@RestController
@Validated
@RequestMapping("/workers_on_positions")
public class WorkerOnPositionController {

    @Autowired
    private WorkerOnPositionServiceImpl workerOnPositionService;
    @Autowired
    private WorkerOnPositionMapper workerOnPositionMapper;

    @GetMapping("/{workerId}/{positionId}/{startDate}")
    public WorkerOnPositionGetDto getWorkerOnPosition(@PathVariable("workerId") Long workerId, @PathVariable("positionId") Long positionId, @PathVariable("startDate") Date startDate) {
        return workerOnPositionMapper.workerOnPositionToWorkerOnPositionGetDto(workerOnPositionService.getWorkerOnPositionById(workerId, positionId, startDate));
    }

    @GetMapping("/positions_of_worker/{id}")
    public Iterable<WorkerOnPositionGetDto> getHistoryOfPositionsOfWorker(@PathVariable("id") Long id){
        return workerOnPositionMapper.workersOnPositionsToWorkersOnPositionsGetDto(workerOnPositionService.getHistoryOfPositionsOfWorker(id));
    }

    @GetMapping("/current_position_of_worker/{id}")
    public WorkerOnPositionGetDto getCurrentPositionOfWorker(@PathVariable("id") Long id) {
        return workerOnPositionMapper.workerOnPositionToWorkerOnPositionGetDto(workerOnPositionService.getCurrentPositionOfWorker(id));
    }

    @PostMapping
    public WorkerOnPositionGetDto addWorkerOnPosition(@Valid @RequestBody WorkerOnPositionPostDto workerOnPositionPostDto){
        return workerOnPositionMapper.workerOnPositionToWorkerOnPositionGetDto(
                workerOnPositionService.addWorkerOnPosition(workerOnPositionPostDto));
    }

    @PutMapping("/{workerId}/{positionId}/{startDate}")
    public WorkerOnPositionGetDto updateEndDateOfWorkerOnPosition(@PathVariable("workerId") Long workerId, @PathVariable("positionId") Long positionId, @PathVariable("startDate") Date startDate, @Valid @RequestBody WorkerOnPositionPutDto workerOnPositionPutDto) {
        return workerOnPositionMapper.workerOnPositionToWorkerOnPositionGetDto(workerOnPositionService.updateEndDateOfWorkerOnPosition(workerId, positionId, startDate, workerOnPositionPutDto.getEndDate()));
    }

    @ExceptionHandler(NoWorkerOnPositionWithSuchIdException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoWorkerOnPositionWithSuchIdException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoCurrentPositionOfWorkerException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoCurrentPositionOfWorkerException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

}
