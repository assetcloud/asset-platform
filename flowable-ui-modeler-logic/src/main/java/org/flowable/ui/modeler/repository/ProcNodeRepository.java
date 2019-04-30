package org.flowable.ui.modeler.repository;

import org.flowable.ui.modeler.domain.ProcNodeInfo;

/**
 * @author lichao
 */
public interface ProcNodeRepository {

    void save(ProcNodeInfo procNodeInfo);
}
