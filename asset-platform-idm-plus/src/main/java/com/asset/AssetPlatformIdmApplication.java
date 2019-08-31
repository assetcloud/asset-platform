package com.asset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@MapperScan("com.asset.mapper")
@EnableCaching
@EnableFeignClients
public class AssetPlatformIdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetPlatformIdmApplication.class, args);
    }

}
