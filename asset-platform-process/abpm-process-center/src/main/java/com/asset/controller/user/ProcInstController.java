package com.asset.controller.user;

import com.asset.javabean.RespBean;
import com.asset.service.ProcInstService;
import com.asset.utils.ProcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YBY
 * @time 190723
 * @version 1.0_190723
 */
@RestController
public class ProcInstController {
    @Autowired
    ProcInstService procInstService;

    /**
     * 在执行上面的流程测试的时候需要保证现有的流程实例都已经跑完了，否则会造成错误
     */
    @RequestMapping(value = "/proc_inst/insts/complete",method = RequestMethod.POST)
    public RespBean completeAll()
    {
        ProcUtils.completeAll();
        return RespBean.ok("");
    }

}
