package org.companyLog.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.companyLog.bean.Permission;
import org.companyLog.bean.Role;
import org.companyLog.bean.RolePermission;
import org.companyLog.bean.User;
import org.companyLog.service.LogLimitRoleService;
import org.companyLog.service.PermissionService;
import org.companyLog.service.RolePermissionService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * @TODO：
 * @fileName : org.companyLog.api.RoleApi.java
 * date | author | version |   
 * 2017年3月12日 | Jiong | 1.0 |
 */
@RequestMapping("/role")
@Controller  
public class RoleApi {  
	
	@Autowired
    private RoleService roleService; 
	
	@Autowired
    private PermissionService permissionService; 
	
	@Autowired
    private LogLimitRoleService lrService; 
	
	@Autowired
    private UserRoleService userRoleService; 
	
	@Autowired
    private UserService userService; 
	
	@Autowired
    private RolePermissionService rolePermissionService; 
      
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
            List<Role> roleList = roleService.getAllRole(index,rows,null);
            httpSession.setAttribute("roles", roleList);
            
            int count = roleService.getAllRoleCount(null);
            Map<String,Object> pager = new HashMap<String,Object>();
            pager.put("total", count);
            pager.put("page", page);
            pager.put("rows", rows);
            pager.put("listUrl", "role/gotoList");
            httpSession.setAttribute("pager", pager);
            
            return "redirect:/jsp/role/RoleList.jsp";  
            
        }catch (Exception e) {  
            msg = e.getMessage();
            System.out.println(msg);  
        }
        redirectAttributes.addFlashAttribute("message",msg);  
        return "redirect:/jsp/Login.jsp";  
          
    }  
    
    @RequestMapping(value = "/gotoAddRole", method = RequestMethod.GET)  
	public String initialAddOneRole(HttpSession httpSession,
			RedirectAttributes redirectAttributes){
    	List<Permission> permissionList = permissionService.getAllPermission();
    	httpSession.setAttribute("permissions",permissionList);
		return "redirect:/jsp/role/AddRole.jsp";  
	}
    
    @RequestMapping(value = "/addOneRole", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String addOneRole(String roleName,String[] permissions,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	if(null == permissions || permissions.length == 0){
    		msg = "请至少选择一个权限";
    	}else{
    		Role role = roleService.getRoleByRoleName(roleName);
    		if(role!=null){
    			msg = "该角色名已存在";
    		}else{
    			role = new Role();
    			String id = IDFactory.newID();
    			role.setId(id);
    			role.setRoleName(roleName);
    			role.setRoleCode(id);
    			if(roleService.insertRole(role)>0){
    				msg = "新建角色成功";
    				code = SiteConfig.SUCCESS;
    				for(int i=0;i<permissions.length;i++){
    					RolePermission rp = new RolePermission();
    					rp.setId(IDFactory.newID());
    					rp.setPermissionId(permissions[i]);
    					rp.setRoleId(id);
    					rolePermissionService.insert(rp);
    				}
    			}else{
    				msg = "新建角色失败";
    			}
    		}
    	}
    	JSONResult result = new JSONResult();
    	result.setCode(code);
    	result.setMsg(msg);
    	return JSONObject.toJSONString(result);
//		return "redirect:/jsp/role/AddRole.jsp";  
	}
    
    @RequestMapping(value = "/gotoEditRole/{id}", method = RequestMethod.GET)  
	public String initialEditOneRole(@PathVariable String id,HttpSession httpSession){
    	Role role = roleService.getOneRoleDetail(id);
    	httpSession.setAttribute("role",role);
    	List<Permission> permissionList = permissionService.getAllPermission();
    	httpSession.setAttribute("permissions",permissionList);
		return "redirect:/jsp/role/EditRole.jsp";  
	}
    
    @RequestMapping(value = "/editOneRole", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String editOneRole(String id,String roleName,String[] permissions,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	if(null == permissions || permissions.length == 0){
    		msg = "请至少选择一个权限";
    	}else{
    		Role role = roleService.getRoleByRoleName(roleName);
    		if(role!=null && !role.getId().equals(id)){
    			msg = "该角色名已存在";
    		}else{
    			role = roleService.getOneRoleDetail(id);
    			role.setRoleName(roleName);
    			if(roleService.updateRoleByPrimaryKey(role)>0){
    				msg = "更新角色成功";
    				code = SiteConfig.SUCCESS;
    				List<String> permissionList =  new ArrayList<String>(Arrays.asList(permissions));
//    				Iterator<String> it = Arrays.asList(permissions).iterator();
    				
    				for(Permission p : role.getPermissions()){
    					String pId = p.getId();
    					//从选择的权限列表中除去本来已经有的权限
    					if(permissionList.contains(pId)){
    						permissionList.remove(pId);
    					//要删除的权限（即选择的列表中没有当前权限）
    					}else{
    						rolePermissionService.deleteByRoleAndPermissionId(id, pId);
    					}
    				}
    				//为角色添加新赋予的权限
    				for(String permissionId: permissionList){
    					RolePermission rp = new RolePermission();
    					rp.setId(IDFactory.newID());
    					rp.setRoleId(id);
    					rp.setPermissionId(permissionId);
    					rolePermissionService.insert(rp);
    				}
    				User user = (User) httpSession.getAttribute("user");
    				user = userService.getUserByUsername(user.getUsername());
    				httpSession.setAttribute("user", user);
    			}else{
    				msg = "更新角色失败";
    			}
    		}
    	}
    	JSONResult result = new JSONResult();
    	result.setCode(code);
    	result.setMsg(msg);
    	return JSONObject.toJSONString(result);
//		return "redirect:/jsp/role/AddRole.jsp";  
	}
    
    
    @RequestMapping(value = "/deleteOneRole", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")  
    @ResponseBody
	public String deleteOneRole(String id,
			HttpSession httpSession,RedirectAttributes redirectAttributes){
    	
    	String code = SiteConfig.FAIL;
    	String msg = "";
    	if(roleService.deleteById(id)>0){
    		msg = "删除角色成功";
    		code = SiteConfig.SUCCESS;
    		rolePermissionService.deleteByRoleId(id);
    		userRoleService.deleteByRoleId(id);
    		lrService.deleteByRoleId(id);
    		
    	}else{
    		msg = "删除角色失败";
    	}
    	JSONResult result = new JSONResult();
    	result.setCode(code);
    	result.setMsg(msg);
    	return JSONObject.toJSONString(result);
//		return "redirect:/jsp/role/AddRole.jsp";  
	}
}  

