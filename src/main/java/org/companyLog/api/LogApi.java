package org.companyLog.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.companyLog.bean.Log;
import org.companyLog.bean.LogCollection;
import org.companyLog.bean.LogGroup;
import org.companyLog.bean.LogLimitRole;
import org.companyLog.bean.LogShower;
import org.companyLog.bean.Role;
import org.companyLog.bean.User;
import org.companyLog.service.LogCollectionService;
import org.companyLog.service.LogGroupService;
import org.companyLog.service.LogLimitRoleService;
import org.companyLog.service.LogService;
import org.companyLog.service.RoleService;
import org.companyLog.util.IDFactory;
import org.companyLog.util.JSONResult;
import org.companyLog.util.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

/**
 * @TODO：
 * @fileName : org.companyLog.api.LogApi.java
 * date | author | version |   
 * 2017年3月12日 | Jiong | 1.0 |
 */
@RequestMapping("/log")
@Controller  
public class LogApi {  
	
	@Autowired
    private LogService logService; 
	
	@Autowired
    private LogGroupService lgService; 
	
	@Autowired
    private LogCollectionService lcService; 
	
	@Autowired
    private LogLimitRoleService lrService; 
	
	@Autowired
    private RoleService roleService; 
	
	
      
    @RequestMapping(value = "/gotoListOfOne", method = RequestMethod.GET)  
    public String gotoListOfOne(String authorId,Integer page,Integer rows ,
    		RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        try {  
        	if(page==null||rows==null){
        		page = 1;
        		rows = SiteConfig.DEFAULT_PAGE_ROWS;
        	}
        	int index = (page-1)*rows;
        	if(index<0){
        		index = 0;
        	}
        	User user = (User) httpSession.getAttribute("user");
        	boolean isOther = true;
        	if(null == authorId || "".equals(authorId)){
        		isOther = false;
        		authorId = user.getId();
        	}
            List<LogShower> logList = logService.getLogByAuthor(authorId,index,rows,user.getId());
            int count = logService.getLogCountByAuthor(authorId,user.getId());
            Map<String,Object> pager = new HashMap<String,Object>();
            pager.put("total", count);
            pager.put("page", page);
            pager.put("rows", rows);
            String returnUrl = "";
            //他人的列表
            if(isOther){
            	pager.put("listUrl", "log/gotoListOfOne?authorId="+authorId);
            	returnUrl = "redirect:/jsp/log/logSquare/LogsOfUser.jsp";
            //本人的列表
            }else{
            	pager.put("listUrl", "log/gotoListOfOne");
            	returnUrl = "redirect:/jsp/log/LogList.jsp";
            }
            httpSession.setAttribute("pager", pager);
            httpSession.setAttribute("logs", logList);
            System.out.println("logs:"+logList.size());
            return returnUrl;  
            
        }catch (Exception e) {  
            msg = e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
          
    } 
    
    @RequestMapping(value = "/gotoListOfCollection", method = RequestMethod.GET)  
    public String gotoListOfCollection(Integer page,Integer rows ,
    		RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        try {  
        	if(page==null||rows==null){
        		page = 1;
        		rows = SiteConfig.DEFAULT_PAGE_ROWS;
        	}
        	int index = (page-1)*rows;
        	if(index<0){
        		index = 0;
        	}
        	User user = (User) httpSession.getAttribute("user");
            List<LogShower> logList = logService.getLogCollection(user.getId(), index, rows);
            int count = logService.getLogCollectionCount(user.getId());
            Map<String,Object> pager = new HashMap<String,Object>();
            pager.put("total", count);
            pager.put("page", page);
            pager.put("rows", rows);
            pager.put("listUrl", "log/gotoListOfCollection");
            
            httpSession.setAttribute("pager", pager);
            httpSession.setAttribute("logs", logList);
            
            return "redirect:/jsp/log/logSquare/LogsOfCollection.jsp";  
            
        }catch (Exception e) {  
            msg = e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
          
    } 
    
    @RequestMapping(value = "/gotoListOfAll", method = RequestMethod.GET)  
    public String getRoleList(String keyword,Integer page,Integer rows ,RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        try {  
        	if(page==null||rows==null){
        		page = 1;
        		rows = SiteConfig.DEFAULT_PAGE_ROWS;
        	}
        	int index = (page-1)*rows;
        	if(index<0){
        		index = 0;
        	}
        	User user = (User) httpSession.getAttribute("user");
            List<LogShower> logList = logService.getLogOfAll(index,rows,user.getId(),keyword);
            httpSession.setAttribute("logs", logList);
            int count = logService.getLogCount(user.getId(),keyword);
            Map<String,Object> pager = new HashMap<String,Object>();
            pager.put("total", count);
            pager.put("page", page);
            pager.put("rows", rows);
            pager.put("keyword", keyword);
            httpSession.setAttribute("pager", pager);
            System.out.println("logs:"+logList.size());
            return "redirect:/jsp/log/logSquare/LogSquare.jsp";  
            
        }catch (Exception e) {  
            msg = e.getMessage();
            e.printStackTrace();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
          
    }  
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)  
    public String logDetail(@PathVariable String id,RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        try {  
        	User user = (User) httpSession.getAttribute("user");
        	LogShower log = logService.getLogById(id,user.getId());
        	logService.addReadNum(log.getId());
            httpSession.setAttribute("log", log);
            return "redirect:/jsp/log/LogDetail.jsp";  
            
        }catch (Exception e) {  
            msg = e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
          
    }  
    
    //跳转编辑日志的页面 
    @RequestMapping(value = "/gotoEdit/{id}", method = RequestMethod.GET)  
    public String getEditionLink(@PathVariable String id,
    		RedirectAttributes redirectAttributes,HttpSession httpSession){  
        String msg = "";
        try {  
        	User user = (User) httpSession.getAttribute("user");
            Log log = logService.getLogById(id,user.getId());
            if(log.getAuthorId().equals(user.getId())){
            	
            	List<LogGroup> logGroupList = lgService.selectAll();
            	List<Role> roleList = roleService.getAllRole(null,null,null);
            	Map<String,Object> data = new HashMap<String,Object>();
            	if(null != log.getIsLimitSee() && 1 == log.getIsLimitSee()){
            		List<LogLimitRole> haveRoles = lrService.getListByLogId(id);
            		data.put("haveRoles", haveRoles);
            	}
            	data.put("logGroups", logGroupList);
            	data.put("roles", roleList);
            	data.put("log", log);
            	httpSession.setAttribute("data", data);
            	
                return "redirect:/jsp/log/EditLog.jsp";
            }else{
            	msg = SiteConfig.LOG_CANNOT_EDIT;
            }
        }catch (Exception e) {  
            msg = e.getMessage();
            e.printStackTrace();
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
    }
    
    //更新日志 
    @RequestMapping(value = "/update", method = RequestMethod.POST)  
    public String update(Log log,String[] roles,
    		RedirectAttributes redirectAttributes,HttpSession httpSession){  
        String msg = "";
        try {  
        	User user = (User) httpSession.getAttribute("user");
            Log nativeLog = logService.getLogById(log.getId(),user.getId());
            if(nativeLog.getAuthorId().equals(user.getId())){
            	String summary = log.getSummary();
            	if(summary!=null && summary.length()>100){
            		summary = summary.substring(0,100);
            	}
            	//防止网页标签造成影响
            	summary = summary.replace("<", "[").replace(">", "]");
            	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String timeStr = sd.format(new Date());
            	log.setUpdateTime(timeStr);
            	logService.update(log);
            	if(roles.length != 0){
            		log.setIsLimitSee(1);
	            	List<String> roleList =  new ArrayList<String>(Arrays.asList(roles));
	            	List<LogLimitRole> haveRoles = lrService.getListByLogId(log.getId());
	            	for(LogLimitRole r : haveRoles){
						String rId = r.getRoleId();
						//从选择的权限列表中除去本来已经有的权限
						if(roleList.contains(rId)){
							roleList.remove(rId);
						//要删除的权限（即选择的列表中没有当前权限）
						}else{
							lrService.deleteByLogIdAndRoleId(log.getId(), rId);
						}
					}
					//为角色添加新赋予的权限
	            	for(String role:roleList){
	        			LogLimitRole lr = new LogLimitRole();
	        			lr.setId(IDFactory.newID());
	        			lr.setLogId(log.getId());
	        			lr.setRoleId(role);
	        			lrService.save(lr);
	        		}
            	}else{
            		log.setIsLimitSee(0);
            		List<LogLimitRole> haveRoles = lrService.getListByLogId(log.getId());
            		for(LogLimitRole r : haveRoles){
						String rId = r.getRoleId();
						lrService.deleteByLogIdAndRoleId(log.getId(), rId);
					}
            	}
            	
            	log = logService.getLogById(log.getId(),user.getId());
            	httpSession.setAttribute("log", log);
                return "redirect:/jsp/log/LogDetail.jsp";
            }else{
            	msg = SiteConfig.LOG_UPDATE_FAIL;
            }
        }catch (Exception e) {  
            msg = "发生错误:"+e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
    }
    
    //跳转新建日志的页面 
    @RequestMapping(value = "/gotoAdd", method = RequestMethod.GET)  
    public String gotoAdd(
    		RedirectAttributes redirectAttributes,HttpSession httpSession){  
        String msg = "";
        try {  
        	List<LogGroup> logGroupList = lgService.selectAll();
        	List<Role> roleList = roleService.getAllRole(null,null,null);
        	Map<String,Object> data = new HashMap<String,Object>();
        	data.put("logGroups", logGroupList);
        	data.put("roles", roleList);
        	
        	httpSession.setAttribute("data", data);
        	return "redirect:/jsp/log/AddLog.jsp";
        }catch (Exception e) {  
            msg = e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
    }
    
    //新建日志 
    @RequestMapping(value = "/add", method = RequestMethod.POST)  
    public String add(Log log,String[] roles,
    		HttpServletResponse response,
    		RedirectAttributes redirectAttributes,HttpSession httpSession){  
        String msg = "";
        try {  
        	String logId = IDFactory.newID();
    		log.setId(logId);
        	User user = (User) httpSession.getAttribute("user");
        	log.setAuthorId(user.getId());
        	String summary = log.getSummary();
        	if(summary!=null && summary.length()>100){
        		summary = summary.substring(0,100);
        	}
        	//防止html代码对页面造成影响
        	summary = summary.replace("<", "{").replace(">", "}");
        	log.setSummary(summary);
        	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String timeStr = sd.format(new Date());
        	log.setUpdateTime(timeStr);
        	log.setCreateTime(timeStr);
        	log.setReadNum(0);
        	//查看权限
        	if(null!=roles && roles.length>0){
        		log.setIsLimitSee(1);
        		for(String role:roles){
        			LogLimitRole lr = new LogLimitRole();
        			lr.setId(IDFactory.newID());
        			lr.setLogId(logId);
        			lr.setRoleId(role);
        			lrService.save(lr);
        		}
        	}else{
        		log.setIsLimitSee(0);
        	}
        	
        	if(logService.save(log)>0){
	        	List<LogShower> logs = logService.getLogByAuthor(user.getId(),0,SiteConfig.DEFAULT_PAGE_ROWS,user.getId());
	        	httpSession.setAttribute("logs", logs);
	        	 int count = logService.getLogCountByAuthor(user.getId(),user.getId());
	             Map<String,Object> pager = new HashMap<String,Object>();
	             pager.put("total", count);
	             pager.put("page", 1);
	             pager.put("rows", SiteConfig.DEFAULT_PAGE_ROWS);
	             httpSession.setAttribute("pager", pager);
	        	return "redirect:/jsp/log/LogList.jsp";
            }else{
            	msg = SiteConfig.LOG_UPDATE_FAIL;
            }
        }catch (Exception e) {  
            msg = "发生错误:"+e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
    }
    
    @ResponseBody
    @RequestMapping(value = "/collect", method = RequestMethod.POST)  
    public String collect( String id,
    		RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        String code = SiteConfig.FAIL;
        //是否是收藏动作，否则是取消收藏
        boolean actIsCollect = false;
        try {  
        	User user = (User) httpSession.getAttribute("user");
        	LogShower log = logService.getLogById(id,user.getId());
        	if(log == null){
        		msg = "该日志不存在";
        	}else{
	        	if(!log.isHaveCollect()){
	        		actIsCollect = true;
	        		LogCollection lc = new LogCollection();
	        		lc.setCreateTime(new Date());
	        		lc.setId(IDFactory.newID());
	        		lc.setLogId(id);
	        		lc.setUserId(user.getId());
	        		lcService.insert(lc);
	        		msg = "日志收藏成功";
	        	}else{
	        		LogCollection lc = lcService.getByLogIdAndUserId(log.getId(), user.getId());
	        		lcService.delete(lc.getId());
	        		msg = "取消收藏成功";
	        	}
	        	code = SiteConfig.SUCCESS;
        	}
            
        }catch (Exception e) {  
            msg = e.getMessage();
            System.out.println(msg);  
        }
        JSONResult result = new JSONResult();
    	result.setCode(code);
    	result.setMsg(msg);
    	result.setData(actIsCollect);
    	return JSONObject.toJSONString(result);
          
    }  
    
    @ResponseBody
    @RequestMapping(value = "/getWorkListByTime", method = RequestMethod.GET)  
    public String getWorkListByTime(String authorId,Integer page,Integer rows ,
    		String beginDate,String endDate,
    		RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        String code = SiteConfig.FAIL;
        JSONResult result = new JSONResult();
        if(beginDate == null || endDate == null){
        	msg = "日期格式不正确";
        }else{
	        try {  
	        	if(page==null||rows==null){
	        		page = 1;
	        		rows = SiteConfig.DEFAULT_PAGE_ROWS;
	        	}
	        	int index = (page-1)*rows;
	        	if(index<0){
	        		index = 0;
	        	}
	            List<LogShower> logList = logService.getWorkLogByDate(authorId, index, rows, beginDate, endDate);
	            int count = logService.getWorkLogCountByDate(authorId, beginDate, endDate);
	            code = SiteConfig.SUCCESS;
	            result.setTotal(count);
	            result.setData(logList);
	        }catch (Exception e) {  
	            msg = e.getMessage();
	            System.out.println(msg);  
	        }
        }
       
        result.setMsg(msg);
        result.setCode(code);
        return JSONObject.toJSONString(result);  
          
    } 
    
}  

