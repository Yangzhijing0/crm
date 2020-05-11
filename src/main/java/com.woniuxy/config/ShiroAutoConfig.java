package com.woniuxy.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.woniuxy.realm.MyRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroAutoConfig {
    private String hashAlgorithmName;
    private int hashIterations;
    private String loginUrl;
    private String unauthorizedUrl;
    private String successUrl;
    private String[] anonUrls;
    private String[] authcUrls;
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(hashIterations);
        return hashedCredentialsMatcher;
    }
    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm=new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        Map<String,String> filterChainDefinitionMap=new HashMap<>();
        if(isArrayIsEmpty(anonUrls)){
            for(String anonUrl:anonUrls){
                filterChainDefinitionMap.put(anonUrl,"anon");
            }
        }
        if(isArrayIsEmpty(authcUrls)){
            for(String authcUrl:authcUrls){
                filterChainDefinitionMap.put(authcUrl,"authc");
            }
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> getDelegatingFilterProxy(){
        DelegatingFilterProxy filterProxy=new DelegatingFilterProxy();
        filterProxy.setTargetBeanName("shiroFilter");
        FilterRegistrationBean bean=new FilterRegistrationBean();
        List<String> urlPatterns=new ArrayList<>();
        urlPatterns.add("*.do");
        bean.setUrlPatterns(urlPatterns);
        bean.setFilter(filterProxy);
        return bean;
    }
    //如果要让注解生效必须要添加如下配置
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean("shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    /**
     * 判断数组是否为空
     * @param arrays
     * @return
     */
    private boolean isArrayIsEmpty(String[] arrays){
        if(arrays!=null&&arrays.length>0){
            return true;
        }else{
            return false;
        }
    }
}
