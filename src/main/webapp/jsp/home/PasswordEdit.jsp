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
<script type="text/javascript" src="static/js/home/PasswordEdit.js"></script>
</head>
<body  style="text-valign:center;margin-top:1em" >
	<form id="editPasswordForm" action="" class="form-horizontal" method="post">
		<input type="hidden" name="id" value="${user.id }" />
		<fieldset>
<!-- 		<legend>添加角色</legend> -->
		<div class="control-group">
			<div class="control-group">
				<label class="control-label" for="input01">原密码</label>
				<div class="controls">
					<input id="originPassword" name="originPassword" value="" type="text" placeholder="请填写原密码" form-val="empty" class="input-xlarge" maxlength="20">
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="control-group">
				<label class="control-label" for="input02">新密码</label>
				<div class="controls input-xlarge">
					<input id="newPassword" name="newPassword" value="" type="text" placeholder="请填写新密码" form-val="empty" class="input-xlarge" maxlength="20">
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="control-group">
				<label class="control-label" for="input03">确认密码</label>
				<div class="controls ">
					<input id="confirmPassword" name="confirmPassword" value="" type="text" placeholder="请确认新密码"  class="input-xlarge" maxlength="20">
				</div>
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
