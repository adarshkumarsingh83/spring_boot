package com.espark.adarsh.entities;

/**
 * Created by akumar6 on 8/2/16.
 */
public class Permission {

    private Integer permissionId;
    private String permissionName;

    public Permission() {
    }

    public Permission(Integer permissionId, String permissionName) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
