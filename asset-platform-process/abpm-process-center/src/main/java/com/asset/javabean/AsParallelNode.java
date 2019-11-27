package com.asset.javabean;

import lombok.Data;

/**
 *并行网关节点
 */
@Data
public class AsParallelNode {
    String id;  //节点的id
    int type; //4表示开始并行网关，5表示结束并行网关
    String peerNodeId;   //如果当前网关是开始并行网关，那么这个id代表的就是另一个结束并行网关的id
    int outNums;  //如果该节点是开始并行网关，该数据表示出度数目

    public AsParallelNode(String id, int type,int outNums) {
        this.id = id;
        this.type = type;
        this.outNums = outNums;
    }

    private AsParallelNode(Builder builder) {
        setId(builder.id);
        setType(builder.type);
        setPeerNodeId(builder.peerNodeId);
        setOutNums(builder.outNums);
    }


    public static final class Builder {
        private String id;
        private int type;
        private String peerNodeId;
        private int outNums;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder peerNodeId(String val) {
            peerNodeId = val;
            return this;
        }

        public Builder outNums(int val) {
            outNums = val;
            return this;
        }

        public AsParallelNode build() {
            return new AsParallelNode(this);
        }
    }
}
