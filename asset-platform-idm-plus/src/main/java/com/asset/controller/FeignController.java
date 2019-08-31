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

package com.asset.controller;

import com.asset.client.DataClient;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("feign")
public class FeignController {

    @Autowired
    private DataClient dataClient;

    @GetMapping("data/rules")
    public R getData(String userId, String sceneId){
        return R.data("ok");
    }
}
