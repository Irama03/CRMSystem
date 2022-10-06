package com.mapstruct.mappers;

import com.mapstruct.dtos.flow.FlowGetDto;
import com.mapstruct.dtos.flow.FlowPatchDto;
import com.mapstruct.dtos.flow.FlowPostDto;
import com.models.Flow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlowMapper {

    Iterable<FlowGetDto> flowsToFlowsGetDto(Iterable<Flow> flows);

    FlowGetDto flowToFlowGetDto(Flow flow);

    Flow flowPostDtoToFlow(FlowPostDto flowPostDto);

    Flow flowPatchDtoToFlow(FlowPatchDto flowPatchDto);

}
