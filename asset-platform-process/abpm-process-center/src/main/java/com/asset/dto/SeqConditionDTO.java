package com.asset.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel
public class SeqConditionDTO {
    @ApiModelProperty(required = true)
    String proc_model_id;

    @ApiModelProperty(value = "该流程模型中所有的sequenceFlow条件，例：{\"seq1\":\"${select_1566385304784 eq '本部门'}\",\"seq2\":\"${select_1566385304784 ne '本部门'}\"}",
            required = true)
    String seq_condition;
}
