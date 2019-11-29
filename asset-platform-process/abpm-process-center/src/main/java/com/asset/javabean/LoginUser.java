package com.asset.javabean;

import lombok.Builder;
import lombok.Data;

/**
 * 用来表示当前的登录用户
 */
@Data
@Builder
public class LoginUser {
    String userId;
    String name;
    String sectionId;
    String sceneId;
}
