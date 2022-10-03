package com.CRMSystem.services.implementations;

import com.CRMSystem.exceptions.flow.FlowIllegalArgumentException;
import com.CRMSystem.exceptions.flow.NoFlowWithSuchIdException;
import com.CRMSystem.models.Flow;
import com.CRMSystem.repositories.FlowRepository;
import com.CRMSystem.services.interfaces.FlowService;
import com.CRMSystem.utils.Utils;
import com.CRMSystem.utils.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowRepository flowRepository;
    @Autowired
    private Utils utils;

    @Override
    public Iterable<Flow> getAll() {
        return flowRepository.getAll();
    }

    @Override
    public Flow getFlowById(Long id) {
        return flowRepository.findById(id).orElseThrow(() -> new NoFlowWithSuchIdException(id));
    }

    @Override
    public Flow addFlow(Flow flow) {
        flow.setId(-1L);
        String name = utils.processString(flow.getName());
        utils.checkName(name);
        Iterable<Flow> flowsWithSuchName = flowRepository.findByName(name);
        if (flowsWithSuchName.iterator().hasNext())
            throw new FlowIllegalArgumentException(Values.FLOW_WITH_SUCH_NAME_ALREADY_EXISTS);

        flow.setName(name);
        return flowRepository.save(flow);
    }

    @Override
    public Flow updateFlow(Flow flow) {
        Long id = flow.getId();
        String name = utils.processString(flow.getName());
        utils.checkName(name);

        Iterable<Flow> flowsWithSuchName = flowRepository.findByNameAndNotId(id, name);
        if (flowsWithSuchName.iterator().hasNext())
            throw new FlowIllegalArgumentException(Values.FLOW_WITH_SUCH_NAME_ALREADY_EXISTS);

        Flow f = flowRepository.findById(id).orElseThrow(() -> new NoFlowWithSuchIdException(id));
        f.setName(name);
        return flowRepository.save(f);
    }

    @Override
    public void deleteFlow(Long id) {
        if(!flowExistsById(id))
            throw new NoFlowWithSuchIdException(id);
        flowRepository.deleteById(id);
    }

    @Override
    public boolean flowExistsById(Long id) {
        return flowRepository.existsById(id);
    }

    @Override
    public void failFlow(Long id) {
        Flow flow = getFlowById(id);
        flow.setFailed(true);
        flowRepository.save(flow);
    }

}
