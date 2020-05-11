package com.woniuxy.dao;

import com.woniuxy.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermssionMapper {
    public List<Permission> selectAllPermissions();

    /**
     * 通过角色id查询权限集合
     * @param rid
     * @return
     */
    public List<Permission> selectPermissionsByRid(int rid);

    /**
     * 通过用户Id查询权限名称集合
     * @param uid
     * @return
     */
    public Set<String> selectPercodesByUid(int uid);

    /**
     * 通过用户id查找所拥有的菜单集合
     * @param uid
     * @return
     */
    public List<Permission> selectMenusByUid(int uid);
}


















