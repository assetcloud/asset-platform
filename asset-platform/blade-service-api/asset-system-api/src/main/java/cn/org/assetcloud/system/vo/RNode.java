package cn.org.assetcloud.system.vo;

import java.util.List;

public interface RNode {

    Long getId();

    Long getParentId();

    List<RNode> getChildren();
}
