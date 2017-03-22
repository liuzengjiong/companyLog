package org.companyLog.service;

import java.util.List;

import org.companyLog.bean.Permission;


public interface PermissionService {
    
   List<Permission> getAllPermission();
   
   int insert(Permission permission);
   
   int delete(String id);
}
