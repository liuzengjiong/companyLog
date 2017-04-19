<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<!-- <link rel="stylesheet" href="static/css/blog_default.css" /> -->
<link rel="stylesheet" href="static/css/blog.css" />
</head>
<body>
<!-- 	<div id="mainContent"> -->
<!-- 	<div class="forFlow"> -->
		

<!--done-->
	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="javascript:void(0)"><i class="icon-eye-open"></i>
				收藏日志列表</a>
		    </div>

	   	</div>
	</div>

<div class="entrylist">
			<c:forEach var="log" items="${logs}" varStatus="status">
				<c:if test="${status.count!=0}">
					<div class="entrylistItem">
						<div class="entrylistPosttitle"><a href="log/detail/${log.id }" class="entrylistItemTitle" href="javascript:void(0)">${log.title }</a></div>
						<div class="entrylistPostSummary"><div class="c_b_p_desc">摘要: ${log.summary }</div></div>
						<div class="entrylistItemPostDesc">
							<span class="postItem">${log.authorName }</span>
							<span class="postItem">发布于</span>  
							<span class="postItem"> ${log.createTime }</span> 
							<span class="postItem">阅读(${log.readNum }) | 评论 (${log.commentNum })</span>   
							<a class="postItem" href="javascript:void(0)" id="collect" logId="${log.id }" ></a>
			
							<a  class="postItem" href="log/detail/${log.id }">阅读全文</a>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${ fn:length(logs) == 0 }">
				你暂时还没有收藏哦，快去日志广场看看日志吧
			</c:if>
	<p>
	
	</p>
</div>
	<script src="static/js/log/logSquare/LogsOfCollection.js"></script>
	<%@ include file="/templets/Pagination.jsp"%>	

<!-- 	</div>end: forFlow -->
<!-- 	</div> -->
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
