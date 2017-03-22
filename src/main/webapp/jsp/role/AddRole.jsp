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
<script type="text/javascript" src="static/js/role/AddRole.js"></script>
</head>
<body>
	<form id="addRoleForm" action="role/addOneRole" class="form-horizontal" method="post">
		<fieldset>
<!-- 		<legend>添加角色</legend> -->
		<div class="control-group">
			<div class="control-group">
				<label class="control-label" for="input01">角色名</label>
				<div class="controls">
					<input id="roleName" name="roleName" type="text" placeholder="请填写角色名称" form-val="empty" class="input-xlarge" maxlength="20">
					<div id="roleNameInfo" class="input-error-info"></div>
				</div>
			</div>
			</div>
			<div class="control-group">
				<label class="control-label">权限</label>
				<div class="controls">
				<select multiple id="multiSelect" name="permissions">
					<c:forEach var="permission" items="${permissions}" varStatus="status"> 
						<c:if test="${status.count!=0}">
							<option  value="${permission.id}" />${permission.permissionName}
						</c:if>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<div class="controls" style="float:right;margin-right:95px">
					<button id="btnSubmit" class="btn btn-primary" type="submit" style="margin-right:10px">提交</button>
					<button class="btn btn-primary" type="reset">重置</button>
				</div>
			</div>
		</fieldset>
	</form>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
	${actionScript }
</body>
</html>
