package com.asset.wrapper;

import com.asset.bean.ApiDataSource;
import com.asset.service.IApiDataSourceService;
import com.asset.service.IDictService;
import com.asset.vo.ApiDataSourceVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ApiDataSourceWrapper {

    IApiDataSourceService apiDataSourceService;

    IDictService dictService;

    public ApiDataSourceWrapper() {
    }

    public ApiDataSourceVO entityVO(ApiDataSource dataSource) {
        ApiDataSourceVO apiDataSourceVO = BeanUtil.copy(dataSource, ApiDataSourceVO.class);
        String d1 = dictService.getValue("api_method", Func.toInt(apiDataSourceVO.getApiMethod()));
        String d2 = dictService.getValue("api_type", Func.toInt(apiDataSourceVO.getApiType()));
        String d3 = dictService.getValue("api_content_type", Func.toInt(apiDataSourceVO.getContentType()));
        if (Func.isNotEmpty(d1)) {
            apiDataSourceVO.setApiMethodName(d1);
        }
        if (Func.isNotEmpty(d2)) {
            apiDataSourceVO.setApiTypeName(d2);
        }
        if (Func.isNotEmpty(d3)) {
            apiDataSourceVO.setContentTypeName(d3);
        }
        return apiDataSourceVO;
    }

    public List<ApiDataSourceVO> listNodeVO(List<ApiDataSource> list) {
        return list.stream().map(apiDataSource -> BeanUtil.copy(apiDataSource, ApiDataSourceVO.class)).collect(Collectors.toList());
    }
}
