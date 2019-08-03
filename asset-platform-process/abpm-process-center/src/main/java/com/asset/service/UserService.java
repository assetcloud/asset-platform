package com.asset.service;

import com.asset.dao.FormInstMapper;
import com.asset.dao.ProcInstMapper;
import com.asset.dao.ProcNodeMapper;
import com.asset.dao.UserMapper;
import com.asset.entity.User;
import com.asset.utils.PageGrids;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.flowable.engine.impl.test.AbstractTestCase.assertEquals;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    protected ModelService modelService;
    @Autowired
    FormInstService formInstService;
    @Autowired
    FormInstMapper formInstMapper;
    @Autowired
    ProcInstMapper procInstMapper;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    ProcNodeMapper procNodeMapper;
    @Autowired
    FormModelService formModelService;
    @Autowired
    FlowableService flowableService;

    public PageGrids getUsers(Integer pageNum, Integer pageSize, String id, String displayName) {
        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.getUsers(id, displayName);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }



    /**
     *
     * @param taskId 当前分配到的任务节点Id，可以去as_proc_node表中获取候选人信息中是不是包含 curUserId
     * @param curUserId 当前登录的userId
     * @return
     */
    public boolean validateFormInst(String taskId,String curUserId) {
        String formModelId = formInstService.getFormModelId(taskId);
        String procModelId = formModelService.getProcModelID(formModelId);
        String actId = flowableService.getNodeId(taskId);

        String candidateUsers = procNodeMapper.getCandidateUsers(procModelId,actId);
        String candidateGroups = procNodeMapper.getCandidateGroups(procModelId,actId);

        return true;
    }



}
