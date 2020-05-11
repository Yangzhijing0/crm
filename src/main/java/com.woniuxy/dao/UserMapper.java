package com.woniuxy.dao;

import com.woniuxy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> selectAllUsers();
    /**
     * 通过角色的Id查询到用户的集合
     * @param rid
     * @return
     */
    public List<User> selectUsersByRid(int rid);

    /**
     * 通过部门id查找该部门的所有用户
     * @param did
     * @return
     */
    public List<User> selectUsersByDid(int did);

    /**
     * 通过用户名和密码进行查询用户对象
     * @param telephone
     * @param password
     * @return
     */
    public User selectUserByTelAndPwd(@Param("telephone") String telephone, @Param("password") String password);

    /**
     * 向sys_user表中添加数据
     * @param user
     * @return
     */
    public int insertSysUser(User user);

    /**
     * 向sys_user_role中间表增加数据
     * @param uid
     * @param rid
     */
    public void insertSysUserRole(@Param("uid") int uid, @Param("rid") int rid);

    /**
     * 通过手机号码查询用户对象
     * @return
     */

    public User selectUserByTel(String telephone);

    /**
     * 通过条件查询用户信息列表
     * @param user
     * @return
     */
    public List<User> selectUsersByCondition(User user);

    /**
     * 通过Id删除用户
     * @param id
     */
    public void deleteUserById(int id);

    /**
     * 通过uid删除用户角色中间表数据
     * @param uid
     */
    public void deleteUserRoleByUid(int uid);
}






















