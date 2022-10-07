package com.TasksS.services.interfaces;


import com.TasksS.models.gotModels.Worker;

public interface ExternalRequestsService {

    Worker getWorkerById(Long id);
    Worker getHeadOfDepartment(Long departmentId);

}
