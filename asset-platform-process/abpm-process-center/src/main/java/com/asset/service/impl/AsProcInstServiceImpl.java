package com.asset.service.impl;

import com.asset.entity.AsProcInst;
import com.asset.dao.AsProcInstMapper;
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
public class AsProcInstServiceImpl extends ServiceImpl<AsProcInstMapper, AsProcInst> implements IAsProcInstService {

    static Logger logger = LoggerFactory.getLogger(AsProcInstServiceImpl.class);
    ProcInstService procInstService;
    FlowableService flowableService;
    AsProcInstMapper asProcInstMapper;
    FormModelService formModelService;


    public List<AdminProcInstVO> listAdminProcInstInfo(QueryWrapper<AsProcInst> queryWrapper){
        List<AsProcInst> DOs = asProcInstMapper.selectList(queryWrapper);
        List<AdminProcInstVO> VOs = new ArrayList<>();

        for(int i=0;i<DOs.size();i++)
        {
            AdminProcInstVO vo = new AdminProcInstVO();
            BeanUtils.copyProperties(DOs.get(i),vo);

            //commitTime类型不一样，所以需要手动复制
            vo.setCommitTime(DOs.get(i).getCommitTime().getTime());
            String procModelId = procInstService.getProcModelId(vo.getProcInstId());
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

//


}
