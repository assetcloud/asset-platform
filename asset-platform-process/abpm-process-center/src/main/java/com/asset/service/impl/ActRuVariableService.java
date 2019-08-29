package com.asset.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.ActRuVariableDO;
import com.asset.dao.ActRuVariableMapper;
import com.asset.javabean.ActRuVariableBO;
import com.asset.service.IActRuVariableService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-12
 */
@Service
public class ActRuVariableService extends ServiceImpl<ActRuVariableMapper, ActRuVariableDO> implements IActRuVariableService {

    @Autowired
    ActRuVariableMapper actRuVariableMapper;

    /**
     * 往act_ru_variable表中存储流程运行中变量
     */
    @Override
    public void saveRunVariable(ActRuVariableBO boo) {
        String formValue = boo.getForm_inst_value();
        JSONObject JSON = JSONObject.parseObject(formValue);
        for(String key:JSON.keySet()){
//            System.out.println(key + ":" +JSON.get(key));
            ActRuVariableDO doo =new ActRuVariableDO.Builder()
                    .type("string")
                    .name(key)
                    .text(JSON.get(key).toString()).build();
            BeanUtils.copyProperties(boo,doo);

            //这边需要判断相同EXECUTION、PROC_INST_ID的情况下应该是更新对应的TEXT值，而不是插入
            if(!ifContain(doo))
                actRuVariableMapper.insert(doo);
            else {
                updateSelective(doo);
            }

        }
    }


    public void updateSelective(ActRuVariableDO doo){
        update(Wrappers.<ActRuVariableDO>update().lambda()
                .set(ActRuVariableDO::getText,doo.getText())
                .eq(ActRuVariableDO::getProcInstId, doo.getProcInstId())
                .eq(ActRuVariableDO::getExecutionId, doo.getExecutionId())
                .eq(ActRuVariableDO::getName,doo.getName()));
    }

    //是否包含当前要存入的这个运行值信息？
    public boolean ifContain(ActRuVariableDO doo)
    {
        QueryWrapper<ActRuVariableDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ActRuVariableDO::getProcInstId, doo.getProcInstId())
                .eq(ActRuVariableDO::getExecutionId, doo.getExecutionId())
                .eq(ActRuVariableDO::getName,doo.getName());
        List<ActRuVariableDO> actRuVariables = list(queryWrapper);
        if(actRuVariables.size()==0)
            return false;
        else
            return true;
    }
}
