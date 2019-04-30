package com.asset.config;

import org.flowable.ui.common.properties.FlowableCommonAppProperties;
import org.flowable.ui.common.properties.FlowableCommonAppProperties.Admin;
import org.flowable.ui.modeler.properties.FlowableModelerAppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author lichao
 */
@Configuration
public class FlowableBeanConfig {
	@Bean
	@Primary
	public FlowableCommonAppProperties flowableCommonAppProperties() {
		FlowableCommonAppProperties flowableCommonAppProperties = new FlowableCommonAppProperties();
		flowableCommonAppProperties.setIdmUrl("http://localhost:8080/flowable-idm");
		Admin idmAdmin = flowableCommonAppProperties.getIdmAdmin();
		idmAdmin.setUser("admin");
		idmAdmin.setPassword("test");
		return flowableCommonAppProperties;
	}

	@Bean
	public FlowableModelerAppProperties flowableModelerAppProperties() {
		FlowableModelerAppProperties flowableModelerAppProperties = new FlowableModelerAppProperties();
		return flowableModelerAppProperties;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper;
	}
}
