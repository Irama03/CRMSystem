package com.CRMSystem.services.implementations;

import com.CRMSystem.exceptions.position.NoPositionWithSuchIdException;
import com.CRMSystem.exceptions.position.PositionIllegalArgumentException;
import com.CRMSystem.exceptions.position.UnableToDeletePositionException;
import com.CRMSystem.models.Department;
import com.CRMSystem.models.Position;
import com.CRMSystem.repositories.PositionRepository;
import com.CRMSystem.services.interfaces.PositionService;
import com.CRMSystem.utils.Utils;
import com.CRMSystem.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private Utils utils;

    @Override
    public Position addPosition(String name, boolean isHead, Department department) {
        name = utils.processString(name);
        utils.checkName(name);
        Iterable<Position> positionWithSuchNameInSameDepartment = positionRepository.findByNameAndDepartment(name, department.getId());
        if (positionWithSuchNameInSameDepartment.iterator().hasNext())
            throw new PositionIllegalArgumentException(Values.POSITION_WITH_SUCH_NAME_ALREADY_EXISTS);
        if (isHead) {
            for (Position pos: getAll()) {
                if (pos.isHead() && pos.getDepartment().equals(department))
                    throw new PositionIllegalArgumentException(Values.HEAD_POSITION_ALREADY_EXISTS_IN_THIS_DEPARTMENT);
            }
        }
        return positionRepository.save(new Position(name, isHead, department));
    }

    @Override
    public Position addPosition(Position position) {
        position.setId(-1L);
        return addPosition(position.getName(), position.isHead(), position.getDepartment());
    }

    @Override
    public boolean positionExistsById(Long id) {
        return positionRepository.existsById(id);
    }

    @Override
    public boolean deletePosition(Long id) {
        if(!positionExistsById(id)) throw new NoPositionWithSuchIdException(id);
        try {
            positionRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new UnableToDeletePositionException();
        }
        return true;
    }

    @Override
    public Position updatePosition(Long id, String name, boolean isHead, Department department) {
        name = utils.processString(name);
        utils.checkName(name);

        Iterable<Position> positionsWithSuchName = positionRepository.findByNameAndDepartmentAndNotId(id, department.getId(), name);
        if (positionsWithSuchName.iterator().hasNext())
            throw new PositionIllegalArgumentException(Values.POSITION_WITH_SUCH_NAME_ALREADY_EXISTS);
        if (isHead) {
            for (Position pos: getAll()) {
                if (!pos.getId().equals(id) && pos.isHead() && pos.getDepartment().equals(department))
                    throw new PositionIllegalArgumentException(Values.HEAD_POSITION_ALREADY_EXISTS_IN_THIS_DEPARTMENT);
            }
        }

        Position position = positionRepository.findById(id).orElseThrow(() -> new NoPositionWithSuchIdException(id));
        if (nothingChanged(position, name, isHead, department))
            return position;
        position.setName(name);
        position.setHead(isHead);
        position.setDepartment(department);
        return positionRepository.save(position);
    }

    private boolean nothingChanged(Position position, String name, boolean isHead, Department department) {
        return position.getName().equals(name) && position.isHead() == isHead && position.getDepartment().getId().equals(department.getId());
    }

    @Override
    public Position updatePosition(Position position) {
        return updatePosition(position.getId(), position.getName(), position.isHead(), position.getDepartment());
    }

    @Override
    public Position updatePositionNoCheck(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElseThrow(() -> new NoPositionWithSuchIdException(id));
    }

    @Override
    public Iterable<Position> getAll() {
        return positionRepository.getAll();
    }

    @Override
    public Position getPositionByNameAndDepartment(String position, long departmentId) {
        Iterable<Position> positions = positionRepository.findByNameAndDepartment(position, departmentId);
        return positions.iterator().next();
    }

    @Override
    public Iterable<Position> getPositionsOfDepartment(Long departmentId) {
        return positionRepository.getPositionsOfDepartment(departmentId);
    }
}
