package com.asset.service;

import com.alibaba.fastjson.JSONObject;
import com.asset.converter.FormConverter;
import com.asset.dao.FormAuthorityMapper;
import com.asset.entity.FormAuthorityDO;
import com.asset.entity.AsTempletFormAuthorityDO;
import com.asset.entity.FormInstDO;
import com.asset.exception.ProcException;
import com.asset.javabean.form.OptionsBase;
import com.asset.exception.DatabaseException;
import com.asset.exception.FormException;
import com.asset.exception.InfoException;
import com.asset.javabean.form.FormItem;
import com.asset.javabean.form.FormSheet;
import com.asset.utils.Constants;
import com.asset.utils.FormUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用于实现 表单项权限（该表单项设置不可写、可写、必填）设置 的业务类
 * @author YBY
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuthorityService {

    @Autowired
    FormAuthorityMapper formAuthorityMapper;
    @Autowired
    FormInstService formInstService;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    FlowableService flowableService;
    @Autowired
    FormModelService formModelService;

    public Integer getCurAuthority(String procModelId, String actId, String itemKey) {
        return formAuthorityMapper.getAuthority(procModelId,actId,itemKey);
    }

    /**
     *
     * @param curAuthority 第j个表单项当前的权限应该是什么（在调用该方法前已经指定好了）
     */
    public FormItem handleItemAuthority(FormItem curItem, Integer curAuthority) {
        switch (curAuthority){
            //必填，获取list中json对象的rules属性，进行修改,
            //diable:false;required:true+rule
            case Constants.AUTHORITY_REQUIRED:
                //对Options属性进行修改
                OptionsBase optionsBase = curItem.getOptions();
                optionsBase.setRequired(true);
                optionsBase.setDisabled(false);
                curItem.setOptions(optionsBase);

                //添加Rules
                List<JSONObject> rules = curItem.getRules();
                if(rules==null)
                {
                    //添加新的 必填 rule
                    FormUtils.addRequiredRule(curItem);
                }
                //还需要对rules里面的内容进行进一步的判断
                else{
                    for (int i = 0;i<rules.size();i++)
                    {
                        boolean isContain = rules.get(i).containsKey("required");
                        //表示当前包含"required"这个字段了,没必要添加新的 必填 rule
                        if(isContain)
                            return curItem;
                    }
                    //上面都遍历了还没有找到"required"这个字段，说明的确是没有这个字段，现在
                    //要添加新的 必填 rule
                    FormUtils.addRequiredRule(curItem);
                }
                break;
            //不可见，就是这个表单项直接删除？先暂时不作
            case Constants.AUTHORITY_INVISIBLE:
                break;
            //可见，这个就是不处理
            //disable:false;required:false
            case Constants.AUTHORITY_ENABLE:
                OptionsBase optionsBase2 = curItem.getOptions();
                optionsBase2.setDisabled(false);
                optionsBase2.setRequired(false);
                curItem.setOptions(optionsBase2);
                break;
            //无法编辑，获取list中的json对象的options属性进行修改
            //disable:true;required:false
            case Constants.AUTHORITY_DISABLE:
                OptionsBase optionsBase1 =curItem.getOptions();
                optionsBase1.setDisabled(true);
                optionsBase1.setRequired(false);
                curItem.setOptions(optionsBase1);
                break;
        }
        return curItem;
    }

    public FormInstDO handleFormSheetAuthority(FormInstDO formInstDO) throws FormException ,ProcException{
        //注意这里从表单实例中获取到的sheet是为null的，需要从对应的model中去获取
        String formModelId = formInstDO.getFormModelId();
        String modelSheetStr = formModelService.getModelSheetStr(formModelId);
        FormSheet formSheet = FormConverter.jsonToEntity(modelSheetStr);

        String curNode = flowableService.getNodeId(formInstDO.getTaskId());
        String procModelId = procInstService.getProcModelId(formInstDO.getProcInstId());

        if(procModelId.equals(Constants.REGISTER_PROC_ID)||
                procModelId.equals(Constants.SCENE_SELECT_PROC_ID))
        {
            //这边需要对是否为注册审批流程进行判断，如果是的话，需要对第二个节点————审批节点进行特殊设置
            formSheet = handleRegisterProc(procModelId,formSheet,curNode);
            modelSheetStr = FormConverter.entityToJson(formSheet);
            formInstDO.setFormInstSheetStr(modelSheetStr);
            formInstDO.setNodeType(Constants.AS_NODE_APPROVE);
            return formInstDO;
        }

        List<FormItem> items = formSheet.getList();

        for (int i = 0; i <items.size(); i++) {
            //获取当前Authority
            Integer curAuthority = getCurAuthority(procModelId,
                    curNode,
                    items.get(i).getKey());
            if(curAuthority==null)
            {
                curAuthority =  Constants.AUTHORITY_ENABLE;
                System.out.println("当前流程模型："+procModelId+" 表单项："+items.get(i).getKey()+"的权限信息丢失！");
            }
            //添加权限信息
            handleItemAuthority(items.get(i),curAuthority);
        }

        modelSheetStr = FormConverter.entityToJson(formSheet);

        formInstDO.setFormInstSheetStr(modelSheetStr);
        return formInstDO;
    }



    public FormSheet handleRegisterProc(String procModelId,
                                        FormSheet formSheet,
                                        String curNode) throws FormException {
//        Boolean isRegister = procModelId.equals(Constants.REGISTER_PROC_ID)?true:false;
//        if(!isRegister)
//            return formSheet;

        //获取Sheet中一项项表单项，对每个表单项进行处理，看是否需要对其进行隐藏、加不可写或改为必填
        List<FormItem> items = formSheet.getList();
        if(items==null)
            throw new FormException("表单模型数据为空");
        for(int j=0;j<items.size();j++)
        {
            //当前流程模型是注册，表单项权限全部设为不可写
//            if(isRegister)
//            {
                handleItemAuthority(items.get(j),Constants.AUTHORITY_DISABLE);
//                continue;
//            }
            //获取当前Authority
//            Integer curAuthority = getCurAuthority(procModelId,curNode,items.get(j).getKey());
            //添加权限信息
//            handleItemAuthority(items.get(j),curAuthority);
        }

        return formSheet;
    }

    //判断当前存的表单
    public boolean contain(String procModelId, String actId,String formItemKey) {
        return formAuthorityMapper.getAuthority(procModelId,actId,formItemKey) == null?false:true;
    }

    public int updateAuthority(String procModelId, String actId, String formItemKey, Integer authority) {
        return formAuthorityMapper.updateAuthority(procModelId,actId,formItemKey,authority);
    }

    public void insertTempletResource(List<AsTempletFormAuthorityDO> asTempletFormAuthorityDOs,
                                        String procModelId) throws InfoException,DatabaseException{
        //对流程节点-表单项进行一项一项存储
        for (int i = 0; i < asTempletFormAuthorityDOs.size(); i++) {
            AsTempletFormAuthorityDO cur = asTempletFormAuthorityDOs.get(i);

            if (StringUtils.isEmpty(cur.getActId()) ||
                    StringUtils.isEmpty(cur.getFormItemKey()) ||
                    cur.getAuthority() == null)
                throw new InfoException("权限数据缺失");

            FormAuthorityDO formAuthorityDO = new FormAuthorityDO.Builder()
                    .procModelId(procModelId)
                    .build();
            BeanUtils.copyProperties(cur,formAuthorityDO,new String[]{"id"});

            int flag = formAuthorityMapper.insert(formAuthorityDO);

            if (flag == Constants.DATABASE_FAILED)
                throw new DatabaseException("插入权限数据失败！");
        }
    }

    public List<FormAuthorityDO> selectList(String procModelId) {
        return formAuthorityMapper.selectList(procModelId);
    }


    public void updateFormModelStatus(String procModelId) {
        formModelService.bindAuthority(procModelId);
    }
}
