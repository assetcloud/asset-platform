package com.asset.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/point")
@AllArgsConstructor
public class PointLoginController {

    RestTemplate restTemplate;

    @PostMapping("/login")
    public String login(String accountName){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("accountName", accountName);
        HttpEntity<Object> objectHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange("10.1.18.178:8888", HttpMethod.GET, objectHttpEntity, JSONObject.class);
        if (exchange.getStatusCodeValue() == 200){
            return "ok";
        } else {
            return "error";
        }
    }
}
