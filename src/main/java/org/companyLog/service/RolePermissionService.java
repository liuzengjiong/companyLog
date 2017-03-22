package org.companyLog.service;

import org.companyLog.bean.RolePermission;


public interface RolePermissionService {
    
   
   int insert(RolePermission role);
   
   int deleteByRoleAndPermissionId(String roleId,String permissionId);
   
   int deleteByRoleId(String roleId);
}
