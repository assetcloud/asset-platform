package com.asset.entity;


import lombok.Data;
//nfq:2019/10/11


@Data
public class CommitFormInstDO {
    String userID;
    String sceneID;
    String procinstID;
    public CommitFormInstDO(){

    }
    public CommitFormInstDO(String userID,String sceneID){
        this.userID = userID;
        this.sceneID = sceneID;
    }
}
