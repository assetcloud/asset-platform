package com.asset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.asset.mapper")
public class AssetPlatformIdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetPlatformIdmApplication.class, args);
    }

}
