package org.companyLog.util;
public class SiteConfig {
	public static String PHYSICAL_UPLOAD_PATH = "E:/tomcat_upload/companyLog/";
	public static String WEB_UPLOAD_PATH = "/companyLog_upload/";
	
	public static String AppPath = "";
	public static String DoMain = "/companyLog/";
	
	public static String UEditor_Path = DoMain+"/UEditor/";
	
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	
	public static final String NO_AUTH = "您没有权限进行当前操作";
	
	public static final String DEFAULT_PASSWORD = "88888888";
	public static final Integer PASSWORD_MAX_LENGTH = 20;
	public static final Integer PASSWORD_MIN_LENGTH = 1;
	
	public static final Integer DEFAULT_PAGE_ROWS = 10;
	
	public static final String LOG_CANNOT_EDIT = "只有作者才能编辑哦，快去写日志吧";
	public static final String LOG_UPDATE_FAIL = "只有作者才能编辑哦，快去写日志吧";
	
	
	public static final String REPORT_UPDATE_FAIL_RECEIVER = "只有汇报接收人才能审阅哦";
	public static final String REPORT_UPDATE_FAIL_AUTHOR = "只有汇报发起人才能修改哦";
	public static final String REPORT_HAVE_BEEN_CHECKED = "这篇汇报已经被审阅啦，不能修改了，去看看审阅意见吧~";	
	
	public static final String SessionUserKey = "USERINFO";
	public static final String Error_SessionOut = "sessionout";
	public static final String StaffPasswordDeKey = "^&p)!ND*/";
	public static final String StaffInitialPassword = "88888888";
	
	public static final String SuperAdminName = "超级管理员";
	public static final String SuperAdminLoginName = "admin";
	public static final String SuperAdminPassword = "admin";
	
	
}
