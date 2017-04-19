package org.companyLog.service.impl;


import java.util.List;

import org.companyLog.bean.LogGroup;
import org.companyLog.dao.LogGroupMapper;
import org.companyLog.service.LogGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LogGroupServiceImpl implements LogGroupService {
    private static final Logger LOG = LoggerFactory.getLogger(LogGroupServiceImpl.class);
    @Autowired
    private LogGroupMapper lgMapper;
	@Override
	public List<LogGroup> selectAll() {
		// TODO Auto-generated method stub
		return lgMapper.selectAll();
	}

    
}
