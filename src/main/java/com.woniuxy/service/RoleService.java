package com.woniuxy.service;

import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Role;
import com.woniuxy.util.DataGridView;
import com.woniuxy.vo.RoleVo;

import java.util.List;

public interface RoleService {
    public List<Role> findAllRoles();
    public List<Role> selectRolesByUid(int uid);
    public DataGridView selectRolesByPage(RoleVo roleVo);
    public List<Permission> selectAllPersmission();
    public Integer[] selectPerIdsByRid(int rid);
    public void assignRight(int rid,Integer[] perids);
}
