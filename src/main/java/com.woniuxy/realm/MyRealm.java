package com.woniuxy.realm;

import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(MyRealm.class);

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo授权时调用");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //获取手机号
        String telephone=(String)principalCollection.getPrimaryPrincipal();
        User user=userService.findUserByTel(telephone);
        if(user!=null){
            Set<String> roleNames=userService.selectRoleNamesByUid(user.getId());
            Set<String> percodes=userService.selectPercodesByUid(user.getId());
            authorizationInfo.addRoles(roleNames);
            authorizationInfo.addStringPermissions(percodes);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAuthenticationInfo authenticationInfo=null;
        logger.info("doGetAuthorizationInfo认证时调用");
        //获取手机号码
        String telephone=(String) authenticationToken.getPrincipal();
        User user=userService.findUserByTel(telephone);
        if(user!=null){
            authenticationInfo=new SimpleAuthenticationInfo(telephone,user.getPassword(),getName());
        }
        return authenticationInfo;
    }
}
