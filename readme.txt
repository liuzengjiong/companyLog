��Ҫ���ã�
org.companyLog.utli.SiteConfig.java:
public static String PHYSICAL_UPLOAD_PATH = "E:/tomcat_upload/companyLog/";   //����·��
public static String WEB_UPLOAD_PATH = "/companyLog_upload/";		      //tomcat����·��

��tomcat��localhost����� companyLog_upload.xml
<?xml version="1.0" encoding="UTF-8"?> 
<Context 
    docBase="E:\tomcat_upload\companyLog" 
    path="/companyLog_upload"
    reloadable="true"> 
</Context> 