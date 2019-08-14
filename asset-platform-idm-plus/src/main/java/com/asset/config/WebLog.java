package com.asset.config;

import java.lang.annotation.*;

/**
 * 统一日志输出
 * @author hjhu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

    /**
     * 日志描述信息
     */
    String description() default "";
}
