package org.companyLog.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.companyLog.bean.Comment;
import org.companyLog.bean.RolePermission;
import org.companyLog.bean.User;
import org.companyLog.service.CommentService;
import org.companyLog.util.IDFactory;
import org.companyLog.util.JSONResult;
import org.companyLog.util.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * @TODO：
 * @fileName : org.CommentApi.api.LogApi.java
 * date | author | version |   
 * 2017年3月12日 | Jiong | 1.0 |
 */
@RequestMapping("/comment")
@Controller  
public class CommentApi {  
	
	@Autowired
    private CommentService commentService; 
	
	
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")  
    public String getRoleList(String logId,int page,int rows,
    		RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        String code = SiteConfig.FAIL;
        JSONResult result = new JSONResult();
        try {  
        	int index = (page-1)*rows;
        	index = index<0?0:index;
        	List<Map<Object,Object>> list = commentService.getPageByLogId(logId, index, rows);
        	int total = commentService.getTotalByLogId(logId);
        	result.setData(list);
            result.setTotal(total);
            code = SiteConfig.SUCCESS;
        }catch (Exception e) {  
            msg = "错误："+e.getMessage();
            System.out.println(msg);  
        }
        result.setCode(code);
        result.setMsg(msg);
        return JSONObject.toJSONString(result);  
          
    }  
    
    @RequestMapping(value = "/addOne", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String addOne(Comment comment,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "操作失败";
    	try{
	    	String id = IDFactory.newID();
			comment.setId(id);
			User user = (User) httpSession.getAttribute("user");
			comment.setAuthorId(user.getId());
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String timeStr = sd.format(new Date());
        	comment.setCreateTime(timeStr);
			if(commentService.insert(comment)>0){
				msg = "评论成功";
				code = SiteConfig.SUCCESS;
			}else{
				msg = "评论失败";
			}
    	}catch(Exception e){
    		msg = "错误："+e.getMessage();
    	}
    	JSONResult result = new JSONResult();
    	result.setCode(code);
    	result.setMsg(msg);
    	return JSONObject.toJSONString(result);
//		return "redirect:/jsp/role/AddRole.jsp";  
	}
    
}  

