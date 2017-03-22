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

</head>
<body>
	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="javascript:void(0)"><i class="icon-eye-open"></i>汇报管理</a>
		    </div>
	   	</div>
	</div>
<em id="authorId" style="display:none">${report.authorId}</em>
<form action="" method="post" id="reportForm">
	<input type="hidden" name="id" value="${report.id }" />
	<input type="hidden" name="state" value="1" />
	<input type="hidden" name="act" value="check" />
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
					</tr>
					<tr height="10px"></tr>
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">时间范围&nbsp;&nbsp;</span>
						</td>
						<td>
							<span id="beginDate">
							<fmt:parseDate value="${report.beginDate}" var="date" pattern="yyyy-MM-dd"/>
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />
							</span>
								&nbsp;&nbsp;至&nbsp;&nbsp;  
							<span id="endDate">
							<fmt:parseDate value="${report.endDate}" var="date" pattern="yyyy-MM-dd"/>
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />
							</span>
						</td>
					</tr>
				</table>
			</div>
<div class="workLogContainer">	
	<h4>工作日志</h4>
	<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th style="max-width:30%">标题</th>
								<th style="max-width:40%">摘要</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody id="workLogBody">
						</tbody>
					</table>
	<div id="div_pagination" class="pagination div_pagination" >
			<ul id="pagination" class="pagination-sm"></ul>
	</div>
</div>
<script src="static/js/jquery.twbsPagination.min.js"></script>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">工作汇总</th>
						</tr>
					</thead>
					<tr>
						<td id="workSummary">
							${report.workSummary }
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
							${report.selfEvaluation }
						</td>
					</tr>
				</table>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">领导评价
								<c:if test="${not empty report.leaderEvaluation}">
									<span style="font-size:1em;">
										&nbsp;（上次评价：${ report.updateTime}）
									</span>
								</c:if>
							</th>
						</tr>
					</thead>
					<tr >
						<td>
						<textarea id="leaderEvalu" name="leaderEvaluation" style="width:98.7%;height:10em" >
							${report.leaderEvaluation }
						</textarea>
						</td>
					</tr>
				</table>
		</div>	
	</div>
	<div class="controls" style="float:right;margin-right:30px">
		<button type="button" id="btnSubmit" class="btn btn-primary" data-loading-text="正在提交..." style="position: relative;">提交审阅</button>
		<button type="button" id="back" class="btn btn-primary"  style="margin-right:550px">返回列表</button>
	</div>
	</div>		
</form>

<script type="text/javascript" src="static/js/report/ReportCheck.js"></script>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
