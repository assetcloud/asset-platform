package com.asset.controller;

import com.alibaba.fastjson.JSONObject;
import com.asset.bean.ApiDataSource;
import com.asset.common.model.Query;
import com.asset.service.IApiDataSourceService;
import com.asset.service.IDictService;
import com.asset.utils.Condition;
import com.asset.vo.ApiDataSourceVO;
import com.asset.wrapper.ApiDataSourceWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 控制器   haijie
 *
 * @author hjhu
 */
@RestController
@AllArgsConstructor
@RequestMapping("dataSource")
@Api(value = "api数据源管理", tags = "数据源管理")
public class DataSourceController {

    IApiDataSourceService apiDataSourceService;

    IDictService dictService;

    RestTemplate restTemplate;
    /**
     * 详情
     */
    @GetMapping("detail")
    @ApiOperation(value = "详情", notes = "传入dataSource")
    public R<ApiDataSourceVO> detail(ApiDataSource dataSource) {
        ApiDataSource detail = apiDataSourceService.getOne(Condition.getQueryWrapper(dataSource));
        ApiDataSourceWrapper wrapper = new ApiDataSourceWrapper(apiDataSourceService, dictService);
        return R.data(wrapper.entityVO(detail));
    }

    /**
     * 全部数据源列表
     */
    @GetMapping("list/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "size", value = "数据量", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "apiName", value = "字典编号", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", paramType = "query", dataType = "string")
    })
    @ApiOperation(value = "列表", notes = "传入dataSource")
    public R list(@ApiIgnore @RequestParam Map<String, Object> dataSource, Query query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        @SuppressWarnings("unchecked")
        List<ApiDataSource> list = apiDataSourceService.list(Condition.getQueryWrapper(dataSource, ApiDataSource.class)
                .lambda().orderByAsc(ApiDataSource::getAddTime));
        ApiDataSourceWrapper wrapper = new ApiDataSourceWrapper();
        return R.data(new PageInfo<>(wrapper.listNodeVO(list)));
    }

    /**
     * 单位数据源列表
     */
    @GetMapping("list-per-scene")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "size", value = "数据量", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "apiName", value = "字典编号", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", paramType = "query", dataType = "string")
    })
    @ApiOperation(value = "列表", notes = "传入dataSource")
    public R listPerScene(@ApiIgnore @RequestParam Map<String, Object> dataSource, Query query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        @SuppressWarnings({"unchecked", "Duplicates"})
        List<ApiDataSource> list = apiDataSourceService.list(Condition.getQueryWrapper(dataSource, ApiDataSource.class)
                .lambda().orderByAsc(ApiDataSource::getAddTime));
        ApiDataSourceWrapper wrapper = new ApiDataSourceWrapper();
        return R.data(new PageInfo<>(wrapper.listNodeVO(list)));
    }


    /**
     * 新增或修改
     */
    @PostMapping("submit")
    @ApiOperation(value = "新增或修改", notes = "传入dataSource", position = 6)
    public R submit(@Valid @RequestBody ApiDataSource dataSource) {
        return R.status(apiDataSourceService.saveOrUpdate(dataSource));
    }


    /**
     * 删除
     */
    @PostMapping("remove")
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(apiDataSourceService.removeByIds(Func.toStrList(ids)));
    }

    @PostMapping("api/verify")
    @ApiOperation(value = "删除", notes = "传入id")
    public R test(@ApiParam(value = "主键", required = true) @RequestParam String id){
        ApiDataSource dataSource = apiDataSourceService.getById(id);
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(dictService.getValue("api_content_type", dataSource.getContentType())));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = dataSource.getApiUrl().trim();
        ResponseEntity<JSONObject> responseEntity;
        if ((dictService.getValue("api_method", dataSource.getApiMethod())).equals(String.valueOf(HttpMethod.GET))){
            url += "?" + dataSource.getParams().trim();
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        } else {
            // post 暂未测试，这种接口一般用GET居多
            entity = new HttpEntity<>(dataSource.getParams(), headers);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
        }
        if (responseEntity.getStatusCodeValue() != 200){
            return R.fail("接口验证失败");
        }
        return R.success("接口验证通过");
    }
}
