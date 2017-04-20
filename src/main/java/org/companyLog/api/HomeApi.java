package org.companyLog.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.companyLog.bean.Permission;
import org.companyLog.bean.Role;
import org.companyLog.bean.User;
import org.companyLog.service.UserService;
import org.companyLog.util.JSONResult;
import org.companyLog.util.SiteConfig;
import org.companyLog.util.StringUtil;
import org.companyLog.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * @TODO：
 * @fileName : org.companyLog.api.HomeApi.java
 * date | author | version |   
 * 2017年3月12日 | Jiong | 1.0 |
 */

@Controller  
public class HomeApi {  
	
	@Autowired
	private UserService userService;
	
    @RequestMapping(value="/login",method=RequestMethod.GET)  
    public String toLoginPage(){  
        return "redirect:/jsp/Login.jsp";  
    }  
      
    @RequestMapping(value = "/login", method = RequestMethod.POST)  
    public String login(User user,BindingResult bindingResult,
    		RedirectAttributes redirectAttributes,HttpSession httpSession){  
        String msg = "";
        //去除之前的属性
        httpSession.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());  
        token.setRememberMe(true);
        try {  
        	 if(bindingResult.hasErrors()){  
            	 String errorMsg = "";
            	 for(int i=0;i<bindingResult.getErrorCount();i++){
            		 errorMsg+= ("("+i+")"+bindingResult.getAllErrors().get(i).toString()+"    ");
            	 }
            	 redirectAttributes.addFlashAttribute("message","错误："+errorMsg);  
                return "/jsp/Login.jsp";  
            }   
            //使用权限工具进行用户登录
            SecurityUtils.getSubject().login(token);  
            Subject currentUser = SecurityUtils.getSubject(); 
            currentUser.hasRole("aa");
            Session session = currentUser.getSession();
            User loginUser = (User) session.getAttribute("user");
            List<Permission> permissionList = new ArrayList<Permission>();
            Map<String,Integer> existMap = new HashMap<String,Integer>();
            for(Role role:loginUser.getRoles()){
            	for(Permission p:role.getPermissions()){
            		if(existMap.get(p.getId())==null || existMap.get(p.getId())==0){
            			existMap.put(p.getId(), 1);
            			permissionList.add(p);
            			System.out.println("添加："+p.getId());
            		}
            	}
            }
            session.setAttribute("permissions", permissionList);
            return "redirect:/jsp/Main.jsp";  
            
        }catch (IncorrectCredentialsException e) {  
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
            System.out.println(msg);  
        } catch (ExcessiveAttemptsException e) {  
            msg = "登录失败次数过多";  
            System.out.println(msg);  
        } catch (LockedAccountException e) {  
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
            System.out.println(msg);  
        } catch (DisabledAccountException e) {  
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
            System.out.println(msg);  
        } catch (ExpiredCredentialsException e) {  
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
            System.out.println(msg);  
        } catch (UnknownAccountException e) {  
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
            System.out.println(msg);  
        } catch (UnauthorizedException e) {  
            msg = "您没有得到相应的授权！" + e.getMessage();  
            System.out.println(msg);  
        }   
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/Login.jsp";  
          
    }  
      
    @RequestMapping(value="/logout",method=RequestMethod.GET)    
    public String doLogout(RedirectAttributes redirectAttributes,HttpSession httpSession){   
        //使用权限管理工具进行用户的退出
        SecurityUtils.getSubject().logout();   
//        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
        return "redirect:/login";  
    }   
      
    @RequestMapping("/403")  
    public String unauthorizedRole(){  
        return "/403";  
    }  
    
    @RequestMapping(value = "/gotoHome", method = RequestMethod.GET)  
   	public String gotoHome(){
   		return "redirect:/jsp/Main.jsp";  
   	}
    
    @RequestMapping(value = "/gotoInfoEdit", method = RequestMethod.GET)  
   	public String gotoInfoEdit(){
   		return "redirect:/jsp/home/InfoEdit.jsp";  
   	}
    
    @RequestMapping(value = "/editInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String editInfo(String id,String nickname,
			String email,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	User user = (User) httpSession.getAttribute("user");
    	try{
	    	if(null == user || !user.getId().equals(id)){
	    		msg = "你没有修改此用户的权限";
	    	}else if(StringUtil.isNotNull(email) && !ValidateUtil.checkEmail(email)){
	    		msg = "请输入正确格式的邮箱";
	    	}else{
	    			user.setEmail(email);
	    			user.setNickname(nickname);
	    			if(userService.update(user)>0){
	    				httpSession.setAttribute("user",user);
	    				msg = "更新信息成功";
	    				code = SiteConfig.SUCCESS;
	    			}else{
	    				msg = "更新信息失败";
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
    
    
    @RequestMapping(value = "/gotoPasswordEdit", method = RequestMethod.GET)  
   	public String gotoPasswordEdit(){
   		return "redirect:/jsp/home/PasswordEdit.jsp";  
   	}
    
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String editPassword(String id,String originPassword,
			String newPassword,
			String confirmPassword,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	User user = (User) httpSession.getAttribute("user");
    	try{
    		String checkPassword = StringUtil.checkPassword(newPassword, confirmPassword);
	    	if(null == user || !user.getId().equals(id)){
	    		msg = "你没有修改此用户的权限";
	    	}else if(!"ok".equals(checkPassword)){
	    		msg = checkPassword;
    		}else{
    				User originUser = userService.getUserByUsername(user.getUsername());
    				String cryptedOriginPwd = new Md5Hash(originPassword,originUser.getId()).toString();
    				if(!originUser.getPassword().equals(cryptedOriginPwd)){
    					msg = "原密码不正确";
    				}else{
    					String cryptedPwd = new Md5Hash(newPassword,originUser.getId()).toString();
    					user.setPassword(cryptedPwd);
		    			if(userService.updatePasswordById((user))>0){
		    				msg = "密码修改成功";
		    				code = SiteConfig.SUCCESS;
		    			}else{
		    				msg = "密码修改失败";
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
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error(HttpServletRequest request){
    	String requestType = request.getHeader("X-Requested-With");  
        // ajax 请求  
        if (requestType != null && requestType.equals("XMLHttpRequest")) {  
            return "redirect:/ajax403";  
        } else {  
            return "redirect:/static/html/403.jsp";  
        }  
    }
    
    @ResponseBody
    @RequestMapping(value = "/ajax403", method = RequestMethod.GET)
    public String ajaxError(){
    	JSONResult result = new JSONResult();  
    	result.setCode(SiteConfig.FAIL);
    	result.setMsg(SiteConfig.NO_AUTH);
    	return JSONObject.toJSONString(result);
    }
}  

