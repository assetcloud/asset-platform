package com.asset.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author lichao
 */
@RestController
@RequestMapping("/api")
public class FlowableAccountController {
	
	@RequestMapping(value = "/rest/account")
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
}
