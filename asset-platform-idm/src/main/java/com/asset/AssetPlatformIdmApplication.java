package com.asset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.asset.mapper")
public class AssetPlatformIdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetPlatformIdmApplication.class, args);
    }

}
