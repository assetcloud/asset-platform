package com.asset.ant.as.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.ant.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YBY
 * @since 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("as_form_proc")
public class FormProc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String procModelId;

    private String formModelId;


}
