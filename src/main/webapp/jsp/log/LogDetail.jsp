<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.companyLog.util.SiteConfig"%>
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
<%@ include file="/templets/UEditor-head.jsp"%>
<!-- <link rel="stylesheet" href="static/css/blog_default.css" /> -->
<link rel="stylesheet" href="static/css/blog.css" />
<link rel="stylesheet" href="static/css/blog-common.css" />

</head>
<body>
<!-- 	<div id="mainContent"> -->
<!-- 	<div class="forFlow"> -->
		

<!--done-->
	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="javascript:void(0)"><i class="icon-eye-open"></i>日志详情</a>
		    </div>
	   	</div>
	</div>
<div id="post_detail">
<!--done-->
<div id="topics">
	<div class="post">
		<h1 class="postTitle">
			<a id="cb_post_title_url" class="postTitle" href="javascipt:void(0)">${log.title}</a>
		</h1>
		<span id="updateTime">最后修改：${log.updateTime }</span>
		<div class="clear"></div>
		<div class="postBody">
			<div id="cnblogs_post_body">
${log.content }
			</div>
			<div class="entrylistItemPostDesc">
				<a class="postItem" href="log/gotoListOfOne?authorId=${log.authorId }">${log.authorName}</a> 
				<span class="postItem">发布于</span>  
				<span class="postItem"> ${log.createTime }</span> 
				<span class="postItem">阅读(${log.readNum }) | 评论 (<span id="commetNum">${log.commentNum }</span>)</span>   
				<a class="postItem" href="log/gotoEdit/${log.id }" rel="nofollow">编辑</a> 
				<a class="postItem" href="javascript:void(0)" id="collect" logId="${log.id }" haveCollect="${log.haveCollect }">收藏</a>
			</div>
				<script type="text/javascript" src="static/js/log/LogCollect.js"></script>
		</div>
			
	</div>
</div>	
	<!-- 评论 -->
<div class="commentContainer">	
	<h3>评论区</h3>
	<div class="commentPane">
		<input type="hidden" id="logId" value="${log.id }" />
		<div class="commentList" id="commentList">
		</div>
	</div>
	<div id="div_pagination" class="pagination div_pagination" >
			<ul id="pagination" class="pagination-sm"></ul>
	</div>
	<span class="replyTip" id="replyTip" replyId=""></span> 
	<button id="btnReplyTip"  class="btn" type="button" style="display:none">取消</button>
	<div class="commentArea">
		<script id="commentContainer" style="width:100%;height:50%;" type=text/plain></script>
	</div>
	
	<div class="divBelowUEditor" style="float:right;">
		<button id="btnSubmit" class="btn btnBelowUEditor" type="button" 
			logId="${log.id }" >
			&nbsp;&nbsp;发表评论
		</button>	
	</div>
</div>
	<script type="text/javascript">
		var editor = null;
		 window.UEDITOR_HOME_URL = "<%=SiteConfig.UEditor_Path %>";
		 window.UEDITOR_CONFIG.maximumWords = 1000;
		editor = UE.getEditor('commentContainer', {
			toolbars:[['FullScreen', 'Undo', 'Redo','bold','italic', 
            'underline', 'link', 'insertcode']]
		});
		
		editor.ready(function() {
		    //设置编辑器的内容
// 		    editor.setContent('hello');
		    //获取html内容，返回: <p>hello</p>
		    var html = editor.getContent();
		    //获取纯文本内容，返回: hello
		    var txt = editor.getContentTxt();
		});
	</script>
</div><!--end: topics 文章、评论容器-->
</div>

<!-- 	</div>end: forFlow -->
<!-- 	</div> -->
<script src="static/js/jquery.twbsPagination.min.js"></script>
<!-- <script src="static/js/jquery.form.js"></script> -->
<script type="text/javascript" src="static/js/log/LogDetail.js"></script>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
