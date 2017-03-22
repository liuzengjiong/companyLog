package org.companyLog.service.impl;


import org.companyLog.bean.UserRole;
import org.companyLog.dao.UserMapper;
import org.companyLog.dao.UserRoleMapper;
import org.companyLog.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl implements UserRoleService {
    private static final Logger LOG = LoggerFactory.getLogger(UserRoleServiceImpl.class);
    @Autowired
    private UserRoleMapper urMapper;
	@Override
	public int save(UserRole ur) {
		// TODO Auto-generated method stub
		return urMapper.insert(ur);
	}
	@Override
	public int deleteByUserIdAndRoleId(String userId, String roleId) {
		// TODO Auto-generated method stub
		return urMapper.deleteByUserIdAndRoleId(userId, roleId);
	}
	@Override
	public int deleteByUserId(String userId) {
		// TODO Auto-generated method stub
		return urMapper.deleteByUserId(userId);
	}
	@Override
	public int deleteByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return urMapper.deleteByRoleId(roleId);
	}
}
