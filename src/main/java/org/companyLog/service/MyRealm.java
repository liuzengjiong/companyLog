package org.companyLog.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.companyLog.bean.Permission;
import org.companyLog.bean.Role;
import org.companyLog.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @TODO：
 * @fileName : org.companyLog.service.MyRealm.java
 * date | author | version |   
 * 2017年3月11日 | Jiong | 1.0 |
 */
  
@Service  
@Transactional  
public class MyRealm extends AuthorizingRealm{  
  
	@Autowired
    private UserService userService;  
    /** 
     * 权限认证 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {  
    	//获取登录时输入的用户名  
        String userName=(String) principalCollection.fromRealm(getName()).iterator().next();  
        //到数据库查是否有此对象  
        User user=userService.getUserByUsername(userName);  
        if(user!=null){  
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
            //用户的角色集合  
            Set<String> permissions = new HashSet<String>();
            List<Role> roles = user.getRoles();
            for(Role role : roles){
            	for(Permission perm : role.getPermissions()){
            		permissions.add(perm.getPermissionCode());
            	}
            }
//            info.setRoles(rolenames);  
            //用户的角色对应的所有权限 
    
            info.addStringPermissions(permissions);  
            return info;  
        }  
        return null;  
    }  
  
    /** 
     * 登录认证; 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息  
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;  
        
        //查出是否有此用户  
        User user=userService.getUserByUsername(token.getUsername()); 
        System.out.println(user==null?"null":user.getNickname()+"+"+String.valueOf(user.getPassword()));
        
        if(user!=null){  
        	System.out.println("用户角色："+user.getRoles().size());
        	for(Role role:user.getRoles()){
        		System.out.println(role.getId()+"-"+role.getRoleName());
        		for(Permission p:role.getPermissions()){
        			System.out.println("'"+p.getPermissionName()+"'");
        		}
        	}
            Subject currentUser = SecurityUtils.getSubject(); 
            Session session = currentUser.getSession();
            String password = user.getPassword();
            user.setPassword("");
            session.setAttribute("user",user );
            //若存在，将此用户存放到登录认证info中  
             return new SimpleAuthenticationInfo(user.getUsername(), password, getName());  
        }  
        return null;  
    }  
  
}  