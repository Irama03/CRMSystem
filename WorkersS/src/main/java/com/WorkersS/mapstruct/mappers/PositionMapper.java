package com.WorkersS.mapstruct.mappers;

import com.WorkersS.mapstruct.dtos.position.PositionGetDto;
import com.WorkersS.mapstruct.dtos.position.PositionPostDto;
import com.WorkersS.mapstruct.dtos.position.PositionPutDto;
import com.WorkersS.models.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionGetDto positionToPositionGetDto(Position position);

    Position positionGetDtoToPosition(PositionGetDto positionGetDto);

    Iterable<PositionGetDto> positionsToPositionsGetDto(Iterable<Position> positions);

    Position positionPutDtoToPosition(PositionPutDto positionPutDto);

    Position positionPostDtoToPosition(PositionPostDto positionPostDto);
}
