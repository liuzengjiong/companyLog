package org.companyLog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @TODO：
 * @fileName : org.companyLog.util.ValidateUtil.java
 * date | author | version |   
 * 2017年4月19日 | Jiong | 1.0 
 */
public class ValidateUtil {
	
	/**
	 * @param email
	 * 邮箱格式校验
	 */
	public static boolean checkEmail(String email){
		if(email == null || "".equals(email)){
			return false;
		}
		boolean ok = false;
		try{
			String reg = 
					"^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(reg);
			Matcher matcher = regex.matcher(email);
			ok = matcher.matches();
		}catch(Exception e){
			
		}
		return ok;
	}
	
	/**
	 * 手机号码校验
	 * @param phoneNumber
	 * @return
	 */
	
	public static boolean checkPhoneNumber(String phoneNumber){
		if(phoneNumber == null || "".equals(phoneNumber)){
			return false;
		}
		boolean ok = false;
		try{
			String reg = 
					"^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
			Pattern regex = Pattern.compile(reg);
			Matcher matcher = regex.matcher(phoneNumber);
			ok = matcher.matches();
		}catch(Exception e){
			
		}
		return ok;
	}
}

