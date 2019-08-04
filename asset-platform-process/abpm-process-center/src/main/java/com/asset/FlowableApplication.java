package com.asset;

import com.asset.config.ApplicationStartup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement(proxyTargetClass = true)
public class FlowableApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FlowableApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }
}
