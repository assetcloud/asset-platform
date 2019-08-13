package com.asset.entity;

import java.io.Serializable;

/**
 * @author lichao
 */
public class ProcDefInfo  implements Serializable {

    private String id_;
    private String name_;
    private String key_;
    private String version_;
    private String createtime_;

    public ProcDefInfo() {
    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getKey_() {
        return key_;
    }

    public void setKey_(String key_) {
        this.key_ = key_;
    }

    public String getVersion_() {
        return version_;
    }

    public void setVersion_(String version_) {
        this.version_ = version_;
    }

    public String getCreatetime_() {
        return createtime_;
    }

    public void setCreatetime_(String createtime_) {
        this.createtime_ = createtime_;
    }
}
