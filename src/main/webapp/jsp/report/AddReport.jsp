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
		    	 <a href="javascript:void(0)"><i class="icon-eye-open"></i>新建汇报</a>
		    </div>
	   	</div>
	</div>
<em id="authorId" style="display:none">${user.id }</em>
<form action="" method="post" id="addForm">
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div>
				<table>
					<tr height="10px"></tr>
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">汇报人&nbsp;&nbsp;&nbsp;</span>
						</td>
						<td>
							${user.nickname}
						</td>
					</tr>
					<tr height="10px"></tr>
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">时间范围&nbsp;&nbsp;</span>
						</td>
						<td>
							<div class="input-group input-medium date-picker input-daterange" data-date-format="yyyy-mm-dd" style="display:inline">
							    <input id="beginDate" name="beginDate" class="form-control" style="width:220px;height:25px;" type="text" value="">
							    <span class="input-group-addon">到</span>
							    <input id="endDate" name="endDate" class="form-control" style="width:220px;height:25px;" type="text" value="">
							</div>
						</td>
					</tr>
				</table>
<h4 id="workLogTip"></h4>
<div class="workLogContainer" style="display:none;">	
	<h4>工作日志预览</h4>
	<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>标题</th>
								<th>摘要</th>
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
				<table id="tblReport">
					<tr height="10px"></tr>
					
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">提交领导&nbsp;&nbsp;</span>
						</td>
						<td>
							<select  id="receiverSelect" name="receiverId">
							<c:forEach var="receiver" items="${receivers}" varStatus="status"> 
								<c:if test="${status.count!=0}">
									<option  value="${receiver.id}" />${receiver.nickname}
								</c:if>
							</c:forEach>
							</select>
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
						<td id="workSumma">
							<textarea id="workSummary" name="workSummary" style="width:98.7%;height:10em" >
							</textarea>
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
						<textarea id="selfEvaluation" name="selfEvaluation" style="width:98.7%;height:10em" >
						</textarea>
						</td>
					</tr>
				</table>
		</div>	
	</div>
	<div class="controls" style="float:right;margin-right:30px">
		<button type="button" id="btnSubmit" class="btn btn-primary" data-loading-text="正在提交..." style="position: relative;">提交</button>
		<button type="button" id="back" class="btn btn-primary"  style="margin-right:550px">取消</button>
	</div>
	</div>		
</form>
<script type="text/javascript" src="static/js/report/AddReport.js"></script>


	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
