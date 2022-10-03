package com.CRMSystem.mapstruct.mappers;

import com.CRMSystem.mapstruct.dtos.position.PositionGetDto;
import com.CRMSystem.mapstruct.dtos.position.PositionPostDto;
import com.CRMSystem.mapstruct.dtos.position.PositionPutDto;
import com.CRMSystem.models.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionGetDto positionToPositionGetDto(Position position);

    Position positionGetDtoToPosition(PositionGetDto positionGetDto);

    Iterable<PositionGetDto> positionsToPositionsGetDto(Iterable<Position> positions);

    Position positionPutDtoToPosition(PositionPutDto positionPutDto);

    Position positionPostDtoToPosition(PositionPostDto positionPostDto);
}
