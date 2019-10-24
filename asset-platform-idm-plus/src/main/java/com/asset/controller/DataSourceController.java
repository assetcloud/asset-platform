package com.asset.controller;

import com.alibaba.fastjson.JSONObject;
import com.asset.apiBean.*;
import com.asset.bean.*;
import com.asset.common.model.Query;
import com.asset.service.*;
import com.asset.utils.CommonUtils;
import com.asset.utils.Condition;
import com.asset.vo.ApiDataSourceVO;
import com.asset.wrapper.ApiDataSourceWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.*;

/**
 * 控制器   haijie
 *
 * @author hjhu
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("dataSource")
@Api(value = "api数据源管理", tags = "数据源管理")
public class DataSourceController {

    IApiDataSourceService apiDataSourceService;

    IDictService dictService;

    IOrganService organService;

    IUserService userService;

    IOrganSceneService organSceneService;

    RestTemplate restTemplate;

    IUserSceneService userSceneService;

    ISceneRelationService sceneRelationService;
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
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        @SuppressWarnings("unchecked")
        List<ApiDataSource> list = apiDataSourceService.list(Condition.getQueryWrapper(dataSource, ApiDataSource.class)
                .lambda().orderByAsc(ApiDataSource::getAddTime));
        ApiDataSourceWrapper wrapper = new ApiDataSourceWrapper();
        PageInfo<ApiDataSourceVO> pageInfo = new PageInfo<>(wrapper.listNodeVO(list));
        pageInfo.setTotal(page.getTotal());
        return R.data(pageInfo);
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
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        @SuppressWarnings({"unchecked", "Duplicates"})
        List<ApiDataSource> list = apiDataSourceService.list(Condition.getQueryWrapper(dataSource, ApiDataSource.class)
                .lambda().orderByAsc(ApiDataSource::getAddTime));
        ApiDataSourceWrapper wrapper = new ApiDataSourceWrapper();
        PageInfo<ApiDataSourceVO> pageInfo = new PageInfo<>(wrapper.listNodeVO(list));
        pageInfo.setTotal(page.getTotal());
        return R.data(pageInfo);
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



    @PostMapping("api/organ/get")
    @ApiOperation(value = "资产云助手-获取本单位部门信息", notes = "传入id")
    public R getOrgan(){
//        ApiDataSource dataSource = apiDataSourceService.getById(id);
        JSONObject postData = new JSONObject();
        postData.put("rgCode", "330001");
        postData.put("orgCode", "470009026");
        //设置请求头
        HttpHeaders headers = new HttpHeaders();

        // 接口访问令牌
        headers.add("auth", "2AA9A0E621904C668F61E7FD99045ECF");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONObject> entity = new HttpEntity<>(postData, headers);
        String url = "https://assetcloud.ictdog.com/synergy/org/department";
//        JSONObject jsonObject = restTemplate.postForObject(url, headers, JSONObject.class, postData);
//        ResponseEntity<Res> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Res.class);
//        if (responseEntity.getStatusCodeValue() != 200){
//            return R.fail("接口访问失败");
//        }
//        log.info(responseEntity.toString());
//        log.info(Objects.requireNonNull(responseEntity.getBody()).toString());
//        return R.success("接口验证通过");
        ResponseEntity<Res> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Res.class);
        if (responseEntity.getStatusCodeValue() != 200){
            return R.fail("接口访问失败");
        }
        log.info(responseEntity.toString());
        log.info(Objects.requireNonNull(responseEntity.getBody()).toString());
        List<Organization> data = responseEntity.getBody().getData();
        ArrayList<OrganTree> organTrees = new ArrayList<>();
        int i = 1;
        for (Organization organization : data){
            OrganTree node = new OrganTree();
            node.setUnitName(organization.getName());
            node.setParentId("743ccc5fb94314d08490c4662b16753a");
            node.setSort(i++);
            node.setId(organization.getDepId());
            node.setStatus(1);
            node.setIsDeleted(0);
            organTrees.add(node);
        }
//        743ccc5fb94314d08490c4662b16753a
        return R.status(organService.saveBatch(organTrees));
    }
//
    @PostMapping("api/users/get")
    @ApiOperation(value = "资产云助手-获取本单位员工信息", notes = "传入id")
    public R getUsers(){
//        ApiDataSource dataSource = apiDataSourceService.getById(id);
        JSONObject postData = new JSONObject();
        postData.put("rgCode", "330001");
        postData.put("orgCode", "470009026");
        postData.put("mobile", "15325819667");
        //设置请求头
        HttpHeaders headers = new HttpHeaders();

        // 接口访问令牌
        headers.add("auth", "2AA9A0E621904C668F61E7FD99045ECF");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONObject> entity = new HttpEntity<>(postData, headers);
        String url = "https://assetcloud.ictdog.com/synergy/org/getOrgEmployee";
//        JSONObject jsonObject = restTemplate.postForObject(url, headers, JSONObject.class, postData);
//        ResponseEntity<UserRes> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, UserRes.class);
//        if (responseEntity.getStatusCodeValue() != 200){
//            return R.fail("接口访问失败");
//        }
//        log.info(responseEntity.toString());
//        log.info(Objects.requireNonNull(responseEntity.getBody()).toString());
//        return R.success("接口验证通过");

        ResponseEntity<UserRes> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, UserRes.class);
        if (responseEntity.getStatusCodeValue() != 200){
            return R.fail("接口访问失败");
        }
//        log.info(responseEntity.toString());
//        log.info(Objects.requireNonNull(responseEntity.getBody()).toString());
        List<ApiUser> data = responseEntity.getBody().getData();
//        log.info(data.toString());
        ArrayList<User> objects = new ArrayList<>();
        for (ApiUser datum : data) {
//            log.info(datum.toString());
            User user = new User();
            if (Func.hasEmpty(datum.getMobile())){
                user.setAccountName(datum.getCode());
                log.info(datum.getCode());
            } else {
                user.setAccountName(datum.getMobile());
            }
            user.setId(datum.getUserId());
            user.setPhoneNumber(datum.getMobile());
            user.setRealName(datum.getName());
            user.setNickname(datum.getName());
            user.setPwd("$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm");
            user.setAdmin(0);
            user.setUserAddress("浙江省杭州市");
            user.setStage(2);
            user.setStatus(1);
            user.setCreatedTime(new Date());
            objects.add(user);
        }
        userService.saveBatch(objects);
        test();
        ssss();
        return R.success("数据导入成功");
    }

    @PostMapping("api/users/bind")
    @ApiOperation(value = "资产云助手-获取本单位员工信息", notes = "传入id")
    private void test() {
        List<User> list = userService.list(Wrappers.<User>lambdaQuery());
        log.info("数据总数： {}", list.size());
        ArrayList<UserScene> userScenes = new ArrayList<>();
        for (User object : list) {
            UserScene userScene = new UserScene();
            userScene.setNodePrincipal(0);
            userScene.setUserId(object.getId());
            userScene.setNodeId("743ccc5fb94314d08490c4662b16753a");
            userScene.setSceneId("e65edc60-96ee-11e9-ac96-005056c00001");
            userScene.setStatus(1);
            userScenes.add(userScene);
        }
        userSceneService.saveBatch(userScenes);
    }


    @PostMapping("api/organ/save-as-scene")
    @ApiOperation(value = "资产云助手-获取本单位部门信息", notes = "传入id")
    public R saveAsScene(){
//        ApiDataSource dataSource = apiDataSourceService.getById(id);
        List<OrganTree> list = organService.list(Wrappers.<OrganTree>query().lambda().eq(OrganTree::getParentId, "743ccc5fb94314d08490c4662b16753a"));
        ArrayList<OrganScene> organScenes = new ArrayList<>();
        for (OrganTree organTree : list) {
            OrganScene organScene = CommonUtils.NodeTransformer(organTree);
            organScene.setSceneId("e65edc60-96ee-11e9-ac96-005056c00001");
            organScenes.add(organScene);
        }
//        743ccc5fb94314d08490c4662b16753a
        return R.status(organSceneService.saveBatch(organScenes));
    }

    @PostMapping("organ/sync")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "remoteUnitInfoList"),
            @ApiImplicitParam(value = "")
    })
    @ApiOperation(value = "组织信息同步接口-局部同步", notes = "传入组织信息列表")
    public R organSync(List<RemoteUnitInfo> remoteUnitInfoList, String sceneId){
        ArrayList<OrganTree> organTreeArrayList = new ArrayList<>();
        CommonUtils.remoteOrganTransformer(organTreeArrayList, remoteUnitInfoList);
        return R.status(organService.saveOrUpdateBatch(organTreeArrayList));
    }

    @PostMapping("user/sync")
    @ApiOperation(value = "用户信息同步接口-局部同步", notes = "传入用户信息列表")
    public R userSync(List<RemoteUnitInfo> remoteUnitInfoList, String sceneId){

        return R.success("");
    }

    @PostMapping("test")
    public R ssss(){
        List<User> list = userService.list(Wrappers.<User>lambdaQuery());
        ArrayList<SceneRelation> sceneRelations = new ArrayList<>();
        for (User user : list) {
            SceneRelation sceneRelation = new SceneRelation();
            sceneRelation.setRid(2L);
            sceneRelation.setUid(user.getId());
            sceneRelation.setIsDeleted(0);
            sceneRelations.add(sceneRelation);
        }
        return R.status(sceneRelationService.saveBatch(sceneRelations));
    }
}
