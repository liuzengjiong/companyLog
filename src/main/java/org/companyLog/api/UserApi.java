package org.companyLog.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.companyLog.bean.Permission;
import org.companyLog.bean.Role;
import org.companyLog.bean.RolePermission;
import org.companyLog.bean.User;
import org.companyLog.bean.UserRole;
import org.companyLog.service.RoleService;
import org.companyLog.service.UserRoleService;
import org.companyLog.service.UserService;
import org.companyLog.util.IDFactory;
import org.companyLog.util.JSONResult;
import org.companyLog.util.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * @TODO：
 * @fileName : org.companyLog.api.UserApi.java
 * date | author | version |   
 * 2017年3月16日 | Jiong | 1.0 |
 */
@RequestMapping("/user")
@Controller  
public class UserApi {  
	
	@Autowired
    private UserService userService; 
	
	@Autowired
    private UserRoleService urService; 
	
	@Autowired
    private RoleService roleService; 
      
    @RequestMapping(value = "/gotoList", method = RequestMethod.GET)  
    public String getRoleList(Integer page,Integer rows ,
    		RedirectAttributes redirectAttributes,
    		HttpSession httpSession){  
        String msg = "";
        try {  
        	if(page==null||rows==null){
        		page = 1;
        		rows = SiteConfig.DEFAULT_PAGE_ROWS;
        	}
        	Integer index = (page-1)*rows;
        	if(index<0){
        		index = 0;
        	}
            List<User> userList = userService.getAllUserWithRole(index,rows,null);
            httpSession.setAttribute("staffs", userList);
            System.out.println(userList.size()+"------------");
            Map<String,String> eqCondition =null;
            int count = userService.getAllUserCount(eqCondition);
            Map<String,Object> pager = new HashMap<String,Object>();
            pager.put("total", count);
            pager.put("page", page);
            pager.put("rows", rows);
            pager.put("listUrl", "user/gotoList");
            httpSession.setAttribute("pager", pager);
            
            return "redirect:/jsp/staff/StaffList.jsp";  
            
        }catch (Exception e) {  
            msg = e.getMessage();
            System.out.println(msg);  
        }
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/ErrorMsg.jsp";  
          
    }  
    
