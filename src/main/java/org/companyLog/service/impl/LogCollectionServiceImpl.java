package org.companyLog.service.impl;


import org.companyLog.bean.LogCollection;
import org.companyLog.dao.LogCollectionMapper;
import org.companyLog.service.LogCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogCollectionServiceImpl implements LogCollectionService {
    private static final Logger LOG = LoggerFactory.getLogger(LogCollectionServiceImpl.class);
    @Autowired
    private LogCollectionMapper lcMapper;
	@Override
	public int insert(LogCollection lc) {
		// TODO Auto-generated method stub
		return lcMapper.insert(lc);
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return lcMapper.deleteByPrimaryKey(id);
	}
	@Override
	public LogCollection getByLogIdAndUserId(String logId, String userId) {
		// TODO Auto-generated method stub
		return lcMapper.selectByLogIdAndUserId(logId, userId);
	}

    
    
}
