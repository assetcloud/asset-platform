package com.asset.controller;

import com.asset.dao.FormAuthorityMapper;
import com.asset.entity.ActAuthority;
import com.asset.exception.DatabaseException;
import com.asset.javabean.RespBean;
import com.asset.dto.AuthorityItemDTO;
import com.asset.dto.AuthorityDTO;
import com.asset.dto.ProcModelDTO;
import com.asset.service.ProcModelService;
import com.asset.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YBY
 * @time 190720
 * @version 1.0_190720
 */
@RestController
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
    @RequestMapping(value = "/proc_model/save", method = RequestMethod.POST)
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
    @RequestMapping(value = "/proc_model/authority/save", method = RequestMethod.POST)
    public RespBean saveAuthority(@RequestBody AuthorityDTO authorityDTO) {
        if (authorityDTO.getProc_model_id().isEmpty())
            return RespBean.error("流程模型数据缺失！");

        for (int i = 0; i < authorityDTO.getData().size(); i++) {
            AuthorityItemDTO cur = authorityDTO.getData().get(i);

            if (cur.getAct_id().isEmpty() ||
                    cur.getForm_item_key().isEmpty() ||
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


}
