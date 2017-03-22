package org.companyLog.service;

import java.util.List;
import java.util.Map;

import org.companyLog.bean.User;


public interface UserService {
    User getUser(String id);
    
    User getUserByUsername(String username);
    
    List<User> getUserByPermissionCode(String permissionCode);
    
    List<User> getAllUserWithRole(Integer index,Integer rows,Map<String,String> eqCondition);
    
    int getAllUserCount(Map<String,String> eqCondition);
   
    User getUserByPhone(String phone);
    
    int save(User user);
    
    int update(User user);
    
    int delete(String id);
    
    int updatePasswordById(User user);
    
}
