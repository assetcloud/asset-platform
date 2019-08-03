package com.asset.dto;

import com.asset.bean.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author hjhu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends Role {
	private static final long serialVersionUID = 1L;

}
