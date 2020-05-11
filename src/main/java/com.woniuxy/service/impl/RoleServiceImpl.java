package com.woniuxy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.woniuxy.dao.PermssionMapper;
import com.woniuxy.dao.RoleMapper;
import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.RoleService;
import com.woniuxy.util.DataGridView;
import com.woniuxy.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermssionMapper permissionMapper;
    @Override
    public List<Role> findAllRoles() {
        return roleMapper.selectAllRoles();
    }

    @Override
    public List<Role> selectRolesByUid(int uid) {
        return this.roleMapper.selectRolesByUid(uid);
    }
    @Override
    public DataGridView selectRolesByPage(RoleVo roleVo) {
        Page<Object> page= PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<Role> roleList=roleMapper.selectAllRoles();
        return new DataGridView(page.getTotal(),roleList);
    }

    @Override
    public List<Permission> selectAllPersmission() {
        return permissionMapper.selectAllPermissions();
    }

    @Override
    public Integer[] selectPerIdsByRid(int rid) {
        List<Permission> permissionList=this.permissionMapper.selectPermissionsByRid(rid);
        Integer[] permissionIds=new Integer[permissionList.size()];
        for(int i=0;i<permissionList.size();i++){
            Permission perItem=(Permission)permissionList.get(i);
            permissionIds[i]=perItem.getId();
        }
        return permissionIds;
    }
    @Transactional
    @Override
    public void assignRight(int rid, Integer[] perids) {
        this.roleMapper.deleteRolePermission(rid);
        if(perids!=null&&perids.length>0){
            for(Integer perid:perids){
                this.roleMapper.insertRolePermission(rid,perid);
            }
        }
    }
}
