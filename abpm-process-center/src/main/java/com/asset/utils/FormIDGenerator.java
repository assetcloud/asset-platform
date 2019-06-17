package com.asset.utils;

import java.util.Date;

/**
 * @author yby
 * @time 190603 1050
 * @version 1.0_190603 1050
 */
public class FormIDGenerator implements IDGenerator {

    @Override
    public String generateID() {
        Date date = new Date();
        return "form"+date.getTime();
    }
}
