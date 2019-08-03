package com.asset.utils;

import com.asset.exception.FormException;
import com.asset.javabean.FormInstBO;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式，对用户分到的FormInst进行过滤
 */
public interface Filter {

    public ArrayList<FormInstBO> filtrate(List<FormInstBO> formInstBOList);
    public FormInstBO filtrate(FormInstBO formInstBO) throws FormException;

}
