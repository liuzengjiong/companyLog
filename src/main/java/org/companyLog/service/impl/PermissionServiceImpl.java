package org.companyLog.service.impl;


import java.util.List;

import org.companyLog.bean.Permission;
import org.companyLog.dao.PermissionMapper;
import org.companyLog.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    private PermissionMapper permissionMapper;
	@Override
	public List<Permission> getAllPermission() {
		// TODO Auto-generated method stub
		return permissionMapper.selectAll();
	}
	@Override
	public int insert(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.insert(permission);
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return permissionMapper.deleteByPrimaryKey(id);
	}
    
    
}
