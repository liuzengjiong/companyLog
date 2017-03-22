<%@page import="org.companyLog.util.SiteConfig"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" charset="UTF-8">
   var SiteConfig = {
  	'DoMain' : '<%=SiteConfig.DoMain%>',
  	'page_default_rows':'10'	
  };
 // document.oncontextmenu = function () { event.returnValue = false; };
  window.onhelp = function() { return false ;};
  document.onkeydown = function() {
	  if ((window.event.altKey) &&
	      ((window.event.keyCode == 37) ||     
	      (window.event.keyCode == 39))) {    
	      event.returnValue = false;
	  }
	  if ((event.keyCode == 116) ||                  
	      (event.ctrlKey && event.keyCode == 82)) {  
	      event.keyCode = 0;
	      event.returnValue = false;
	  }
	  if (event.keyCode == 122) { event.keyCode = 0; event.returnValue = false; } 
	  if (event.ctrlKey && event.keyCode == 78) event.returnValue = false;
	  if (event.shiftKey && event.keyCode == 121) event.returnValue = false; 
  };
</script>
<style type="text/css">
.control-group .input-error-info{color:red;font-size:12px;height:23px}
#header_nav_container{background-color:#FFFFFF;border-bottom:1px solid #5CC3F1;}
#header_nav a{padding:0px 15px 0px 0px;line-height:40px;text-decoration: none;color:#000000;display: inline-block;font-size: 12px;}
.nav_prep{background: url('<%=SiteConfig.DoMain%>static/images/breadcrumb.png');background-position: center right;background-repeat: no-repeat;}
#header_nav i{margin-right:3px;margin-top:0px;opacity:0.5;}
</style>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>static/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>static/css/common.css" />
<!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="<%=SiteConfig.DoMain%>static/bootstrap/css/bootstrap-ie6.css" />
<![endif]-->
<!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="<%=SiteConfig.DoMain%>static/bootstrap/css/ie.css" />
<![endif]-->
<!--[if lt IE 9]>
	<script type="text/javascript" src="<%=SiteConfig.DoMain%>static/js/html5.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=SiteConfig.DoMain%>static/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>static/js/placeholder.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>static/js/form-validator.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>static/js/common.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>static/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if(!$.browser.msie){
			$("head:first").append(
				'<style>'
			   +'select,textarea,'
			   +'input[type="text"],'
			   +'input[type="password"],'
			   +'input[type="datetime"],'
			   +'input[type="datetime-local"],'
			   +'input[type="date"],'
			   +'input[type="month"],'
			   +'input[type="time"],'
			   +'input[type="week"],'
			   +'input[type="number"],'
			   +'input[type="email"],'
			   +'input[type="url"],'
			   +'input[type="search"],'
			   +'input[type="tel"],'
			   +'input[type="color"]'
			   +'{height:30px}'
			   +'</style>'
			);
		}
	});
</script>

