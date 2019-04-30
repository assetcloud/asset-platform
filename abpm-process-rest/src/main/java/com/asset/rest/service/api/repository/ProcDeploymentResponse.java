package com.asset.rest.service.api.repository;

/**
 * @author lichao
 */
public class ProcDeploymentResponse {

    protected String deployId;
    protected String deployName;

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public String getDeployName() {
        return deployName;
    }

    public void setDeployName(String deployName) {
        this.deployName = deployName;
    }
}
