package com.asset.apiBean;

import lombok.Data;

import java.util.List;

@Data
public class Res {

    private int code;

    private List<Organization> data;

    private boolean success;
}
