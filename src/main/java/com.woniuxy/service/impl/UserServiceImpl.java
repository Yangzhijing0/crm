package com.woniuxy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.woniuxy.dao.PermssionMapper;
import com.woniuxy.dao.RoleMapper;
import com.woniuxy.dao.UserMapper;
import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.DataGridView;
import com.woniuxy.util.TreeNode;
import com.woniuxy.util.TreeNodeBuilder;
import com.woniuxy.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermssionMapper permssionMapper;
    @Override
    public List<User> findAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public User login(String telephone, String password) {
        return userMapper.selectUserByTelAndPwd(telephone,password);
    }
    @Transactional
    @Override
    public boolean addUser(User user) {
        boolean flag=false;
        int count=this.userMapper.insertSysUser(user);
        if(count>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public User findUserByTel(String telephone) {
        return this.userMapper.selectUserByTel(telephone);
    }

    @Override
    public Set<String> selectRoleNamesByUid(int uid) {
        return roleMapper.selectRoleNamesByUid(uid);
    }

    @Override
    public Set<String> selectPercodesByUid(int uid) {
        return permssionMapper.selectPercodesByUid(uid);
    }

    @Override
    public List<TreeNode> selectMenusByUid(int uid) {
        List<TreeNode> treeNodes=new ArrayList<TreeNode>();
        List<Permission> permissionList=this.permssionMapper.selectMenusByUid(uid);
        for(Permission permission:permissionList){
            TreeNode treeNode=new TreeNode();
            treeNode.setId(permission.getId());
            treeNode.setPid(permission.getPid());
            treeNode.setTitle(permission.getName());
            treeNode.setIcon(permission.getIcon());
            treeNode.setHref(permission.getHref());
            treeNode.setSpread(permission.getOpen()==1?true:false);
            treeNodes.add(treeNode);
        }
        return TreeNodeBuilder.build(treeNodes,1);
    }

    @Override
    public DataGridView selectUsersByPage(UserVo userVo) {
        Page<Object> page=PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<User> userList=this.userMapper.selectUsersByCondition(userVo);
        return new DataGridView(page.getTotal(),userList);
    }
    @Transactional
    @Override
    public void removeUserById(int id) {
        this.userMapper.deleteUserRoleByUid(id);
        this.userMapper.deleteUserById(id);
    }
    @Transactional
    @Override
    public void removeBatchUser(Integer[] ids) {
        if(ids.length>0){
            for(Integer id:ids){
                this.removeUserById(id);
            }
        }
    }

    /**
     * 重新分配角色
     * @param uid
     * @param rids
     */
    @Transactional
    @Override
    public void grantRole(int uid, int[] rids) {
        this.userMapper.deleteUserRoleByUid(uid);
        if(rids!=null&&rids.length>0){
            for(Integer rid:rids){
                this.userMapper.insertSysUserRole(uid,rid);
            }
        }
    }
}
