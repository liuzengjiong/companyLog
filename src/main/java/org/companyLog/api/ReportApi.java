package org.companyLog.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.companyLog.bean.Report;
import org.companyLog.bean.ReportShower;
import org.companyLog.bean.User;
import org.companyLog.service.ReportService;
import org.companyLog.service.UserService;
import org.companyLog.util.IDFactory;
import org.companyLog.util.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @TODO：
 * @fileName : org.companyLog.api.ReportApi.java
 * date | author | version |   
 * 2017年3月12日 | Jiong | 1.0 |
 */
@RequestMapping("/report")
@Controller  
public class ReportApi {  
	
	@Autowired
    private ReportService reportService; 
	
	@Autowired
    private UserService userService; 
      
    @RequestMapping(value = "/gotoListOfReceiver", method = RequestMethod.GET)  
    public String getReportList(Integer page,Integer rows ,String receiverId,RedirectAttributes redirectAttributes,
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
        	if(null == receiverId || "".equals(receiverId)){
        		User user = (User) httpSession.getAttribute("user");
        		receiverId = user.getId();
        	}
            List<ReportShower> reportList = reportService.getReportByReceiver(index,rows,receiverId);
            httpSession.setAttribute("reports", reportList);
            
            Map<String,String> eqCondition = new HashMap<String,String>();
        	eqCondition.put("receiver_id", receiverId);
            int count = reportService.getReportCountByCondition(eqCondition);
            Map<String,Object> pager = new HashMap<String,Object>();
            pager.put("total", count);
            pager.put("page", page);
            pager.put("rows", rows);
            pager.put("listUrl", "report/gotoListOfReceiver");
            httpSession.setAttribute("pager", pager);

            return "redirect:/jsp/report/ReportList.jsp";  
            
        }catch (Exception e) {  
            msg = "错误："+e.getMessage();
            System.out.println(msg);  
        }
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/Login.jsp";  
          
    }  
    
    
    @RequestMapping(value = "/detailCheck/{id}", method = RequestMethod.GET)  
    public String gotoDetailCheck(@PathVariable String id,RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        try {  
        	Report report = reportService.getReportById(id);
            httpSession.setAttribute("report", report);
            return "redirect:/jsp/report/ReportCheck.jsp";  
            
        }catch (Exception e) {  
            msg = "错误："+e.getMessage();
            System.out.println(msg);  
        }
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/Login.jsp";  
          
    }  
    
    
    
    //更新 
    @RequestMapping(value = "/update", method = RequestMethod.POST)  
    public String update(Report report,
    		RedirectAttributes redirectAttributes,HttpSession httpSession){  
        String msg = "";
        try {  
            Report nativeReport = reportService.getReportById(report.getId());
            User user = (User) httpSession.getAttribute("user");
	        if(nativeReport.getReceiverId().equals(user.getId())){
	        	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	String timeStr = sd.format(new Date());
	        	report.setUpdateTime(timeStr);
	        	reportService.update(report);
	        	
	        	String url = "redirect:/report/gotoListOfReceiver?receiverId="+user.getId()
	        			+"&page=1&rows="+SiteConfig.DEFAULT_PAGE_ROWS;
	        	return url;
	        }else{
	        	msg = SiteConfig.REPORT_UPDATE_FAIL_RECEIVER;
	        }
        }catch (Exception e) {  
            msg = "发生错误:"+e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
    }
    

    
   
    
}  

