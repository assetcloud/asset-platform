package com.asset.javabean;

import lombok.Data;

import java.util.List;

@Data
public class FormJson {
    List<Object> list;
    FormConfig config;
}
