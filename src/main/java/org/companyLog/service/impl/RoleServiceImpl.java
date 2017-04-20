package org.companyLog.service.impl;


import java.util.List;
import java.util.Map;

import org.companyLog.bean.Role;
import org.companyLog.dao.RoleMapper;
import org.companyLog.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleMapper roleMapper;
	@Override
	public List<Role> getAllRole(Integer index,Integer rows,Map<String,String> likeCondition) {
		// TODO Auto-generated method stub
		return roleMapper.queryAllRole(index,rows,likeCondition);
	}
	@Override
	public Role getRoleByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return roleMapper.selectByRoleName(roleName);
	}
	@Override
	public int insertRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.insert(role);
	}
	@Override
	public Role getOneRoleDetail(String id) {
		// TODO Auto-generated method stub
		return roleMapper.queryOneRole(id);
	}
	
	public int updateRoleByPrimaryKey(Role role){
		return roleMapper.updateByPrimaryKey(role);
	}
	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int getAllRoleCount(Map<String, String> likeCondition) {
		// TODO Auto-generated method stub
		return roleMapper.queryAllRoleCount(likeCondition);
	}
    
    
}
