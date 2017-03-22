package org.companyLog.service;

import java.util.List;

import org.companyLog.bean.LogLimitRole;


public interface LogLimitRoleService {

    
    int save(LogLimitRole lr);
    
    int deleteByLogIdAndRoleId(String logId,String roleId);
    
    int deleteByLogId(String logId);
    
    int deleteByRoleId(String roleId);
    
    List<LogLimitRole> getListByLogId(String logId);
    
}
