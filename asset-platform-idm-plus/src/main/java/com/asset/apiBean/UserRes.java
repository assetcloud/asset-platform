package com.asset.apiBean;

import lombok.Data;

import java.util.List;

@Data
public class UserRes {

    private int code;

    private List<ApiUser> data;

    private boolean success;
}
