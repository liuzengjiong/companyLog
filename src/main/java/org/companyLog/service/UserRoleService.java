package org.companyLog.service;

import org.companyLog.bean.UserRole;


public interface UserRoleService {

    
    int save(UserRole ur);
    
    int deleteByUserIdAndRoleId(String userId,String roleId);
    
    int deleteByUserId(String userId);
    
    int deleteByRoleId(String roleId);
    
}
