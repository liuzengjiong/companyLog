package org.companyLog.service;

import java.util.List;
import java.util.Map;

import org.companyLog.bean.Role;


public interface RoleService {
    
   List<Role> getAllRole(Integer index,Integer rows,Map<String,String> likeCondition);
   
   int getAllRoleCount(Map<String,String> likeCondition);
   
   Role getRoleByRoleName(String roleName);
   
   int insertRole(Role role);
   
   Role getOneRoleDetail(String id);
   
   int updateRoleByPrimaryKey(Role role);
   
   int deleteById(String id);
}
