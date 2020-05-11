package com.woniuxy.controller;

import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.TreeNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/index.do")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/initIndexMenu.do")
    @ResponseBody
    public List<TreeNode> initIndexMenu(){
        Subject subject= SecurityUtils.getSubject();
        Session session=subject.getSession();
        User user=(User)session.getAttribute("USER_SESSION");
        List<TreeNode> treeNodes=userService.selectMenusByUid(user.getId());
        return treeNodes;
    }

    @RequestMapping(value = "/toUserManager.do")
    public String toUserManager(){
        return "user/userList";
    }
    @RequestMapping(value = "/toUserAddPage.do")
    public String toUserAddPage(){
        return "user/userAdd";
    }
    @RequestMapping(value = "/toUserRolePage.do")
    public String toUserRolePage(){
        return "user/userRole";
    }
    @RequestMapping(value = "/toRoleManager.do")
    public String toRoleManager(){
        return "role/roleList";
    }
    @RequestMapping(value = "/toAssignRightPage.do")
    public String toAssignRightPage(){
        return "role/assignRight";
    }
    @RequestMapping(value = "/toUserEidtPage.do")
    public String toUserEditPage(){
        return "user/userEdit";
    }
}
