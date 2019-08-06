package com.asset.controller.user;

import com.asset.dao.FormAuthorityMapper;
import com.asset.entity.ActAuthority;
import com.asset.exception.DatabaseException;
import com.asset.javabean.RespBean;
import com.asset.dto.AuthorityItemDTO;
import com.asset.dto.AuthorityDTO;
import com.asset.dto.ProcModelDTO;
import com.asset.javabean.UnBindFormModelVO;
import com.asset.service.ProcModelService;
import com.asset.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YBY
 * @time 190720
 * @version 1.0_190720
 */
@RestController
@RequestMapping("/proc_model")
public class ProcModelController {

    @Autowired
    ProcModelService procModelService;
    @Autowired
    FormAuthorityMapper authorityMapper;

    /**
     * 保存功能性节点信息，这里不管是创建还是修改都用同一个接口
     * @param dto
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RespBean editAsProcModel(@RequestBody ProcModelDTO dto) {
        try {
            procModelService.saveProcModelInfo(dto);
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            return RespBean.error("插入数据失败！");
        }
        return RespBean.ok("");
    }

    /**
     * 对流程模型中每个节点对应的所有表单项权限信息进行存储
     */
    @RequestMapping(value = "/authority/save", method = RequestMethod.POST)
    public RespBean saveAuthority(@RequestBody AuthorityDTO authorityDTO) {
        if (StringUtils.isEmpty(authorityDTO.getProc_model_id()))
            return RespBean.error("流程模型数据缺失！");

        for (int i = 0; i < authorityDTO.getData().size(); i++) {
            AuthorityItemDTO cur = authorityDTO.getData().get(i);

            if (StringUtils.isEmpty(cur.getAct_id()) ||
                    StringUtils.isEmpty(cur.getForm_item_key()) ||
                    cur.getAuthority() == null)
                return RespBean.error("权限数据缺失！");

            ActAuthority actAuthority = new ActAuthority(authorityDTO.getProc_model_id(),
                    cur.getAct_id(),
                    cur.getForm_item_key(),
                    cur.getAuthority());
            int a = authorityMapper.insert(actAuthority);
            if (a == Constants.DATABASE_FAILED)
                return RespBean.error("插入权限数据失败！");
        }

        return RespBean.ok("");
    }

    /**
     * 流程模型与表单模型绑定分离，可以在流程模型设计阶段选择未绑定流程模型的表单模型
     * 这里是获取未绑定流程模型的表单模型
     */
    @GetMapping(value = "/unbind_form_model")
    public RespBean getUnbindFormModels(){
        List<UnBindFormModelVO> unbindFormModels = procModelService.getUnbindFormModels();

        return RespBean.ok("",unbindFormModels);
    }



}
