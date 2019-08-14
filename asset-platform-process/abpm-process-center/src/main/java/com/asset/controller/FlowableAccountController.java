package com.asset.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lichao
 */
@RestController
@RequestMapping("/app")
@ApiIgnore
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
