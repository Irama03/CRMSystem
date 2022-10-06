package com.services.implementations;

import com.exceptions.workerOnPosition.NoCurrentPositionOfWorkerException;
import com.exceptions.workerOnPosition.NoWorkerOnPositionWithSuchIdException;
import com.mapstruct.dtos.workerOnPosition.WorkerOnPositionPostDto;
import com.mapstruct.mappers.PositionMapper;
import com.mapstruct.mappers.WorkerMapper;
import com.models.*;
import com.repositories.WorkerOnPositionRepository;
import com.services.interfaces.WorkerOnPositionService;
import com.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkerOnPositionServiceImpl implements WorkerOnPositionService {

    @Autowired
    private WorkerOnPositionRepository workerOnPositionRepository;
    @Autowired
    private WorkerServiceImpl workerService;
    @Autowired
    private Utils utils;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public WorkerOnPosition getWorkerOnPositionById(Long workerId, Long positionId, Date startDate) {
        return workerOnPositionRepository.getWorkerOnPositionById(workerId, positionId, startDate).orElseThrow(() -> new NoWorkerOnPositionWithSuchIdException(workerId, positionId, startDate));
    }

    @Override
    public Iterable<WorkerOnPosition> getHistoryOfPositionsOfWorker(Long workerId) {
        return workerOnPositionRepository.findAllPositionsOfWorker(workerId);
    }

    @Override
    public WorkerOnPosition getCurrentPositionOfWorker(Long workerId) {
        return workerOnPositionRepository.findCurrentPositionOfWorker(workerId).orElseThrow(() -> new NoCurrentPositionOfWorkerException(workerId));
    }

    @Override
    public WorkerOnPosition addWorkerOnPosition(WorkerOnPositionPostDto workerOnPositionPostDto) {
        utils.checkDates(workerOnPositionPostDto.getStartDate(), workerOnPositionPostDto.getEndDate());

        Worker worker = workerMapper.workerSlimGetDtoToWorker(workerOnPositionPostDto.getWorker());
        worker = workerService.getWorkerById(worker.getId());
        Position position = positionMapper.positionGetDtoToPosition(workerOnPositionPostDto.getPosition());
        WorkerOnPosition workerOnPosition = new WorkerOnPosition(worker, position, workerOnPositionPostDto.getEndDate());

        WorkerOnPositionId workerOnPositionId = new WorkerOnPositionId();
        workerOnPositionId.setWorkerId(worker.getId());
        workerOnPositionId.setPositionId(position.getId());
        workerOnPositionId.setStartDate(workerOnPositionPostDto.getStartDate());
        workerOnPosition.setId(workerOnPositionId);

        Date now = utils.getCurrentDate();
        Date wOPEndDate = workerOnPosition.getEndDate();
        if (wOPEndDate == null || wOPEndDate.after(now)) {
            if (worker.getRole() != Role.ADMIN) {
                if (workerOnPosition.getPosition().isHead()) {
                    worker.setRole(Role.HEAD_OF_DEPARTMENT);
                    workerService.changeHead(workerOnPosition.getPosition(), worker);
                }
                else worker.setRole(Role.WORKER);
                workerService.updateWorkerNoCheck(worker);
            }
            try {
                WorkerOnPosition currentPosition = getCurrentPositionOfWorker(worker.getId());
                currentPosition.setEndDate(now);
                workerOnPositionRepository.save(currentPosition);
            } catch(NoCurrentPositionOfWorkerException ignored) {}
        }

        worker.getPositions().add(workerOnPosition);
        return workerOnPositionRepository.save(workerOnPosition);
    }

    @Override
    public WorkerOnPosition updateEndDateOfWorkerOnPosition(Long workerId, Long positionId, Date startDate, Date endDate) {
        utils.checkDates(startDate, endDate);

        WorkerOnPosition workerOnPosition = workerOnPositionRepository.getWorkerOnPositionById(workerId, positionId, startDate).orElseThrow(() -> new NoWorkerOnPositionWithSuchIdException(workerId, positionId, startDate));

        if (endDate.equals(workerOnPosition.getEndDate()))
            return workerOnPosition;
        workerOnPosition.setEndDate(endDate);
        Date now = utils.getCurrentDate();
        if (endDate.equals(now) || endDate.before(now)) {
            Worker worker = workerOnPosition.getWorker();
            worker.setRole(Role.FIRED);
            workerService.updateWorkerNoCheck(worker);
        }
        return workerOnPositionRepository.save(workerOnPosition);
    }

}
