package com.asset.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于协作类
 */
@Data
@ApiModel("第三方向业务中台请求发起表单流程所需的信息")
public class CoopCommitFormProcDTO {
    //要调用业务中台系统中哪个表单流程
    @ApiModelProperty(value = "要调用业务中台系统中哪个表单流程,需要和业务中台开发人员提前确认",required = true)
    String form_proc_uid;

    //第三方系统按照业务中台要求组装好的表单信息（以json字符串形式保存）
    /**
     * 注意这里的格式是：
     * {
     *   "input_1566366027958": "sa",
     *   "input_1566366044118": 212,
     *   "input_1566366061170": "21"
     * }
     * 可以直接作为sheet的value部分！所以不需要进行解析！
     */
    @ApiModelProperty(value = "第三方系统按照业务中台要求组装好的表单信息（以json字符串形式保存），格式如下：" +
            "{\n" +
            " \"input_1566366027958\": \"sa\",\n" +
            " \"input_1566366044118\": 212,\n" +
            " \"input_1566366061170\": \"21\"\n" +
            "}",required = true)
    String form_value;

    //提交人的Id
    @ApiModelProperty(value = "提交人的用户Id，这个值应当从组织架构模块中获取",required = true)
    String committer_id;

    @ApiModelProperty(value = "用于指示是部门内资产移交还是部门间资产移交,1——部门间移交，0——部门内移交")
    int condition;

    //接收部门Id字段
    String receiving_section_id;
}
