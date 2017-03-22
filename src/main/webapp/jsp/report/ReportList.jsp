<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</head>
<body>
	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="javascript:void(0)"><i class="icon-eye-open"></i>汇报管理</a>
		    
		    </div>

	   	</div>
	</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>汇报人</th>
								<th>开始日期</th>
								<th>结束日期</th>
								<th>汇报时间</th>
								<th>审阅状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="report" items="${reports}" varStatus="status"> 
							<c:if test="${status.count!=0}">
								<tr>
									<td>${report.authorName }</td>
									<td>
										<fmt:parseDate value="${report.beginDate}" var="date" pattern="yyyy-MM-dd"/>
										<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />
									</td>
									<td>
										<fmt:parseDate value="${report.endDate}" var="date" pattern="yyyy-MM-dd"/>
										<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />
										</td>
									<td>${report.submitTime }</td>
									<td>
										<c:choose>
										   <c:when test="${report.state==1}">  
										   		<span style="color:green">已审阅</span>
										   </c:when>
										   <c:otherwise> 
										  		 <span style="color:red">未审阅</span>
										   </c:otherwise>
										</c:choose>
									</td>
									<td>
										<a class="btn"  href="report/detailCheck/${report.id }"  title="处理汇报"><em class="icon-edit"></em></a>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						</tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
	<%@ include file="/templets/Pagination.jsp"%>	
		
		<script type="text/javascript" src="static/js/ReportList.js"></script>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
