package com.asset.utils;

import java.util.List;

public interface RNode {

    Long getId();

    Long getParentId();

    List<RNode> getChildren();
}
