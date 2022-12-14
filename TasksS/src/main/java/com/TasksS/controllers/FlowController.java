package com.TasksS.controllers;

import com.TasksS.exceptions.flow.FlowIllegalArgumentException;
import com.TasksS.exceptions.flow.NoFlowWithSuchIdException;
import com.TasksS.mapstruct.dtos.flow.FlowGetDto;
import com.TasksS.mapstruct.dtos.flow.FlowPatchDto;
import com.TasksS.mapstruct.dtos.flow.FlowPostDto;
import com.TasksS.mapstruct.mappers.FlowMapper;
import com.TasksS.models.Flow;
import com.TasksS.services.implementations.FlowServiceImpl;
import com.TasksS.utils.Utils;
import com.TasksS.utils.Values;
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
@RequestMapping("/flows")
public class FlowController {

    @Autowired
    FlowServiceImpl flowService;
    @Autowired
    FlowMapper flowMapper;

    @GetMapping()
    public Iterable<FlowGetDto> getFlows(){
        return flowMapper.flowsToFlowsGetDto(flowService.getAll());
    }

    @GetMapping("/{id}")
    public FlowGetDto getFlow(@PathVariable("id") Long id) {
        return flowMapper.flowToFlowGetDto(flowService.getFlowById(id));
    }

    @PostMapping
    public FlowGetDto addFlow(@Valid @RequestBody FlowPostDto flowPostDto){
        return flowMapper.flowToFlowGetDto(flowService.addFlow(flowMapper.flowPostDtoToFlow(flowPostDto)));
    }

    @PatchMapping("/{id}")
    public FlowGetDto updateFlow(@PathVariable("id") Long id, @Valid @RequestBody FlowPatchDto flowPatchDto) {
        Flow flow = flowMapper.flowPatchDtoToFlow(flowPatchDto);
        flow.setId(id);
        return flowMapper.flowToFlowGetDto(flowService.updateFlow(flow));
    }

    @DeleteMapping("/{id}")
    public void deleteFlow(@PathVariable("id") Long id) {
        flowService.deleteFlow(id);
    }

    @PatchMapping("/{id}/fail")
    public void failFlow(@PathVariable("id") Long id) {
        flowService.failFlow(id);
    }

    @ExceptionHandler(NoFlowWithSuchIdException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String,String>> handleException(NoFlowWithSuchIdException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {FlowIllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String,String>> handleException(FlowIllegalArgumentException e){
        return new ResponseEntity<>(Utils.getExceptionResponse(e),HttpStatus.BAD_REQUEST);
    }

}
