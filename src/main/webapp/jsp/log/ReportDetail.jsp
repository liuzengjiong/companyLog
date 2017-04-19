<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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

<link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap-datepicker.css" />
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datepicker.js"></script>

</head>
<body>
	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="javascript:void(0)"><i class="icon-eye-open"></i>汇报详情</a>
		    </div>
	   	</div>
	</div>
<form action="" method="post" id="reportForm">
	<input type="hidden" name="id" value="${report.id }" />
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div>
				<table id="tblReport">
					<tr height="10px"></tr>
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">汇报人&nbsp;&nbsp;&nbsp;</span>
						</td>
						<td>
							${report.authorName}
						</td>
						
						<td>
							<span style="font-size:18;font-weight:bold">接收人&nbsp;&nbsp;&nbsp;</span>
						</td>
						<td>
							${report.receiverName}
						</td>
					</tr>
					<tr height="10px"></tr>
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">时间范围&nbsp;&nbsp;</span>
						</td>
						<td>
							<c:choose>
							   <c:when test="${report.state==1}">  
							   		<fmt:parseDate value="${report.beginDate}" pattern="yyyy-MM-dd" />
									&nbsp;&nbsp;至&nbsp;&nbsp;  
									<fmt:parseDate value="${report.endDate}" pattern="yyyy-MM-dd" /> 
							   </c:when>
							   <c:otherwise> 
							  		 <div class="input-group input-medium date-picker input-daterange" data-date-format="yyyy-mm-dd" style="display:inline">
									    <input name="beginDate" class="form-control" style="width:220px;height:25px;" type="text" value="${report.beginDate}">
									    <span class="input-group-addon">到</span>
									    <input name="endDate" class="form-control" style="width:220px;height:25px;" type="text" value="${report.endDate}">
									</div>
							   </c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				</table>
			</div>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">工作汇总</th>
						</tr>
					</thead>
					<tr>
						<td id="workSummary">
							<c:choose>
							   <c:when test="${report.state==1}">  
							   	${report.workSummary }
							   </c:when>
							   <c:otherwise> 
							  		 <textarea id="workSummary" name="workSummary" style="width:98.7%;height:10em" >
										${report.workSummary }
									</textarea>
							   </c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				</table>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">自我评价</th>
						</tr>
					</thead>
					<tr>
						<td id="selfEvalu">
							<c:choose>
							   <c:when test="${report.state==1}">  
							   	${report.selfEvaluation }
							   </c:when>
							   <c:otherwise> 
							  		 <textarea id="selfEvaluation" name="selfEvaluation" style="width:98.7%;height:10em" >
										${report.selfEvaluation }
									</textarea>
							   </c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				</table>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">领导评价
								<c:if test="${not empty report.leaderEvaluation}">
									<span style="font-size:1em;">
										&nbsp;（评价时间：${ report.updateTime}）
									</span>
								</c:if>
							</th>
						</tr>
					</thead>
					<tr >
						<td>
							${report.leaderEvaluation }
						</td>
					</tr>
				</table>
		</div>	
	</div>
	<div class="controls" style="float:right;margin-right:30px">
			<c:choose>
				   <c:when test="${report.state==1}">  
				   		已审阅
				   </c:when>
				   <c:otherwise> 
				  		<button type="button" id="btnSubmit" class="btn btn-primary" data-loading-text="正在提交..." style="position: relative;">提交修改</button>
				   </c:otherwise>
			</c:choose>
				
		<button type="button" id="back" class="btn btn-primary"  style="margin-right:550px">返回列表</button>
	</div>
	</div>		
</form>
<script type="text/javascript" src="static/js/log/ReportDetail.js"></script>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
