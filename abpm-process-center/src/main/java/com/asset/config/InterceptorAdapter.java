package com.asset.config;

import com.asset.common.DateConverter;
import com.asset.filter.FlowableHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * @author hjhu
 */
@SuppressWarnings("deprecation")
@Configuration
public class InterceptorAdapter extends WebMvcConfigurerAdapter {

	//注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new FlowableHandlerInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/","/login","/logout","toLogin","/error/**");
		super.addInterceptors(registry);
	}

	@Override
	public void addFormatters(FormatterRegistry registry){
		registry.addConverter(new DateConverter());
	}
}
