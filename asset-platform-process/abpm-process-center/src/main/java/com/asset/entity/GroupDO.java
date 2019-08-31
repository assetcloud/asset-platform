package com.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_group")
public class GroupDO {

    @TableId(type = IdType.UUID)
    protected Integer id;

    protected String appId;

    protected String groupName;

    protected Integer status;

    public GroupDO() {
    }

    private GroupDO(Builder builder) {
        setId(builder.id);
        setAppId(builder.appId);
        setGroupName(builder.groupName);
        setStatus(builder.status);
    }


    public static final class Builder {
        private Integer id;
        private String appId;
        private String groupName;
        private Integer status;

        public Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public Builder groupName(String val) {
            groupName = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public GroupDO build() {
            return new GroupDO(this);
        }
    }
}
