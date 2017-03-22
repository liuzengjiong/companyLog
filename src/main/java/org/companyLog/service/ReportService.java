package org.companyLog.service;

import java.util.List;
import java.util.Map;

import org.companyLog.bean.Report;
import org.companyLog.bean.ReportShower;


public interface ReportService {
    
	List<ReportShower> getReportByReceiver(int index,int rows,String receiverId);
	
	List<ReportShower> getReportByAuthor(int index,int rows,String authorId);
	
	int getReportCountByCondition(Map<String,String> eqCondition);
	
	Report getReportById(String id);
	
	int update(Report log);
	
	int save(Report log);
}
