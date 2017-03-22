package org.companyLog.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.companyLog.bean.Report;
import org.companyLog.bean.ReportShower;
import org.companyLog.dao.ReportMapper;
import org.companyLog.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);
   
    private String queryColums =  "r.id, author_id, receiver_id, begin_date, end_date, work_contet, work_summary, "
		      +  "self_evaluation, leader_evaluation, submit_time, update_time, state"
		       + ",u1.nickname as authorName,u2.nickname as receiverName";
		
    
    @Autowired
    private ReportMapper reportMapper;
	@Override
	public List<ReportShower> getReportByReceiver(int index,int rows,String receiverId) {
		String table = "report r,user u1,user u2";
		String colums = queryColums;
		String order = "state asc,submit_time desc";
		Map<String,String> eqCondition = new HashMap<String,String>();
		eqCondition.put("receiver_id", receiverId);
		eqCondition.put("r.author_id", "u1.id");
		eqCondition.put("r.receiver_id", "u2.id");
		return reportMapper.getReportPage(table, colums, order, index+"", rows+"", eqCondition, null);
	}
	@Override
	public Report getReportById(String id) {
		// TODO Auto-generated method stub
		return reportMapper.selectByPrimaryKey(id);
	}
	@Override
	public int update(Report report) {
		// TODO Auto-generated method stub
		return reportMapper.updateByPrimaryKey(report);
	}
	@Override
	public int save(Report report) {
		// TODO Auto-generated method stub
		return reportMapper.insert(report);
	}
	@Override
	public List<ReportShower> getReportByAuthor(int index,int rows,String authorId) {
		// TODO Auto-generated method stub
		String table = "report r,user u1,user u2";
		String colums = queryColums;
		String order = "state desc,submit_time desc";
		Map<String,String> eqCondition = new HashMap<String,String>();
		eqCondition.put("author_id", authorId);
		eqCondition.put("r.author_id", "u1.id");
		eqCondition.put("r.receiver_id", "u2.id");
		return reportMapper.getReportPage(table, colums, order, index+"", rows+"", eqCondition, null);
	}
	@Override
	public int getReportCountByCondition(Map<String, String> eqCondition) {
		// TODO Auto-generated method stub
		String table = "report r";
		return reportMapper.getReportCount(table, eqCondition, null);
	}
    
    
}
