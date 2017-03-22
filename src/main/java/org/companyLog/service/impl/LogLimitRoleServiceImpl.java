package org.companyLog.service.impl;


import java.util.List;

import org.companyLog.bean.LogLimitRole;
import org.companyLog.bean.UserRole;
import org.companyLog.dao.LogLimitRoleMapper;
import org.companyLog.service.LogLimitRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogLimitRoleServiceImpl implements LogLimitRoleService {
    private static final Logger LOG = LoggerFactory.getLogger(LogLimitRoleServiceImpl.class);
    @Autowired
    private LogLimitRoleMapper lrMapper;
    
	@Override
	public int save(LogLimitRole lr) {
		// TODO Auto-generated method stub
		return lrMapper.insert(lr);
	}
	@Override
	public int deleteByLogIdAndRoleId(String logId, String roleId) {
		// TODO Auto-generated method stub
		return lrMapper.deleteByLogIdAndRoleId(logId, roleId);
	}
	@Override
	public int deleteByLogId(String logId) {
		// TODO Auto-generated method stub
		return lrMapper.deleteByLogId(logId);
	}
	@Override
	public int deleteByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return lrMapper.deleteByRoleId(roleId);
	}
	@Override
	public List<LogLimitRole> getListByLogId(String logId) {
		// TODO Auto-generated method stub
		return lrMapper.selectByLogId(logId);
	}
    
}
