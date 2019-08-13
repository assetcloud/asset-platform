package com.asset.javabean;


import lombok.Data;

/**
 * 实体类ActRuVariable的包装类
 */
@Data
public class ActRuVariableBO {
    private Integer rev;
    private String executionId;
    private String procInstId;
    private String taskId;
    private String scopeId;
    private String subScopeId;
    private String scopeType;
    private String bytearrayId;
    private Double doubleLocal;
    private Long longLocal;
    //以上值都是与实体类ActRuVariable相同的

    //以下值是和实体类ActRuVariable不一样的
//    private String id;      //id值DO对象自己生成
//    private String type;
//    private String name;
//    private String text;
//    private String text2;

    String form_inst_value; //填写的表单数据信息，需要把它解析出来，填写在上面的type、name、text、text2中

    private ActRuVariableBO(Builder builder) {
        setRev(builder.rev);
        setExecutionId(builder.executionId);
        setProcInstId(builder.procInstId);
        setTaskId(builder.taskId);
        setScopeId(builder.scopeId);
        setSubScopeId(builder.subScopeId);
        setScopeType(builder.scopeType);
        setBytearrayId(builder.bytearrayId);
        setDoubleLocal(builder.doubleLocal);
        setLongLocal(builder.longLocal);
        setForm_inst_value(builder.form_inst_value);
    }


    public static final class Builder {
        private Integer rev;
        private String executionId;
        private String procInstId;
        private String taskId;
        private String scopeId;
        private String subScopeId;
        private String scopeType;
        private String bytearrayId;
        private Double doubleLocal;
        private Long longLocal;
        private String form_inst_value;

        public Builder() {
        }

        public Builder rev(Integer val) {
            rev = val;
            return this;
        }

        public Builder executionId(String val) {
            executionId = val;
            return this;
        }

        public Builder procInstId(String val) {
            procInstId = val;
            return this;
        }

        public Builder taskId(String val) {
            taskId = val;
            return this;
        }

        public Builder scopeId(String val) {
            scopeId = val;
            return this;
        }

        public Builder subScopeId(String val) {
            subScopeId = val;
            return this;
        }

        public Builder scopeType(String val) {
            scopeType = val;
            return this;
        }

        public Builder bytearrayId(String val) {
            bytearrayId = val;
            return this;
        }

        public Builder doubleLocal(Double val) {
            doubleLocal = val;
            return this;
        }

        public Builder longLocal(Long val) {
            longLocal = val;
            return this;
        }

        public Builder form_inst_value(String val) {
            form_inst_value = val;
            return this;
        }

        public ActRuVariableBO build() {
            return new ActRuVariableBO(this);
        }
    }
}
