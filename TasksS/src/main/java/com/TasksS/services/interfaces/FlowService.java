package com.TasksS.services.interfaces;

import com.TasksS.models.Flow;

public interface FlowService {

    Iterable<Flow> getAll();
    Flow getFlowById(Long id);

    Flow addFlow(Flow flow);
    Flow updateFlow(Flow flow);
    void deleteFlow(Long id);

    boolean flowExistsById(Long id);

    void failFlow(Long id);

}
