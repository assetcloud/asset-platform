package com.asset.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
@SuppressWarnings("deprecation")
public class FlowableWebMvcConfigurer  extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/flowable/**")
		.addResourceLocations("classpath:/static/");
	}

}
