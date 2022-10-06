package com.services.implementations;

import com.exceptions.position.NoPositionWithSuchIdException;
import com.exceptions.worker.NoWorkerWithSuchIdException;
import com.exceptions.worker.WorkerIllegalArgumentException;
import com.models.*;
import com.repositories.PositionRepository;
import com.repositories.WorkerOnPositionRepository;
import com.repositories.WorkerRepository;
import com.services.interfaces.WorkerService;
import com.utils.Utils;
import com.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private WorkerOnPositionRepository workerOnPositionRepository;
    @Autowired
    private Utils utils;

    @Override
    public Worker addWorker(PIB pib, Contacts contacts, Role role, Position position) {
        Iterable<Worker> workerWithSuchEmail = workerRepository.findByEmail(contacts.getEmail());
        if (workerWithSuchEmail.iterator().hasNext())
            throw new WorkerIllegalArgumentException(Values.WORKER_WITH_SUCH_EMAIL_ALREADY_EXISTS);

        Worker worker = new Worker(pib, contacts, role);
        utils.processWorker(worker);
        utils.checkPib(worker.getPib());
        utils.checkContacts(worker.getContacts());
        worker = workerRepository.save(worker);
        long posId = position.getId();
        position = positionRepository.findById(posId).orElseThrow(() -> new NoPositionWithSuchIdException(posId));
        WorkerOnPosition workerOnPosition = new WorkerOnPosition(worker, position, null);

        WorkerOnPositionId workerOnPositionId = new WorkerOnPositionId();
        workerOnPositionId.setWorkerId(worker.getId());
        workerOnPositionId.setPositionId(position.getId());
        workerOnPositionId.setStartDate(utils.getCurrentDate());

        workerOnPosition.setId(workerOnPositionId);
        //workerOnPosition = workerOnPositionRepository.save(workerOnPosition);
        //workerOnPosition.getId().setStartDate(utils.getCurrentDate());
        workerOnPosition = workerOnPositionRepository.save(workerOnPosition);
        worker.getPositions().add(workerOnPosition);
        if (position.isHead())
            changeHead(position, worker);
        return worker;
    }

    @Override
    public Worker addWorker(Worker worker, Position position) {
        worker.setId(-1L);
        return addWorker(worker.getPib(), worker.getContacts(), worker.getRole(), position);
    }

    @Override
    public boolean workerExistsById(Long id) {
        return workerRepository.existsById(id);
    }

    @Override
    public boolean fireWorker(Long id) {
        //workerRepository.deleteById(id);
        workerRepository.findById(id).map((worker) -> {
            Date now = utils.getCurrentDate();
            for (WorkerOnPosition p: worker.getPositions()) {
                if (p.getEndDate() == null || p.getEndDate().after(now)) {
                    p.setEndDate(now);
                    workerOnPositionRepository.save(p);
                    break;
                }
            }
            worker.setRole(Role.FIRED);
            workerRepository.save(worker);
            return true;
        }).orElseThrow(() -> new NoWorkerWithSuchIdException(id));
        return true;
    }

    @Override
    public Worker updateWorker(Long id, PIB pib, Contacts contacts, Role role) {
        Iterable<Worker> workerWithSuchEmail = workerRepository.findByEmailAndNotId(id, contacts.getEmail());
        if (workerWithSuchEmail.iterator().hasNext())
            throw new WorkerIllegalArgumentException(Values.WORKER_WITH_SUCH_EMAIL_ALREADY_EXISTS);

        pib = utils.processPib(pib);
        utils.checkPib(pib);
        contacts = utils.processContacts(contacts);
        utils.checkContacts(contacts);

        Worker worker = workerRepository.findById(id).orElseThrow(() -> new NoWorkerWithSuchIdException(id));
        if (nothingChanged(worker, pib, contacts, role))
            return worker;
        worker.setPib(pib);
        worker.setContacts(contacts);
        worker.setRole(role);
            /*if (role == Role.HEAD_OF_DEPARTMENT) {
                department.setHead(worker);
                departmentRepository.save(department);
                changeHead(department.getId(), worker);
            }*/
        return workerRepository.save(worker);
    }

    private boolean nothingChanged(Worker worker, PIB pib, Contacts contacts, Role role) {
        return worker.getPib().equals(pib) && worker.getContacts().equals(contacts)
                && worker.getRole().equals(role);
    }

    @Override
    public Worker updateWorker(Worker worker) {
        return updateWorker(worker.getId(), worker.getPib(), worker.getContacts(), worker.getRole());
    }

    @Override
    public Worker updateWorkerNoCheck(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElseThrow(() -> new NoWorkerWithSuchIdException(id));
    }

    @Override
    public Iterable<Worker> getAll() {
        return workerRepository.getAll();
    }

    @Override
    public void changeHead(Position headPosition, Worker newHead) {
        Iterable<Worker> pHead = workerRepository.findPrevWorkerOnPosition(headPosition.getId(), newHead.getId());
        if (pHead.iterator().hasNext()) {
            Worker prevHead = pHead.iterator().next();
            //TODO: Think about position of previous head of department
            prevHead.setRole(Role.WORKER);
            //prevHead.setPosition(???)
            updateWorkerNoCheck(prevHead);
        }
    }

    @Override
    public Worker getHeadOfDepartment(Long departmentId) {
        return workerRepository.getHeadOfDepartment(departmentId);
    }

    @Override
    public Iterable<Worker> getWorkersOfDepartment(Long departmentId) {
        return workerRepository.getWorkersOfDepartment(departmentId);
    }
}
