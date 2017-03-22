package org.companyLog.service.impl;


import java.util.List;
import java.util.Map;

import org.companyLog.bean.Comment;
import org.companyLog.dao.CommentMapper;
import org.companyLog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentMapper commentMapper;
	@Override
	public List<Map<Object, Object>> getPageByLogId(String logId, int index,
			int num) {
		List<Map<Object, Object>> list = commentMapper.selectPageByLogId(logId, index, num);
		for(Map<Object, Object> map:list){
			String createTime = map.get("createTime")==null?"":map.get("createTime").toString();
			map.put("createTime",createTime);
		}
		// TODO Auto-generated method stub
		return list;
	}
	@Override
	public int getTotalByLogId(String logId) {
		// TODO Auto-generated method stub
		return commentMapper.countTotal(logId);
	}
	@Override
	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.insert(comment);
	}

}
