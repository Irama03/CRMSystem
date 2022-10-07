package com.TasksS.mapstruct.mappers;

import com.TasksS.mapstruct.dtos.flow.FlowGetDto;
import com.TasksS.mapstruct.dtos.flow.FlowPatchDto;
import com.TasksS.mapstruct.dtos.flow.FlowPostDto;
import com.TasksS.models.Flow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlowMapper {

    Iterable<FlowGetDto> flowsToFlowsGetDto(Iterable<Flow> flows);

    FlowGetDto flowToFlowGetDto(Flow flow);

    Flow flowPostDtoToFlow(FlowPostDto flowPostDto);

    Flow flowPatchDtoToFlow(FlowPatchDto flowPatchDto);

}
