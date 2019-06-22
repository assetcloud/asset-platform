package com.asset.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author YBY
 * @time 190619 1057
 * @version 1.1_190619 1057
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

	@RequestMapping(value = "/app/authentication", method = RequestMethod.GET)
	public boolean login(){
		return true;
	}

	@RequestMapping(value = "/flowable-idm/app/authentication", method = RequestMethod.GET)
	public boolean login2(){
		return true;
	}

	/**
	 * postman测试成功
	 * @return
	 */
	@RequestMapping(value = {"/flowable-idm/app/rest/account"})
	public String accountIdm() {
		return "{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
				+ "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
				+ ":["
				+ "\"access-idm\","
				+ "\"access-task\","
				+ "\"access-modeler\","
				+ "\"access-admin\""
				+ "]}\n";
	}


	/**
	 * postman测试成功
	 * @return
	 */
	@RequestMapping(value = {"/flowable-idm/app/logout"},method = RequestMethod.GET)
	public String accountLogout() {
		return "{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
				+ "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
				+ ":["
				+ "\"access-idm\","
				+ "\"access-task\","
				+ "\"access-modeler\","
				+ "\"access-admin\""
				+ "]}\n";
	}

	@RequestMapping(value = "/test")
	public String test() {
		return "test";
	}
}
