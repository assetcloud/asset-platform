package com.asset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
@MapperScan("com.asset.mapper")
@EnableCaching
public class AssetPlatformIdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetPlatformIdmApplication.class, args);
    }

//    @Bean
////    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
////        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
////            @Override
////            public void customize(ConfigurableWebServerFactory factory) {
//////                factory.setPort(8081);
////                ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
////                ArrayList<ErrorPage> errorPages = new ArrayList<>();
////                errorPages.add(errorPage);
////                HashSet<ErrorPage> errorPages1 = new HashSet<>(errorPages);
////                factory.setErrorPages(errorPages1);
////            }
////        };
////    }
}
