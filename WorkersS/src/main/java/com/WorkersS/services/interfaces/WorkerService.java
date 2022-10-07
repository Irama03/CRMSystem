package com.WorkersS.services.interfaces;

import com.WorkersS.models.*;

public interface WorkerService {

    Worker addWorker(PIB pib, Contacts contacts, Role role, Position position);
    Worker addWorker(Worker worker, Position position);

    boolean workerExistsById(Long id);

    boolean fireWorker(Long id); // throws WorkerNotFoundException;
    Worker updateWorker(Long id, PIB pib, Contacts contacts, Role role);
    Worker updateWorker(Worker worker);
    Worker updateWorkerNoCheck(Worker worker);
    Worker getWorkerById(Long id); // throws WorkerNotFoundException;

    Iterable<Worker> getAll();

    void changeHead(Position headPosition, Worker newHead);

    Worker getHeadOfDepartment(Long departmentId);
    Iterable<Worker> getWorkersOfDepartment(Long departmentId);
}
