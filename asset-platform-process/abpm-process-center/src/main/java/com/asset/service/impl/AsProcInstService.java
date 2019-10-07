package com.asset.service.impl;

import com.asset.entity.AsProcInstDO;
import com.asset.dao.AsProcInstMapper;
import com.asset.exception.ProcException;
import com.asset.javabean.AdminProcInstVO;
import com.asset.service.*;
import com.asset.utils.Constants;
import com.asset.utils.ProcDiagramGenerator;
import com.asset.utils.ProcUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  用于控制台展示、修改流程实例
 *  服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-04
 */
@Service
@AllArgsConstructor
public class AsProcInstService extends ServiceImpl<AsProcInstMapper, AsProcInstDO> implements IAsProcInstService {

    static Logger logger = LoggerFactory.getLogger(AsProcInstService.class);
    ProcInstService procInstService;
    FlowableService flowableService;
    AsProcInstMapper asProcInstMapper;
    FormModelService formModelService;
    ProcDiagramGenerator procDiagramGenerator;


    public List<AdminProcInstVO> listAdminProcInstInfo(QueryWrapper<AsProcInstDO> queryWrapper){
        List<AsProcInstDO> DOs = asProcInstMapper.selectList(queryWrapper);
        List<AdminProcInstVO> VOs = new ArrayList<>();

        for(int i=0;i<DOs.size();i++)
        {
            AdminProcInstVO vo = new AdminProcInstVO();
            BeanUtils.copyProperties(DOs.get(i),vo);

            //commitTime类型不一样，所以需要手动复制
            vo.setProcInstId(DOs.get(i).getId());
            vo.setCommitTime(DOs.get(i).getCommitTime().getTime());
            String procModelId = procInstService.getProcModelId(vo.getProcInstId());
            if(procModelId.equals(Constants.REGISTER_PROC_ID))
                vo.setProcInstName(Constants.REGISTER_PROC_NAME);
            else if(procModelId.equals(Constants.SCENE_SELECT_PROC_ID))
                vo.setProcInstName(Constants.SCENE_SELECT_PROC_NAME);
            else
                vo.setProcInstName(flowableService.getModelName(procModelId));

            if(ProcUtils.isFinished(vo.getProcInstId()))
                vo.setStatus(Constants.PROC_INST_FINISHED);
            else{
                vo.setStatus(DOs.get(i).getStatus());
            }
            vo.setBindFormModelId(formModelService.getFormModelId(procModelId));

            VOs.add(vo);
        }

        return VOs;
    }

    public void getProcDiagram(HttpServletResponse httpServletResponse, String procInstId) {
        InputStream in = procDiagramGenerator.genProcessDiagramInputStream(httpServletResponse, procInstId);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            httpServletResponse.setContentType("image/jpeg");
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("生成流程图操作异常{}",e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
        }
    }

    public void activateProcInst(String procInstId) throws Exception {
        //先判断流程实例状态
        if(procInstService.getStatus(procInstId) == Constants.PROC_INST_SUSPENDED)
        {
            ProcUtils.activateProcInst(procInstId);
            AsProcInstDO inst = new AsProcInstDO.Builder()
                    .id(procInstId)
                    .status(Constants.PROC_INST_ENABLE).build();
            asProcInstMapper.updateById(inst);
        }
        else
            throw new ProcException("流程实例："+procInstId+"已经处于激活状态！");
    }

    public void suspendProcInst(String procInstId) throws Exception {
        if(procInstService.getStatus(procInstId) == Constants.PROC_INST_ENABLE){
            ProcUtils.suspendProcInst(procInstId);
            AsProcInstDO inst = new AsProcInstDO.Builder()
                    .id(procInstId)
                    .status(Constants.PROC_INST_SUSPENDED).build();
            asProcInstMapper.updateById(inst);
        }
        else
            throw new ProcException("流程实例："+procInstId+"已经处于挂起状态！");
    }

    /**
     * 删除这个流程实例，通过还要在as_proc_inst表中把这个流程实例对应的status设为被删除
     * @param procInstId
     */
    public void deleteProcInst(String procInstId) {
        ProcUtils.deleteProcInst(procInstId);
        AsProcInstDO inst = new AsProcInstDO.Builder()
                .id(procInstId)
                .status(Constants.PROC_INST_DELETED).build();
        asProcInstMapper.updateById(inst);
    }

//


}
