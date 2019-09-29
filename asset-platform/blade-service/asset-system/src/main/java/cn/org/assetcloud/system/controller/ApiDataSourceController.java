package cn.org.assetcloud.system.controller;

import cn.org.assetcloud.system.entity.ApiDataSource;
import cn.org.assetcloud.system.entity.Menu;
import cn.org.assetcloud.system.service.IApiDataSourceService;
import cn.org.assetcloud.system.vo.MenuVO;
import cn.org.assetcloud.system.wrapper.MenuWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/datasource")
@AllArgsConstructor
public class ApiDataSourceController {

	IApiDataSourceService apiDataSourceService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "urlName", value = "数据源名称", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "列表", notes = "传入menu")
	public R<List<ApiDataSource>> list(@ApiIgnore @RequestParam Map<String, Object> apiDataSource) {
		@SuppressWarnings("unchecked")
		List<ApiDataSource> list = apiDataSourceService.list(Condition.getQueryWrapper(apiDataSource, ApiDataSource.class).lambda()
			.orderByAsc(ApiDataSource::getAddTime));
		// 要写一个Wrapper
//		MenuWrapper menuWrapper = new MenuWrapper(menuService, dictClient);
		return R.data(list);
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入apiDataSource实体")
	public R submit(@Valid @RequestBody ApiDataSource apiDataSource) {
		return R.status(apiDataSourceService.saveOrUpdate(apiDataSource));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入id")
	@Transactional
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam Integer id) {
		return R.status(apiDataSourceService.removeById(id));
	}
}
