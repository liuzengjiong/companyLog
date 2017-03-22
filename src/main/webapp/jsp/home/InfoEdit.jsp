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
<script type="text/javascript" src="static/js/home/InfoEdit.js"></script>
</head>
<body style="text-valign:center;margin-top:1em" >
	<form id="editInfoForm" action="" class="form-horizontal" method="post">
		<input type="hidden" name="id" value="${user.id }" />
		<fieldset>
<!-- 		<legend>添加角色</legend> -->
		<div class="control-group">
			<div class="control-group">
				<label class="control-label" for="input01">名字</label>
				<div class="controls">
					<input id="nickame" name="nickname" value="${user.nickname }" type="text" placeholder="请填写员工名字" form-val="empty" class="input-xlarge" maxlength="20">
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="control-group">
				<label class="control-label" for="input02">手机(账号)</label>
				<div class="controls input-xlarge">
					${user.phoneNumber }
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="control-group">
				<label class="control-label" for="input03">邮箱</label>
				<div class="controls ">
					<input id="email" name="email" value="${user.email }" type="text" placeholder="邮箱可由员工自己完善"  class="input-xlarge" maxlength="20">
				</div>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">角色</label>
				<div class="controls  input-xlarge">
					<c:forEach var="role" items="${user.roles}" varStatus="status"> 
						<c:if test="${status.count!=0}">
							${role.roleName} &nbsp;;
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="control-group">
				<div id="errorInfo" class="input-error-info"></div>
				<div class="controls" style="float:right;margin-right:95px">
					<button id="btnSubmit" class="btn btn-primary" type="submit" style="margin-right:10px">提交修改</button>
					<button class="btn btn-primary" type="reset">重置</button>
				</div>
			</div>
		</fieldset>
	</form>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
	${actionScript }
</body>
</html>
