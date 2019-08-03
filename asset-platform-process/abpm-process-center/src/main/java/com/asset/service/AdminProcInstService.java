package com.asset.service;

import com.asset.entity.FormInstDO;
import com.asset.entity.ProcInstDO;
import com.asset.javabean.AdminProcInstVO;
import com.asset.javabean.AdminProcTaskVO;
import com.asset.utils.Constants;
import com.asset.utils.ProcDiagramGenerator;
import com.asset.utils.ProcUtils;
import com.github.pagehelper.Page;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    FormProcService formProcService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    FormInstService formInstService;

    public void getProcDiagram(HttpServletResponse httpServletResponse, String procInstId) {
        InputStream in = ProcDiagramGenerator.genProcessDiagramInputStream(httpServletResponse, procInstId);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } catch (IOException e) {
            logger.error("生成流程图操作异常{}",e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
        }
    }

    public void activateProcInst(String procInstId) {
        ProcUtils.activateProcInst(procInstId);
    }

    public void suspendProcInst(String procInstId) {
        ProcUtils.suspendProcInst(procInstId);
    }

    /**
     * 删除这个流程实例，通过还要在as_proc_inst表中把这个流程实例对应的status设为被删除
     * @param procInstId
     */
    public void deleteProcInst(String procInstId) {
        ProcUtils.deleteProcInst(procInstId);
        procInstService.deleteProcInstDO(procInstId);
    }


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
            vo.setBindFormModelId(formProcService.getBindFormModelId(procModelId));
            VOs.add(vo);
        }
        return VOs;
    }

    /**
     * 在控制台端获取流程任务节点信息
     * @return
     */
    public Page<AdminProcTaskVO> getProcTasks() {
        Page<FormInstDO> formInstDOs = (Page<FormInstDO>) formInstService.listFormInst();
        Page<AdminProcTaskVO> VOs = new Page<>();
        for(int i = 0;i<formInstDOs.size();i++)
        {
            AdminProcTaskVO vo = new AdminProcTaskVO();
            BeanUtils.copyProperties(formInstDOs.get(i),vo);

            //手动赋值
            vo.setFormInstId(formInstDOs.get(i).getId());
            if(formInstDOs.get(i).getExecuteTime()!=null)
                vo.setExecutorTime(formInstDOs.get(i).getExecuteTime().getTime());
            vo.setFormModelName(formModelService.getFormName(formInstDOs.get(i).getFormModelId()));

            VOs.add(vo);
        }
        return VOs;
    }
}
