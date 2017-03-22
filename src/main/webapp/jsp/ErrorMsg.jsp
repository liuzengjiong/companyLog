<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${sessionScope['org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS'][0]['message'] }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
       <script src="<%= basePath %>/static/js/jquery-1.10.1.min.js"></script>
  </head>
  
  <body>
  		${sessionScope['org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS'][0]['message'] }
		<br />
		<br />
		<br />
		<span id="time">3</span>秒后回到上一页
		<script type="text/javascript">
			var i=2;
			var interval; 
			interval = setInterval("count()", 1000); 
			function count(){
				if(i==0){
					window.history.back();
					clearInterval(interval);
				}else{
					$("#time").html(i);
					i--;
				}
			}
		</script>
  </body>
</html>
