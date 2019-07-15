package com.asset.config;

import com.asset.filter.FlowableHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * @author lichao
 */
@SuppressWarnings("deprecation")
@Configuration
public class InterceptorAdapter extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new FlowableHandlerInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/","/login","/logout","toLogin","/error/**");
		super.addInterceptors(registry);
	}
}
