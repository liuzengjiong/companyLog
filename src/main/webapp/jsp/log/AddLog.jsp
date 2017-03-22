<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
 
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<%@ include file="/templets/UEditor-head.jsp"%>
<!-- <link rel="stylesheet" href="static/css/blog_default.css" /> -->
<link rel="stylesheet" href="static/css/blog.css" />
</head>
<body>
		
<div>
<!--done-->
	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="javascript:void(0)"><i class="icon-eye-open"></i>新建日志</a>
		    </div>

	   	</div>
	</div>

<form id="addForm" action="" method="post">
	<input type="hidden" name="content" id="content" value=""/>
	<input type="hidden" name="summary" id="summary" value=""/>
	<div style="margin:1em;margin-top:2em">
		<em style="height:30px;">标题：</em>&nbsp;<input id="title" name="title" type="text" class="input-xlarge" maxlength="20" style="border: 1px solid rgb(198, 97, 97); background-color: rgb(251, 226, 226);">
 	</div>
 	
 	
 	<!-- 编辑 -->
	<div>
		<script  id="editContainer"  style="width:97%; height:50%; padding-left:10px;display:none;" align="center" name=""replyContainer"" type=text/plain>
	</script>
	
	
	</div>
	<div class="divBelowUEditor">
		<div class="belowUEditor">
			<button id="btnSubmit" class="btn" type="submit" >
				&nbsp;&nbsp;提交
			</button>	
		</div>
		<div class="belowUEditor">
			<select  id="groupSelect" name="groupId">
			<c:forEach var="logGroup" items="${data.logGroups}" varStatus="status"> 
				<c:if test="${status.count!=0}">
					<option  value="${logGroup.id}" groupTip="${logGroup.groupTip }"/>${logGroup.groupName}
				</c:if>
			</c:forEach>
			</select>
			<br/>
			<div id="groupTipDiv"></div>
		</div>
		<div class="belowUEditor" style="text-align:right;">
			<select  id="rolesSelect" multiple  name="roles">
			<c:forEach var="role" items="${data.roles}" varStatus="status"> 
				<c:if test="${status.count!=0}">
					<option  value="${role.id}"/>${role.roleName}
				</c:if>
			</c:forEach>
			</select>
			<br/>
			<span id="roleTipDiv"></span>
		</div>
		
	</div>
</form>
</div>
	<script type="text/javascript">
		var editor = null;
		 window.UEDITOR_HOME_URL = "<%=SiteConfig.UEditor_Path %>";
		editor = UE.getEditor('editContainer', {
			
		});
		
		editor.ready(function() {
		  	$("#editContainer").show();
		    //设置编辑器的内容
// 		    editor.setContent("测试");
		    //获取html内容，返回: <p>hello</p>
// 		    var html = editor.getContent();
		    //获取纯文本内容，返回: hello
// 		    var txt = editor.getContentTxt();
		});
</script>
<input type="hidden" id="haveSubmit"></input>
<script type="text/javascript" src="static/js/log/AddLog.js"></script>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
