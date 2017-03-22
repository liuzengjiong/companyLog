package org.companyLog.service;

import java.util.List;

import org.companyLog.bean.Log;
import org.companyLog.bean.LogShower;


public interface LogService {
    
	List<LogShower> getLogCollection(String userId,int index,int rows);
	
	int getLogCollectionCount(String userId);
	
	List<LogShower> getLogByAuthor(String authorId,int index,int rows,String userId);
	
	int getLogCountByAuthor(String authorId,String userId);
	
	List<LogShower> getLogOfAll(int index,int rows,String userId,String keyword);
	
	int getLogCount(String userId,String keyword);
	
	LogShower getLogById(String id,String userId);
	
	int update(Log log);
	
	int save(Log log);
	
	int addReadNum(String id);

	List<LogShower> getWorkLogByDate(String authorId, int index, int rows,
			String beginDate, String endDate);

	int getWorkLogCountByDate(String authorId, String beginDate, String endDate);

}
