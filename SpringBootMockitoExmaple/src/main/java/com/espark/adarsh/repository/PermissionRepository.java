package com.espark.adarsh.repository;

import com.espark.adarsh.entities.Permission;

import java.util.List;

/**
 * Created by akumar6 on 8/2/16.
 */
public interface PermissionRepository {

    public boolean savePermission(Permission permission);

    public boolean updatePermission(Permission permission);

    public boolean deletePermission(Integer permissionId);

    public Permission getPermission(Integer permissionId);

    public List<Permission> getAllPermission();
}
