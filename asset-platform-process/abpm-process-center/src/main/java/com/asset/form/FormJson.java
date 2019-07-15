package com.asset.form;

import com.asset.form.FormConfig;
import lombok.Data;

import java.util.List;

@Data
public class FormJson {
    List<Object> list;
    FormConfig config;
}
