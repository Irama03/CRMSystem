package com.CRMSystem.controllers;

import com.CRMSystem.exceptions.position.NoPositionWithSuchIdException;
import com.CRMSystem.exceptions.position.PositionIllegalArgumentException;
import com.CRMSystem.exceptions.position.UnableToDeletePositionException;
import com.CRMSystem.mapstruct.dtos.position.PositionGetDto;
import com.CRMSystem.mapstruct.dtos.position.PositionPostDto;
import com.CRMSystem.mapstruct.dtos.position.PositionPutDto;
import com.CRMSystem.mapstruct.mappers.PositionMapper;
import com.CRMSystem.models.Position;
import com.CRMSystem.services.implementations.PositionServiceImpl;
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
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionServiceImpl positionService;

    @Autowired
    private PositionMapper positionMapper;

    @GetMapping
    public Iterable<PositionGetDto> getPositions(){
        return positionMapper.positionsToPositionsGetDto(positionService.getAll());
    }

    @GetMapping("/{id}")
    public PositionGetDto getPosition(@PathVariable("id") Long id) {
        return positionMapper.positionToPositionGetDto(positionService.getPositionById(id));
    }

    @GetMapping("/of_department/{id}")
    public Iterable<PositionGetDto> getPositionsOfDepartment(@PathVariable("id") Long id){
        return positionMapper.positionsToPositionsGetDto(positionService.getPositionsOfDepartment(id));
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable("id") Long id) {
        positionService.deletePosition(id);
    }

    @PostMapping
    public PositionGetDto addPosition(@Valid @RequestBody PositionPostDto positionPostDto){
        return positionMapper.positionToPositionGetDto(positionService.addPosition(positionMapper.positionPostDtoToPosition(positionPostDto)));
    }

    @PutMapping("/{id}")
    public PositionGetDto updatePosition(@PathVariable("id") Long id, @Valid @RequestBody PositionPutDto positionPutDto) {
        Position position = positionMapper.positionPutDtoToPosition(positionPutDto);
        position.setId(id);
        return positionMapper.positionToPositionGetDto(positionService.updatePosition(position));
    }

    @ExceptionHandler(NoPositionWithSuchIdException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoPositionWithSuchIdException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PositionIllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(PositionIllegalArgumentException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnableToDeletePositionException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(UnableToDeletePositionException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

}

