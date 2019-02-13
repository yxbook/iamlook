package com.iamlook.um.config;

import com.iamlook.um.filter.AnyRolesAuthorizationFilter;
import com.iamlook.um.filter.JwtAuthFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(SecurityManager securityManager) throws Exception{
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter((Filter)shiroFilter(securityManager).getObject());
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ASYNC);

        return filterRegistration;
    }

    //配置核心安全事务管理器
    @Bean
    public SessionsSecurityManager securityManager() {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealms(Arrays.asList(jwtShiroRealm(), dbShiroRealm()));
        return manager;
    }

    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    @Bean(name="dbRealm")
    public Realm dbShiroRealm() {
        DbShiroRealm myShiroRealm = new DbShiroRealm();
        return myShiroRealm;
    }

    @Bean(name="jwtRealm")
    public Realm jwtShiroRealm() {
        JWTShiroRealm myShiroRealm = new JWTShiroRealm();
        return myShiroRealm;
    }



    /**
     * 设置过滤器
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    	ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = factoryBean.getFilters();
        filterMap.put("authcToken", createAuthFilter());
        filterMap.put("anyRole", createRolesFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return factoryBean;
    }

    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/api/iamlook/login", "noSessionCreation,anon");
        chainDefinition.addPathDefinition("/api/iamlook/logout", "noSessionCreation,anon");
        chainDefinition.addPathDefinition("/logout", "noSessionCreation,authcToken[permissive]");
        chainDefinition.addPathDefinition("/image/**", "anon");  
        chainDefinition.addPathDefinition("/admin/**", "noSessionCreation,authcToken,anyRole[admin,manager]"); //只允许admin或manager角色的用户访问
        chainDefinition.addPathDefinition("/article/list", "noSessionCreation,authcToken");
        chainDefinition.addPathDefinition("/article/*", "noSessionCreation,authcToken[permissive]");
        chainDefinition.addPathDefinition("/**", "noSessionCreation,authcToken");
        return chainDefinition;
    }

    protected JwtAuthFilter createAuthFilter(){
        return new JwtAuthFilter();
    }

    protected AnyRolesAuthorizationFilter createRolesFilter(){
        return new AnyRolesAuthorizationFilter();
    }

}
