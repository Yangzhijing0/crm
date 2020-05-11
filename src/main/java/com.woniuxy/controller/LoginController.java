package com.woniuxy.controller;

import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.Constant;
import com.woniuxy.util.ResultDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/login.do")
    public ResultDto login(String telephone,String password){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(telephone,password);
        try {
            subject.login(token);
            if(subject.isAuthenticated()){
                User user=this.userService.findUserByTel(telephone);
                //获取Session对象
                Session session=subject.getSession();
                session.setAttribute("USER_SESSION",user);
                return ResultDto.LOGIN_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.LOGIN_FAILURE;
        }
        return null;
    }
}
