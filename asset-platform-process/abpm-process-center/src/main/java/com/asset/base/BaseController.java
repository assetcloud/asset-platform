package com.asset.base;

import com.asset.utils.Parametermap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * @author lichao
 */
public class BaseController {

	public Parametermap getParametermap() {
		Parametermap parametermap=new Parametermap(getRequest());
		parametermap.put("flowable", "flowable");
		return parametermap;
	}
	
	public HttpSession getSession() {
		HttpSession session = this.getRequest().getSession();
		return session;
	}
	
	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	public HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return request;
		
	}
}