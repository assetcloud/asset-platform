package com.asset.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author lichao
 */
@RestController
public class FlowableAccountController {

    /**
     * postman测试成功
     * @return
     */
	@RequestMapping(value = "/api/rest/account", method = RequestMethod.GET)
	public String account() {
		return "{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
				+ "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
				+ ":["
				+ "\"access-idm\","
				+ "\"access-task\","
				+ "\"access-modeler\","
				+ "\"access-admin\""
				+ "]}\n";
	}

	@RequestMapping(value = "/app/rest/account", method = RequestMethod.GET)
	public String accountTest() {
		return "{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
				+ "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
				+ ":["
				+ "\"access-idm\","
				+ "\"access-task\","
				+ "\"access-modeler\","
				+ "\"access-admin\""
				+ "]}\n";
	}
}
