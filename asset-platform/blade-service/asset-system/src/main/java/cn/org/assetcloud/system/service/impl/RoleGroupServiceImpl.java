package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.RoleGroup;
import cn.org.assetcloud.system.mapper.RoleGroupMapper;
import cn.org.assetcloud.system.service.IRoleGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */
@Service
@AllArgsConstructor
public class RoleGroupServiceImpl extends ServiceImpl<RoleGroupMapper, RoleGroup> implements IRoleGroupService {

}
