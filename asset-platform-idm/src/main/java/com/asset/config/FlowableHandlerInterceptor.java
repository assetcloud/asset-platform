package com.asset.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hjhu
 */
public class FlowableHandlerInterceptor implements HandlerInterceptor {

	private final Logger LOGGER = LoggerFactory.getLogger(FlowableHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String servletPath = request.getServletPath();
		if (servletPath.endsWith(".css") || servletPath.endsWith(".js") || servletPath.endsWith(".jpg")
				|| servletPath.endsWith(".png")) {
			return true;
		}
		System.out.println(servletPath);
		LOGGER.info(servletPath);
//		Object obj = request.getSession().getAttribute(Constants.SESSION_USER);
//		if (obj == null) {
//			response.sendRedirect("/");
//			return false;
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
