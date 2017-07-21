package com.espark.adarsh.repository;

import com.espark.adarsh.entities.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by akumar6 on 8/2/16.
 */
@Repository("permissionRepository")
public class PermissionRepositoryImpl implements PermissionRepository {

    @Override
    public boolean savePermission(Permission permission) {
        return false;
    }

    @Override
    public boolean updatePermission(Permission permission) {
        return false;
    }

    @Override
    public boolean deletePermission(Integer permissionId) {
        return false;
    }

    @Override
    public Permission getPermission(Integer permissionId) {
        return null;
    }

    @Override
    public List<Permission> getAllPermission() {
        return null;
    }
}
