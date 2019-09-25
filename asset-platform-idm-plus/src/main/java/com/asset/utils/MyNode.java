/*
 * Copyright (c) 2019.
 * *
 *  * All rights Reserved, Designed By hjhu
 *  * @Title:  ${file_name}
 *  * @Package ${package_name}
 *  * @Description:    ${todo}(用一句话描述该文件做什么)
 *  * @author: HDU
 *  * @date:   ${date} ${time}
 *  * @version V1.0
 *  * @Copyright: ${year} www.assetcloud.org.cn Inc. All rights reserved.
 *
 */

package com.asset.utils;

import java.util.List;

public interface MyNode {

    String getId();

    String getParentId();

    List<MyNode> getChildren();
}
