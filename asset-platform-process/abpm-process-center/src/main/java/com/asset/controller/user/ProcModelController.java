package com.asset.controller.user;

import com.asset.dao.AsProcModelMapper;
import com.asset.dao.FormAuthorityMapper;
import com.asset.entity.ActAuthority;
import com.asset.entity.AsProcModel;
import com.asset.exception.DatabaseException;
import com.asset.javabean.RespBean;
import com.asset.dto.AuthorityItemDTO;
import com.asset.dto.AuthorityDTO;
import com.asset.dto.ProcModelDTO;
import com.asset.javabean.UnBindFormModelVO;
import com.asset.service.AuthorityService;
import com.asset.service.ProcNodeService;
import com.asset.service.impl.AsProcModelServiceImpl;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 负责处理流程模型所有内容的请求
 *
 * @author YBY
 * @version 1.0_190720
 * @time 190720
 */
@RestController
@RequestMapping("/proc_model")
@Api(value = "流程模型与流程模型中节点管理", tags = "用户端")
public class ProcModelController extends ServiceImpl<AsProcModelMapper, AsProcModel> {

    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    FormAuthorityMapper authorityMapper;
    @Autowired
    AsProcModelServiceImpl procModelService;
    @Autowired
    AuthorityService authorityService;

    /**
     * 保存功能性节点信息，这里不管是创建还是修改都用同一个接口
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RespBean editAsProcModel(@RequestBody ProcModelDTO dto) {
        try {
            procNodeService.saveProcModelInfo(dto);
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            return RespBean.error("插入数据失败！");
        }
        return RespBean.ok("");
    }

    /**
     * 对流程模型中每个节点对应的所有表单项权限信息进行存储
     */
    @RequestMapping(value = "/authority", method = RequestMethod.POST)
    public RespBean saveAuthority(@RequestBody AuthorityDTO authorityDTO) {
        if (StringUtils.isEmpty(authorityDTO.getProc_model_id()))
            return RespBean.error("流程模型数据缺失！");

        //对流程节点-表单项进行一项一项存储
        for (int i = 0; i < authorityDTO.getData().size(); i++) {
            AuthorityItemDTO cur = authorityDTO.getData().get(i);

            if (StringUtils.isEmpty(cur.getAct_id()) ||
                    StringUtils.isEmpty(cur.getForm_item_key()) ||
                    cur.getAuthority() == null)
                return RespBean.error("权限数据缺失！");

            ActAuthority actAuthority = new ActAuthority.Builder()
                    .procModelId(authorityDTO.getProc_model_id())
                    .actId(cur.getAct_id())
                    .formItemKey(cur.getForm_item_key())
                    .authority(cur.getAuthority()).build();
            int flag  ;

            //不包含这个表单项权限，插入新的
            if(!authorityService.contain(authorityDTO.getProc_model_id(),cur.getAct_id(),cur.getForm_item_key()))
                flag = authorityMapper.insert(actAuthority);
            else
                flag = authorityService.updateAuthority(authorityDTO.getProc_model_id(),cur.getAct_id(),cur.getForm_item_key(),cur.getAuthority());

            if (flag == Constants.DATABASE_FAILED)
                return RespBean.error("插入权限数据失败！");
        }

        return RespBean.ok("");
    }


    /**
     * 流程模型与表单模型绑定分离，可以在流程模型设计阶段选择未绑定流程模型的表单模型
     * 这里是获取未绑定流程模型的表单模型
     */
    @GetMapping(value = "/unbind_form_model")
    public RespBean getUnbindFormModels() {
        List<UnBindFormModelVO> unbindFormModels = procNodeService.getUnbindFormModels();

        return RespBean.ok("", unbindFormModels);
    }

    /**
     * 流程模型与表单模型绑定分离，可以在流程模型设计阶段选择未绑定流程模型的表单模型
     * 这里是获取未绑定流程模型的表单模型
     */
    @PostMapping(value = "/proc_node_num")
    @ApiOperation(value = "首次保存设计界面节点数",notes = "")
    public RespBean saveProcNodeNum(@ApiParam(value = "流程模型Id", required = true) @RequestParam("proc_model_id") String procModelId,
                                    @ApiParam(value = "拖入设计页面的节点数目", required = true) @RequestParam("proc_node_num") Integer procNodeNum) {
        try {
            procModelService.saveProcNodeNum(procModelId, procNodeNum);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());

        }
        return RespBean.ok("插入成功！");
    }

    @PutMapping(value = "/proc_node_num")
    @ApiOperation(value = "更新设计界面节点数",notes = "")
    public RespBean updateProcNodeNum(@ApiParam(value = "流程模型Id", required = true) @RequestParam("proc_model_id") String procModelId,
                                    @ApiParam(value = "拖入设计页面的节点数目", required = true) @RequestParam("proc_node_num") Integer procNodeNum) {
        try {
            procModelService.updateProcNodeNum(procModelId, procNodeNum);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());

        }
        return RespBean.ok("插入成功！");
    }

    @GetMapping(value = "/proc_node_num")
    @ApiOperation(value = "获取之前保存的设计界面节点数",notes = "")
    public RespBean getProcNodeNum(@ApiParam(value = "流程模型Id", required = true) @RequestParam("proc_model_id") String procModelId) {
        int num = procModelService.getProcNodeNum(procModelId);
        return RespBean.ok("成功！",num);
    }

    @GetMapping(value = "/bind_form_model")
    @ApiOperation(value = "获取当前流程模型绑定的表单数据")
    public RespBean getBindFormSheet(@RequestParam("proc_model_id")String procModelId){
        String sheetStr = procModelService.getBindFormSheet(procModelId);
        if(sheetStr==null)
            return RespBean.error("当前流程模型还未绑定表单模型，请先选择表单模型进行绑定!");
        return RespBean.ok("",sheetStr);
    }

}
