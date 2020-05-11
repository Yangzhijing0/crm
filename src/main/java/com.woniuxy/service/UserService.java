package com.woniuxy.service;

import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.User;
import com.woniuxy.util.DataGridView;
import com.woniuxy.util.TreeNode;
import com.woniuxy.vo.UserVo;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> findAllUsers();

    /**
     * 登录方法
     * @param telephone
     * @param password
     * @return
     */
    public User login(String telephone, String password);

    /**
     * 增加用户
     * @param user
     * @return
     */
    public boolean addUser(User user);

    /**
     * 通过手机号码查询用户对象
     * @param telephone
     * @return
     */

    public User findUserByTel(String telephone);
    /**
     * 通过用户Id查询角色集合
     * @param uid
     * @return
     */
    public Set<String> selectRoleNamesByUid(int uid);
    /**
     * 通过用户Id查询权限名称集合
     * @param uid
     * @return
     */
    public Set<String> selectPercodesByUid(int uid);

    /**
     * 通过用户id获取用户的菜单，用于后台首页的左侧的动态菜单的生成
     * @param uid
     * @return
     */
    public List<TreeNode> selectMenusByUid(int uid);

    /**
     * 组合分页查询
     * @param userVo
     * @return
     */
    public DataGridView selectUsersByPage(UserVo userVo);

    /**
     * 删除用户信息
     * @param id
     */
    public void removeUserById(int id);

    /**
     * 批量删除用户
     * @param ids
     */
    public void removeBatchUser(Integer[] ids);

    /**
     * 分配角色
     * @param uid
     * @param rids
     */
    public void grantRole(int uid,int[] rids);

}























