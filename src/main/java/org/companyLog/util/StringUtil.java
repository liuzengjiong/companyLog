package org.companyLog.util;
/**
 * @TODO：
 * @fileName : org.companyLog.util.StringUtil.java
 * date | author | version |   
 * 2017年3月13日 | Jiong | 1.0 |
 */
public class StringUtil {
	public static String getScript(String script){
		String result = "<script type=\"text/javascript\">"+script+"</script>";
		return result;
	}
	
	/**
	 * TODO 返回ok即为通过验证
	 * @param newPassword
	 * @param confirmPassword
	 * @return
	 */
	public static String checkPassword(String newPassword,String confirmPassword){
		if(newPassword == null || confirmPassword == null){
			return "密码不能为空";
		}
		if(!newPassword.equals(confirmPassword)){
			return "两次密码不一致";
		}
		if(newPassword.length()>SiteConfig.PASSWORD_MAX_LENGTH){
			return "密码长度过长,最长限制："+ SiteConfig.PASSWORD_MAX_LENGTH;
		}
		if(newPassword.length()<SiteConfig.PASSWORD_MIN_LENGTH){
			return "密码长度过短，最短限制："+SiteConfig.PASSWORD_MIN_LENGTH;
		}
		return "ok";
	}
	
	public static boolean isNotNull(String str){
		if(str == null){
			return false;
		}
		if(str.trim().equals("")){
			return false;
		}
		return true;
	}
}

