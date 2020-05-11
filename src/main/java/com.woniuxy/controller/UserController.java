package com.woniuxy.controller;

import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.service.RoleService;
import com.woniuxy.service.UserService;
import com.woniuxy.util.DataGridView;
import com.woniuxy.util.ResultDto;
import com.woniuxy.vo.RoleVo;
import com.woniuxy.vo.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController extends BaseController{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/users")
    public DataGridView selectUsersByPage(UserVo userVo){
        logger.debug("当前页:"+userVo.getPage());
        logger.debug("每页的记录数:"+userVo.getLimit());
        logger.debug("搜索框的信息:"+userVo.getRealname());
        return userService.selectUsersByPage(userVo);
    }
    @DeleteMapping(value = "/users/{id}")
    public ResultDto deleteUser(@PathVariable("id") int id){
        try {
            this.userService.removeUserById(id);
            return ResultDto.DELETE_USER_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.DELETE_USER_FAILURE;
        }
    }
    @GetMapping(value = "users/deleteBatch")
    public ResultDto deleteBathUser(@RequestParam("ids[]") Integer[] ids){
        try {
            this.userService.removeBatchUser(ids);
            return ResultDto.DELETE_USER_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.DELETE_USER_FAILURE;
        }
    }
    @PostMapping(value = "/users.do")
    public ResultDto addUser(User user){
        logger.debug("手机号:"+user.getTelephone());
        logger.debug("密码:"+user.getPassword());
        logger.debug("姓名:"+user.getRealname());
        logger.debug("生日:"+user.getBirthday());
        logger.debug("图片:"+user.getHeadimg());
        logger.debug("部门；"+user.getDept().getId());
        logger.debug("状态:"+user.getAvailable());
        Object result=new SimpleHash("md5",user.getPassword(),null,1024);
        user.setPassword(result.toString());
        User userResult=this.userService.findUserByTel(user.getTelephone());
        if(userResult!=null) {
            return ResultDto.USER_EXITS;
        }else {
            boolean flag=this.userService.addUser(user);
            if (flag) {
                return ResultDto.USER_ADD_SUCCESS;
            } else {
                return ResultDto.USER_ADD_FAILURE;
            }
        }
    }
    @GetMapping("users/initRolesByUid")
    public DataGridView initRolesByUid(int uid){
        logger.debug("用户id:"+uid);
        //查询所有的角色集合
        List<Role> roleList=roleService.findAllRoles();
        //通过用户Id查询该用户所拥有的角色对象集合
        List<Role> roles=this.roleService.selectRolesByUid(uid);
        List<Role> newsRoleList=new ArrayList<>();
        for(Role role:roleList){
            boolean LAY_CHECKED=false;
            for(Role roleItem:roles){
                if(role.getId()==roleItem.getId()){
                    LAY_CHECKED=true;
                    break;
                }
            }
            RoleVo roleBean=new RoleVo();
            roleBean.setId(role.getId());
            roleBean.setRolename(role.getRolename());
            roleBean.setLAY_CHECKED(LAY_CHECKED);
            newsRoleList.add(roleBean);
        }
        return new DataGridView(Long.valueOf(newsRoleList.size()),newsRoleList);
    }
    @GetMapping(value = "/users/grantRole.do")
    public ResultDto grantRole(int uid,@RequestParam("rids[]") int[] rids){
        logger.debug("uid:"+uid);
        logger.debug("rids:"+rids);
        try {
            this.userService.grantRole(uid,rids);
            return ResultDto.ROLE_GRANT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.ROLE_GRANT_FAILURE;
        }
    }
}
