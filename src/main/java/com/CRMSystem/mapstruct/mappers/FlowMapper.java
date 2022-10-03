package com.CRMSystem.mapstruct.mappers;

import com.CRMSystem.mapstruct.dtos.flow.FlowGetDto;
import com.CRMSystem.mapstruct.dtos.flow.FlowPatchDto;
import com.CRMSystem.mapstruct.dtos.flow.FlowPostDto;
import com.CRMSystem.models.Flow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlowMapper {

    Iterable<FlowGetDto> flowsToFlowsGetDto(Iterable<Flow> flows);

    FlowGetDto flowToFlowGetDto(Flow flow);

    Flow flowPostDtoToFlow(FlowPostDto flowPostDto);

    Flow flowPatchDtoToFlow(FlowPatchDto flowPatchDto);

}
