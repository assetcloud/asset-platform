package com.asset.utils;

import java.util.Date;

/**
 *
 */
public class FormAuthorityIDGenerator implements IDGenerator{
    @Override
    public String generateID() {
        Date date = new Date();
        return "FormAuthority"+date.getTime();
    }
}
