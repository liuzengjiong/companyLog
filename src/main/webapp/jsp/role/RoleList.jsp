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
<script type="text/javascript" src="static/js/role/RoleList.js"></script>
</head>
<body>
		<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="#"><i class="icon-eye-open"></i>角色管理</a>
		    	 
		    	 <span style="float:right;margin-right:2em;">
		    	 	关键词：<input style="position:relative;top:0.3em" type="text" placeholder="角色或权限名称"   id="keyword" value="${pager.keyword }"/>
					<button id="btnKeyword" class="btn btnTitle" type="button">
								<em class="icon-search"></em>&nbsp;&nbsp;搜索
					</button>
		    	 </span>
		    	 
		    	 <button id="btnNewRole" class="btn btnTitle" type="button">
							<em class="icon-plus"></em>&nbsp;&nbsp;新建角色
				</button>
		    </div>
	   	</div>
	</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>角色名</th>
								<th>权限</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${roles}" varStatus="status"> 
							<c:if test="${status.count!=0}">
								<tr>
									<td hidden=true>${role.id } </td>
									<td>${role.roleName }</td>
									<td>
										<c:forEach var="permission" items="${role.permissions}" varStatus="status_2"> 
											<c:if test="${status_2.count!=0}">
												${permission.permissionName }&nbsp;;
											</c:if>
										</c:forEach>	
									</td>
									<td>
										<button class="btn" operate="editRole" roleid="${role.id}" type="button" title="编辑角色权限"><em class="icon-edit"></em></button>
										<button class="btn" operate="deleteRole" roleid="${role.id}" type="button"><em class="icon-remove" title="删除角色"></em></button>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	<%@ include file="/templets/Pagination.jsp"%>	
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
