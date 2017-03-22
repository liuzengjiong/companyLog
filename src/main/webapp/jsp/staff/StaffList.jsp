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
<script type="text/javascript" src="static/js/staff/StaffList.js"></script>
</head>
<body>
		<input type="hidden" name="roleID" id="hidDeleteRoleID" value="0" />
		<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="#"><i class="icon-eye-open"></i>员工管理</a>
		    	 <button id="btnNewStaff" class="btn btnTitle" type="button">
							<em class="icon-plus"></em>&nbsp;&nbsp;员工录入
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
								<th>员工名</th>
								<th>角色</th>
								<th>电话</th>
								<th>邮箱</th>	
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="staff" items="${staffs}" varStatus="status"> 
							<c:if test="${status.count!=0}">
								<tr>
									<td hidden=true>${staff.id } </td>
									<td>${staff.nickname }</td>
									<td>
										<c:forEach var="role" items="${staff.roles}" varStatus="status_2"> 
											<c:if test="${status_2.count!=0}">
												${role.roleName }&nbsp;;
											</c:if>
										</c:forEach>	
									</td>
									<td>${staff.phoneNumber }</td>
									<td>${staff.email }</td>
									<td>
										<button class="btn" operate="editStaff" staffId="${staff.id}" type="button" title="编辑员工信息"><em class="icon-edit"></em></button>
										<button class="btn" operate="deleteStaff" staffId="${staff.id}" type="button"><em class="icon-remove" title="删除员工"></em></button>
										<button operate="resetPwd" class="btn" staffId="${staff.id}" type="button" title="重置员工密码"><em class="icon-lock"></em></button>
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
