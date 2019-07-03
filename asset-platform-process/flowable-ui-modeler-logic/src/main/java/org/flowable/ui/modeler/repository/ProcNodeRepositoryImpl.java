package org.flowable.ui.modeler.repository;

import org.flowable.ui.modeler.domain.ProcNodeInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lichao
 */
@Component
public class ProcNodeRepositoryImpl implements ProcNodeRepository {

    private static final String NAMESPACE = "org.flowable.ui.modeler.domain.ProcNodeInfo.";

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;


    @Override
    public void save(ProcNodeInfo procNodeInfo) {
        if(procNodeInfo.getId()==null) {
            sqlSessionTemplate.insert(NAMESPACE+ "insertProcNodeInfo",procNodeInfo);
        }else {
            sqlSessionTemplate.update(NAMESPACE +"updateProcNodeInfo",procNodeInfo);
        }
    }
}
