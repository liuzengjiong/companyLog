package org.companyLog.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.companyLog.bean.Log;
import org.companyLog.bean.LogShower;
import org.companyLog.dao.LogMapper;
import org.companyLog.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LogServiceImpl implements LogService {
    private static final Logger LOG = LoggerFactory.getLogger(LogServiceImpl.class);
    @Autowired
    private LogMapper logMapper;
    
    private String getLimitId(String userId){
    	String limitId = 
    			"(SELECT lo.id from log lo,log_limit_role lr,role r,user_role ur "
    			+"WHERE (lo.is_limit_see=1 and lo.id = lr.log_id "
    			+"and lr.role_id = r.id and r.id = ur.role_id and "
    			+"ur.user_id = '"+userId+"' ) or lo.is_limit_see=0 "
    			+"or lo.author_id = '"+userId+"' "
    			+"group by lo.id)";
    	return limitId;
    }
    	
    
    
	@Override
	public LogShower getLogById(String id,String userId) {
		// TODO Auto-generated method stub
		return logMapper.selectByPrimaryKey(id,userId);
	}
	@Override
	public int update(Log log) {
		// TODO Auto-generated method stub
		return logMapper.updateByPrimaryKey(log);
	}
	@Override
	public int save(Log log) {
		// TODO Auto-generated method stub
		return logMapper.insert(log);
	}
	@Override
	public List<LogShower> getLogOfAll(int index, int rows,String userId,String keyword) {
		String keywordSearch = "";
		if(keyword!=null && keyword.length()>0){
			keywordSearch = " and l.title like '%"+keyword+"%' or l.content like '%"+keyword+"%' or u.nickname like '%"+keyword+"%' ";
		}
		System.out.println(keywordSearch);
		String sql ="select "
				+" l.id, l.author_id, l.is_limit_see, l.create_time, "
				+" l.update_time, l.title, l.group_id, l.read_num,summary, l.content,"
				+" ifnull(u.nickname,'已删除用户') as authorName,ifnull(count(c.id),'0') as commentNum "
				+" ,case when lc.id is null then false else true end as haveCollect "
				+" from log l"
				+" left join user u"
				+" 		on l.author_id=u.id "
				+" left join comment c "
				+" 		on c.log_id=l.id "
				+" left join log_collection lc"
        		+"		on l.id = lc.log_id and lc.user_id='"+userId+"' "
				+" where l.id in "+getLimitId(userId)
				+keywordSearch
				+" group by l.id"
				+" order by l.create_time desc"
				+" limit "+index+","+rows;
		// TODO Auto-generated method stub
//		return logMapper.getLogPage("log",null,"create_time desc",
//			index+"", rows+"",null,null);
		return logMapper.getLogPageBySql(sql);
	}
	@Override
	public int getLogCount(String userId,String keyword) {
		String keywordSearch = "";
		if(keyword!=null && keyword.length()>0){
			keywordSearch = " and l.title like '%"+keyword+"%' or l.content like '%"+keyword+"%' or u.nickname like '%"+keyword+"%' ";
		}
		
		String sql = "select count(1) from log l "
				+" left join user u"
				+" 		on l.author_id=u.id "
				+" where l.id in "+getLimitId(userId)
				+keywordSearch;
		// TODO Auto-generated method stub
		return logMapper.getLogCountBySql(sql);
	}
	
	@Override
	public List<LogShower> getLogByAuthor(String authorId,int index,int rows,String userId) {
		String sql ="select "
				+" l.id, l.author_id, l.is_limit_see, l.create_time, "
				+" l.update_time, l.title, l.group_id, l.read_num,summary, l.content,"
				+" ifnull(u.nickname,'已删除用户') as authorName,ifnull(count(c.id),'0') as commentNum "
				+" ,case when lc.id is null then false else true end as haveCollect "
				+" from log l"
				+" left join user u"
				+" 		on l.author_id=u.id "
				+" left join comment c "
				+" 		on c.log_id=l.id "
				+" left join log_collection lc"
        		+"		on l.id = lc.log_id and lc.user_id='"+userId+"' "
				+" where l.author_id="+authorId
				+" and l.id in "+getLimitId(userId)
				+" group by l.id"
				+" order by l.create_time desc"
				+" limit "+index+","+rows;
		return logMapper.getLogPageBySql(sql);
	}
	
	@Override
	public int getLogCountByAuthor(String authorId,String userId) {
		
		String sql = "select count(1) from log l "
				+"where l.author_id="+authorId
				+" and l.id in "+getLimitId(userId);
		// TODO Auto-generated method stub
		return logMapper.getLogCountBySql(sql);
	}



	@Override
	public List<LogShower> getLogCollection(String userId, int index, int rows) {
		String sql ="select "
				+" l.id, l.author_id, l.is_limit_see, l.create_time, "
				+" l.update_time, l.title, l.group_id, l.read_num,summary, l.content,"
				+" ifnull(u.nickname,'已删除用户') as authorName,ifnull(count(c.id),'0') as commentNum "
				+" from log_collection lc,log l "
				+" left join user u"
				+" 		on l.author_id=u.id "
				+" left join comment c "
				+" 		on c.log_id=l.id "
				+" where l.id = lc.log_id and lc.user_id='"+userId+"' "
				+" group by l.id"
				+" order by lc.create_time desc"
				+" limit "+index+","+rows;
		return logMapper.getLogPageBySql(sql);
	}



	@Override
	public int getLogCollectionCount(String userId) {
		String sql = "select count(1) from log l,log_collection lc "
				+" where l.id = lc.log_id and lc.user_id='"+userId+"' ";
		// TODO Auto-generated method stub
		return logMapper.getLogCountBySql(sql);
	}



	@Override
	public int addReadNum(String id) {
		// TODO Auto-generated method stub
		return logMapper.addReadNum(id);
	}
    
	@Override
	public List<LogShower> getWorkLogByDate(String authorId,int index,int rows,String beginDate,String endDate) {
		String sql ="select "
				+" l.id, l.author_id, l.create_time, "
				+" l.title, l.group_id,l.summary"
				+" from log l"
				+" where l.author_id='"+authorId+"' "
				+" and l.group_id=1 "
				+" and unix_timestamp( l.create_time ) between unix_timestamp('"+beginDate+"') and unix_timestamp( date_add('"+endDate+"',interval 1 day) )"
				+" order by l.create_time asc"
				+" limit "+index+","+rows;
		return logMapper.getLogPageBySql(sql);
	}
	
	@Override
	public int getWorkLogCountByDate(String authorId,String beginDate,String endDate) {
		
		String sql = "select count(1) from log l "
				+" where l.author_id='"+authorId+"' "
				+" and l.group_id=1 "
				+" and unix_timestamp( l.create_time ) between unix_timestamp('"+beginDate+"') and unix_timestamp( date_add('"+endDate+"',interval 1 day) )"
				;
				// TODO Auto-generated method stub
		return logMapper.getLogCountBySql(sql);
	}

    
}
