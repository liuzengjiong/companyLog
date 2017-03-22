package org.companyLog.service;

import java.util.List;
import java.util.Map;

import org.companyLog.bean.Comment;


public interface CommentService {
    
   List<Map<Object,Object>> getPageByLogId(String logId,int index,int num);
   
   int getTotalByLogId(String logId);
   
   int insert(Comment comment);
}
