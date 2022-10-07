package com.WorkersS.services.interfaces;

import com.WorkersS.models.Department;
import com.WorkersS.models.Position;

public interface PositionService {

    Position addPosition(String name, boolean isHead, Department department);
    Position addPosition(Position position);

    boolean positionExistsById(Long id);

    boolean deletePosition(Long id); // throws PositionNotFoundException;
    Position updatePosition(Long id, String name, boolean isHead, Department department);
    Position updatePosition(Position position);
    Position updatePositionNoCheck(Position position);
    Position getPositionById(Long id); // throws PositionNotFoundException;

    Iterable<Position> getAll();

    Position getPositionByNameAndDepartment(String position, long departmentId);

    Iterable<Position> getPositionsOfDepartment(Long departmentId);
}
