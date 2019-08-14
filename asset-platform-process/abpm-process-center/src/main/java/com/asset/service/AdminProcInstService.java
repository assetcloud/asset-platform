package com.asset.service;

import com.asset.entity.ProcInstDO;
import com.asset.javabean.AdminProcInstVO;
import com.asset.utils.Constants;
import com.asset.utils.ProcUtils;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AdminProcInstService {

    static Logger logger = LoggerFactory.getLogger(AdminProcInstService.class);
    @Autowired
    ProcInstService procInstService;
    @Autowired
    FlowableService flowableService;

    @Autowired
    FormModelService formModelService;
    @Autowired
    FormInstService formInstService;



    /**
     * 在控制台端获取流程实例数据列表
     * @return
     */
    public Page<AdminProcInstVO> getProcInsts() {
        List<ProcInstDO> instDOPage = procInstService.listProcInsts();
//        PageInfo<ProcInstDO> list = new PageInfo<>(instDOPage);
        Page<AdminProcInstVO> VOs = new Page<>();
        for(int i = 0;i<instDOPage.size();i++) {
            AdminProcInstVO vo = new AdminProcInstVO();
            BeanUtils.copyProperties(instDOPage.get(i),vo);

            //commitTime类型不一样，所以需要手动复制
            vo.setCommitTime(instDOPage.get(i).getCommitTime().getTime());
            String procModelId = procInstService.getProcModelId(vo.getProcInstId());
            vo.setProcInstName(flowableService.getModelName(procModelId));
            if(ProcUtils.isFinished(vo.getProcInstId()))
                vo.setStatus(Constants.PROC_INST_FINISHED);
            else{
                vo.setStatus(instDOPage.get(i).getStatus());
            }
            vo.setBindFormModelId(formModelService.getFormModelId(procModelId));
            VOs.add(vo);
        }
        return VOs;
    }


}
