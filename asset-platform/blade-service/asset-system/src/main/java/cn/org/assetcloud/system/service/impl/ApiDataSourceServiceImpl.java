package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.ApiDataSource;
import cn.org.assetcloud.system.mapper.ApiDataSourceMapper;
import cn.org.assetcloud.system.service.IApiDataSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApiDataSourceServiceImpl extends ServiceImpl<ApiDataSourceMapper, ApiDataSource> implements IApiDataSourceService {


}
