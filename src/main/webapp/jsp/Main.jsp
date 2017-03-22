<%@page import="org.companyLog.util.SiteConfig"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日志管理系统</title>
<%@ include file="../templets/bootstrap-head.jsp"%>
<script type="text/javascript" src="static/bootstrap-sco/css/scojs.css"></script>
<link rel="stylesheet" href="static/bootstrap-messenger/css/messenger.css" />
<link rel="stylesheet" href="static/bootstrap-messenger/css/messenger-theme-block.css" />
<link rel="stylesheet" href="static/css/main.css" />
<!--[if IE]>
	<link rel="stylesheet" href="static/css/main-ie.css" type="text/css"></link>
<![endif]-->
<script type="text/javascript" src="static/bootstrap-sco/js/sco.modal.js"></script>
<script type="text/javascript" src="static/bootstrap-sco/js/sco.confirm.js"></script>
<script type="text/javascript" src="static/bootstrap-messenger/js/messenger.min.js"></script>
<script type="text/javascript" src="static/js/dialog/Dialog-OperateConfirm.js"></script>
<script type="text/javascript" src="static/js/dialog/Dialog-LogManage.js"></script>
<script type="text/javascript" src="static/js/dialog/Dialog-StaffManage.js"></script>
<script type="text/javascript" src="static/js/dialog/Dialog-RoleManage.js"></script>
<script type="text/javascript" src="static/js/dialog/Dialog-ReportManage.js"></script>
<script type="text/javascript" src="static/js/Main.js"></script>
</head>
<body style="background: url('static/images/blackBack2.gif')">
	<div class="header">
		<span class="userWelcome">欢迎你,${user.nickname }</span>
		<div class="login_info">
			<img src="static/images/write.png" class="logo" /><span>日志管理系统</span>
		</div>
		<div class="menusplitOne"></div>
		<div class="menusplitTwo"></div>
		<div class="menusplitThree"></div>
		<div class="headmenu">
			<ul class="nav nav-pills">
				<li><a id="btnStaffInfo" href="#"><i class="icon-list-alt icon-white"></i><span>个人资料</span>
				</a>
				</li>
				<li><a id="btnEditPwd" href="#"><i class="icon-lock icon-white"></i><span>修改密码</span>
				</a>
				</li>
				<li><a id="btnQuit" href="#"><i class="icon-ban-circle icon-white"></i><span>安全退出</span>
				</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="left_menu">
	    
		<div style="padding: 8px 0;">
			<ul class="nav nav-list">
			
			<c:forEach var="permission" items="${permissions}" varStatus="status"> 
				<c:if test="${status.count!=0}">
					<li>
					<a 
						permission-code="${permission.permissionCode }" 
						url="${permission.url }" 
						href="javascript:void(0)">
						<i class="icon-white"></i>
						<span>${permission.permissionName }</span>
					</a></li>
					<li class="divider"></li>
				</c:if>
			</c:forEach>
			</ul>
		</div> 
	</div>
	<div class="main">
	<c:forEach var="permission" items="${permissions}" varStatus="status"> 
		<c:if test="${status.getIndex()==0}">
			<iframe id="frmContent" style="width:100%;" src="${permission.url}"  frameborder="0"></iframe>	
		</c:if>
	</c:forEach>
	</div>
	<%@ include file="../templets/bootstrap-bottom.jsp"%>
</body>
</html>
