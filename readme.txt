需要配置：
org.companyLog.utli.SiteConfig.java:
public static String PHYSICAL_UPLOAD_PATH = "E:/tomcat_upload/companyLog/";   //物理路径
public static String WEB_UPLOAD_PATH = "/companyLog_upload/";		      //tomcat虚拟路径

在tomcat的localhost中添加 companyLog_upload.xml
<?xml version="1.0" encoding="UTF-8"?> 
<Context 
    docBase="E:\tomcat_upload\companyLog" 
    path="/companyLog_upload"
    reloadable="true"> 
</Context> 