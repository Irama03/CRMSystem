package com.TasksS.services.implementations;

import com.TasksS.models.gotModels.Worker;
import com.TasksS.services.interfaces.ExternalRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExternalRequestsServiceImpl implements ExternalRequestsService {

    @Autowired
    private Environment environment;

    @Override
    public Worker getWorkerById(Long id) {
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        ResponseEntity<Worker> responseEntity = new RestTemplate().getForEntity(
                environment.getProperty("workers-url") + "/workers/for_projects/{id}", Worker.class, uriVariables);
        return responseEntity.getBody();
    }

    @Override
    public Worker getHeadOfDepartment(Long departmentId) {
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("id", departmentId);
        ResponseEntity<Worker> responseEntity = new RestTemplate().getForEntity(
                environment.getProperty("workers-url") + "/workers/head_of_department/{id}", Worker.class, uriVariables);
        return responseEntity.getBody();
    }

}
