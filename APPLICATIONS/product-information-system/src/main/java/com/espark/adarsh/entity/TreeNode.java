package com.espark.adarsh.entity;

import java.util.List;

public interface TreeNode<T extends AbstractEntity> {

    public String getName();

    public Long getId();

    public Long getParent();

    public AbstractEntity.Type getType();

    public Boolean getActive();

    public String getPath();

    public List<T> getChildren();
}
