package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.MenuRole;
import cn.org.assetcloud.system.mapper.MenuRoleMapper;
import cn.org.assetcloud.system.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-29
 */
@Service
@AllArgsConstructor
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

	private MenuRoleMapper menuRoleMapper;
}
