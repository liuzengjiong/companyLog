package org.companyLog.service.impl;


import org.companyLog.bean.RolePermission;
import org.companyLog.dao.RolePermissionMapper;
import org.companyLog.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    private static final Logger LOG = LoggerFactory.getLogger(RolePermissionServiceImpl.class);
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
	@Override

	public int insert(RolePermission rolePermission) {
		// TODO Auto-generated method stub
		return rolePermissionMapper.insert(rolePermission);
	}
	@Override
	public int deleteByRoleAndPermissionId(String roleId, String permissionId) {
		// TODO Auto-generated method stub
		return rolePermissionMapper.deleteByRoleIdAndPermissionId(roleId, permissionId);
	}
	@Override
	public int deleteByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return rolePermissionMapper.deleteByRoleId(roleId);
	}
    
    
}
