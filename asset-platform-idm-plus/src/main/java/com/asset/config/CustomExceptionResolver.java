package com.asset.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjhu on 2019/5/27.
 */
@Configuration
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        LOGGER.error("\n--------------------Error Print Start--------------------"
                + e.getMessage()
                + "\n--------------------Error Print End--------------------");
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("msg", "服务器错误");
        mv.addAllObjects(map);
        return mv;
    }
}
