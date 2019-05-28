package com.asset.common;

import com.asset.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by hjhu on 2019/5/27.
 */
public class UserUtils {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
