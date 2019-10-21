package com.asset.apiBean;

import lombok.Data;

import java.util.List;

@Data
public class UniRes<T> {

    private int code;

    private List<T> data;

    private boolean success;
}