    @RequestMapping(value = "/gotoAddStaff", method = RequestMethod.GET)  
   	public String initialAddOneStaff(HttpSession httpSession,
   			RedirectAttributes redirectAttributes){
       	List<Role> roleList = roleService.getAllRole(null,null,null);
       	httpSession.setAttribute("roles",roleList);
   		return "redirect:/jsp/staff/StaffAdd.jsp";  
   	}
    
    
    @RequestMapping(value = "/addOne", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String addOne(String nickname,String phoneNumber,
			String email,String[] roles,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	try{
	    	if(null == roles || roles.length == 0){
	    		msg = "请至少选择一个角色";
	    	}else if(null == phoneNumber || phoneNumber.length()<11){
	    		msg = "请输入手机号码";
	    	}else{
	    		User checkUser = userService.getUserByPhone(phoneNumber);
	    		if(checkUser!=null){
	    			msg = "该手机号已存在，请更换";
	    		}else{
	    			User user = new User();
	    			user.setEmail(email);
	    			user.setNickname(nickname);
	    			user.setPhoneNumber(phoneNumber);
	    			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	String timeStr = sd.format(new Date());
	    			user.setCreateTime(timeStr);
	    			String id = IDFactory.newID();
	    			user.setId(id);
	    			user.setUsername(user.getPhoneNumber());
	    			user.setPassword(SiteConfig.DEFAULT_PASSWORD);
	    			if(null == user.getNickname() || user.getNickname().length()==0){
	    				user.setNickname("用户"+user.getPhoneNumber());
	    			}
	    			if(userService.save(user)>0){
	    				msg = "新建用户成功";
	    				code = SiteConfig.SUCCESS;
	    				for(int i=0;i<roles.length;i++){
	    					UserRole ur = new UserRole();
	    					ur.setId(IDFactory.newID());
	    					ur.setRoleId(roles[i]);
	    					ur.setUserId(id);
	    					urService.save(ur);
	    				}
	    			}else{
	    				msg = "新建用户失败";
	    			}
	    		}
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
    
    @RequestMapping(value = "/gotoEditStaff/{id}", method = RequestMethod.GET)  
   	public String initialEditOneRole(@PathVariable String id,HttpSession httpSession){
       	User user = userService.getUser(id);
       	httpSession.setAttribute("staff",user);
       	List<Role> roleList = roleService.getAllRole(null,null,null);
       	httpSession.setAttribute("roles",roleList);
   		return "redirect:/jsp/staff/StaffEdit.jsp";  
   	}
    
    @RequestMapping(value = "/editOneStaff", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String editOneStaff(String id,String nickname,String phoneNumber,
			String email,String[] roles,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	try{
	    	if(null == roles || roles.length == 0){
	    		msg = "请至少选择一个角色";
	    	}else if(null == phoneNumber || phoneNumber.length()<11){
	    		msg = "请输入手机号码";
	    	}else{
	    		User checkUser = userService.getUserByPhone(phoneNumber);
	    		if(checkUser!=null && !checkUser.getId().equals(id)){
	    			msg = "该手机号已存在，请更换";
	    		}else{
	    			User user = new User();
	    			user.setEmail(email);
	    			user.setNickname(nickname);
	    			user.setPhoneNumber(phoneNumber);
	    			user.setId(id);
	    			user.setUsername(user.getPhoneNumber());
	    			user.setPassword(SiteConfig.DEFAULT_PASSWORD);
	    			if(null == user.getNickname() || user.getNickname().length()==0){
	    				user.setNickname("用户"+user.getPhoneNumber());
	    			}
	    			if(userService.update(user)>0){
	    				msg = "更新用户成功";
	    				code = SiteConfig.SUCCESS;
	    				List<String> roleList =  new ArrayList<String>(Arrays.asList(roles));
	//    				Iterator<String> it = Arrays.asList(permissions).iterator();
	    				User u = userService.getUser(id);
	    				for(Role r : u.getRoles()){
	    					String rId = r.getId();
	    					//从选择的权限列表中除去本来已经有的权限
	    					if(roleList.contains(rId)){
	    						roleList.remove(rId);
	    					//要删除的权限（即选择的列表中没有当前权限）
	    					}else{
	    						urService.deleteByUserIdAndRoleId(id, rId);
	    					}
	    				}
	    				//为角色添加新赋予的权限
	    				for(String roleId: roleList){
	    					UserRole ur = new UserRole();
	    					ur.setId(IDFactory.newID());
	    					ur.setUserId(id);
	    					ur.setRoleId(roleId);
	    					urService.save(ur);
	    				}
	    			}else{
	    				msg = "更新用户失败";
	    			}
	    		}
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
    
    
    @RequestMapping(value = "/deleteOneStaff", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String deleteOneStaff(String id,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	try{
	    	if(userService.delete(id)>0){
	    		msg = "删除员工成功";
	    		code = SiteConfig.SUCCESS;
	    		urService.deleteByUserId(id);
	    	}else{
	    		msg = "删除员工失败";
	    	}
    	}catch(Exception e){
    		msg = "错误："+e.getMessage();
    	}
    	JSONResult result = new JSONResult();
    	result.setCode(code);
    	result.setMsg(msg);
    	return JSONObject.toJSONString(result);
	}
    
    
    @RequestMapping(value = "/resetOneStaffPwd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String resetOneStaffPwd(String id,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	JSONResult result = new JSONResult();
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	try{
	    	User u = userService.getUser(id);
	    	if(u==null){
	    		msg = "用户不存在";
	    	}else{
	    		u.setPassword(SiteConfig.DEFAULT_PASSWORD);
	    		if(userService.update(u)>0){
	    			msg = "密码重置成功";
	    			code = SiteConfig.SUCCESS;
	    		}else{
	    			msg = "密码重置失败";
	    		}
	    	}
    	}catch(Exception e){
    		msg = "错误:"+e.getMessage();
    	}
    	
    	
    	result.setCode(code);
    	result.setMsg(msg);
    	return JSONObject.toJSONString(result);
    	
    }
    
}  


