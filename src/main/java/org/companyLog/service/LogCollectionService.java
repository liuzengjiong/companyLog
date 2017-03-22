package org.companyLog.service;

import org.companyLog.bean.LogCollection;




public interface LogCollectionService {
    
	int insert(LogCollection lc);
	
	int delete(String id);
	
	LogCollection getByLogIdAndUserId(String logId,String userId);
	
}
