package com.asset.controller.user;

import com.asset.dao.AsProcModelMapper;
import com.asset.dao.FormAuthorityMapper;
import com.asset.dto.SeqConditionDTO;
import com.asset.entity.FormAuthorityDO;
import com.asset.entity.AsProcModelDO;
import com.asset.exception.DatabaseException;
import com.asset.dto.AuthorityItemDTO;
import com.asset.dto.AuthorityDTO;
import com.asset.dto.ProcModelDTO;
import com.asset.javabean.UnBindFormModelVO;
import com.asset.service.AuthorityService;
import com.asset.service.ProcNodeService;
import com.asset.service.impl.AsProcModelService;
import com.asset.utils.Constants;
import com.asset.utils.R;
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
@Api(tags = "终端：流程模型管理")
public class ProcModelController extends ServiceImpl<AsProcModelMapper, AsProcModelDO> {

    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    FormAuthorityMapper authorityMapper;
    @Autowired
    AsProcModelService procModelService;
    @Autowired
    AuthorityService authorityService;

    /**
     * 保存功能性节点信息，这里不管是创建还是修改都用同一个接口
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public R saveProcNodes(@RequestBody ProcModelDTO dto) {
        try {
            procNodeService.saveProcNodes(dto);
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            return R.fail("插入数据失败！");
        }
        return R.success("插入成功");
    }

    /**
     * 对流程模型中每个节点对应的所有表单项权限信息进行存储
     */
    @RequestMapping(value = "/authority", method = RequestMethod.POST)
    public R saveAuthority(@RequestBody AuthorityDTO authorityDTO) {
        if (StringUtils.isEmpty(authorityDTO.getProc_model_id()))
            return R.fail("流程模型数据缺失！");

        //对流程节点-表单项进行一项一项存储
        for (int i = 0; i < authorityDTO.getData().size(); i++) {
            AuthorityItemDTO cur = authorityDTO.getData().get(i);

            if (StringUtils.isEmpty(cur.getAct_id()) ||
                    StringUtils.isEmpty(cur.getForm_item_key()) ||
                    cur.getAuthority() == null)
                return R.fail("权限数据缺失！");

            FormAuthorityDO formAuthorityDO = new FormAuthorityDO.Builder()
                    .procModelId(authorityDTO.getProc_model_id())
                    .actId(cur.getAct_id())
                    .formItemKey(cur.getForm_item_key())
                    .authority(cur.getAuthority()).build();
            int flag;

            //不包含这个表单项权限，插入新的
            if (!authorityService.contain(authorityDTO.getProc_model_id(), cur.getAct_id(), cur.getForm_item_key()))
                flag = authorityMapper.insert(formAuthorityDO);
            else
                flag = authorityService.updateAuthority(authorityDTO.getProc_model_id(), cur.getAct_id(), cur.getForm_item_key(), cur.getAuthority());

            if (flag == Constants.DATABASE_FAILED)
                return R.fail("插入权限数据失败！");
        }
        //插入表单项数据之后，对表单模型的状态进行更新
        authorityService.updateFormModelStatus(authorityDTO.getProc_model_id());


        return R.success("插入成功");
    }


    /**
     * 流程模型与表单模型绑定分离，可以在流程模型设计阶段选择未绑定流程模型的表单模型
     * 这里是获取未绑定流程模型的表单模型
     */
    @GetMapping(value = "/unbind_form_model")
    public R<List<UnBindFormModelVO>> getUnbindFormModels() {
        List<UnBindFormModelVO> unbindFormModels = procNodeService.getUnbindFormModels();
        return R.data(unbindFormModels);
    }

    /**
     * 流程模型与表单模型绑定分离，可以在流程模型设计阶段选择未绑定流程模型的表单模型
     * 这里是获取未绑定流程模型的表单模型
     */
    @PostMapping(value = "/proc_node_num")
    @ApiOperation(value = "首次保存设计界面节点数", notes = "")
    public R saveProcNodeNum(@ApiParam(value = "流程模型Id", required = true) @RequestParam("proc_model_id") String procModelId,
                             @ApiParam(value = "拖入设计页面的节点数目", required = true) @RequestParam("proc_node_num") Integer procNodeNum) {
        try {
            procModelService.saveProcNodeNum(procModelId, procNodeNum);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());

        }
        return R.success("插入成功！");
    }

    @PutMapping(value = "/proc_node_num")
    @ApiOperation(value = "更新设计界面节点数", notes = "")
    public R updateProcNodeNum(@ApiParam(value = "流程模型Id", required = true) @RequestParam("proc_model_id") String procModelId,
                               @ApiParam(value = "拖入设计页面的节点数目", required = true) @RequestParam("proc_node_num") Integer procNodeNum) {
        try {
            procModelService.updateProcNodeNum(procModelId, procNodeNum);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());

        }
        return R.success("插入成功！");
    }

    @GetMapping(value = "/proc_node_num")
    @ApiOperation(value = "获取之前保存的设计界面节点数", notes = "")
    public R<Integer> getProcNodeNum(@ApiParam(value = "流程模型Id", required = true)
                                     @RequestParam("proc_model_id") String procModelId) {
        int num = procModelService.getProcNodeNum(procModelId);
        return R.data(num);
    }

    @GetMapping(value = "/bind_form_model")
    @ApiOperation(value = "获取当前流程模型绑定的表单数据")
    public R<String> getBindFormSheet(@RequestParam(value = "proc_model_id") String procModelId) {
        String sheetStr = procModelService.getBindFormSheet(procModelId);
        if (sheetStr == null)
            return R.fail("当前流程模型还未绑定表单模型，请先选择表单模型进行绑定!");
        return R.data(sheetStr);
    }

    @ApiOperation(value = "保存分支流程中sequenceFlow中包含的流转条件信息")
    @PostMapping(value = "/seq_condition")
    public R saveSeqCondition(@RequestBody SeqConditionDTO dto) {
        try {
            procModelService.saveSeqCondition(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.success("保存成功");
    }

}
