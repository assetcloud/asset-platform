package com.asset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lichao
 */
@SpringBootApplication(
        exclude= {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                UserDetailsServiceAutoConfiguration.class,
                LiquibaseAutoConfiguration.class
        }
)
@ComponentScan(basePackages={
        "com.asset",
        "org.flowable.ui.modeler",
        "org.flowable.ui.common",
        "org.flowable.rest"
})
@MapperScan(basePackages= {"com.asset.dao"})
public class FlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }
}
