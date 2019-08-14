package com.asset.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 授权服务器，发放access_token
 * @author YBY
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //这里是获取临时授权的账号密码 账号clientadmin，密码123
                .withClient("clientadmin")
                .secret("123")
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(120)
                .scopes("admin");
    }
}
