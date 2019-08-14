package com.asset.entity;

import java.io.Serializable;

/**
 * @author lichao
 */
public class ProcTypeEntity implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String codeId;

    private String name;

    private Integer pid;

    private Long state;

    public ProcTypeEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }
}
