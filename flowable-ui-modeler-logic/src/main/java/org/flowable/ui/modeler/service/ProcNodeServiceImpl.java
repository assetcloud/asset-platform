package org.flowable.ui.modeler.service;

import org.flowable.ui.modeler.domain.ProcNodeInfo;
import org.flowable.ui.modeler.repository.ProcNodeRepository;
import org.flowable.ui.modeler.serviceapi.ProcNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lichao
 */
@Service
public class ProcNodeServiceImpl implements ProcNodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcNodeServiceImpl.class);

    @Autowired
    ProcNodeRepository procNodeRepository;


    @Override
    public void save(ProcNodeInfo procNodeInfo) {
        procNodeRepository.save(procNodeInfo);
    }
}
