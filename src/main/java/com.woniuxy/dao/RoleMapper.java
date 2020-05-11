package com.woniuxy.dao;

import com.woniuxy.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    /**
     * 通过用户id查询角色集合
     * @param uid
     * @return
     */
    public List<Role> selectRolesByUid(int uid);

    /**
     * 查询所有的角色
     * @return
     */
    public List<Role> selectAllRoles();

    /**
     * 通过权限id，查找到角色id的集合
     * @param perid
     * @return
     */
    public List<Role> selectRolesByPerid(int perid);

    /**
     * 通过用户Id查询角色集合
     * @param uid
     * @return
     */
    public Set<String> selectRoleNamesByUid(int uid);

    /**
     * 增加角色权限中间表数据
     * @param rid
     * @param perid
     */
    public void insertRolePermission(@Param("rid") int rid, @Param("perid") int perid);

    /**
     * 通过rid删除角色权限中间表的数据
     * @param rid
     */
    public void deleteRolePermission(int rid);

}
