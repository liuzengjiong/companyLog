<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <style>
	     body{
            background: url("<%= basePath %>/static/images/blackBack2.gif");
        }
        #login{
            position: relative;
            width: 300px;
            height: 500px;
            margin: 80px auto;
            /*border: 1px solid red;*/
            background-color: #f1f1f1;
        }
        .logo{
        	background-color:#7ECEF4;
            width: 300px;
            height: 80px;
            font-size:35px;
            color:#ffffff;
            font-weight:bold;
        }
        #name{
            position: relative;
            width: 260px;
            height: 32px;
            background: cornsilk url("<%= basePath %>/static/images/login2.png") no-repeat ;
            border: 1px solid #cccccc;
            margin: 20px;
            padding-left: 5px;
        }
        #username{
            position: absolute;
            right: 10px;
            width: 200px;
            height: 30px;
            border: none;
            background-color: cornsilk;
            outline: none;
        }
        #psw{
            position: relative;
            width: 260px;
            height: 32px;
            background: cornsilk url("<%= basePath %>/static/images/login2.png") no-repeat 0px -30px ;
            border: 1px solid #cccccc;
            margin: 20px 20px 40px 20px;
            padding-left: 5px;
        }
        #password{
            position: absolute;
            right: 10px;
            width: 200px;
            height: 30px;
            border: none;
            background-color: cornsilk;
            outline: none;
        }
        #psw .message{
            position: absolute;
            width: 220px;
            height: 30px;
            top: 40px;
            left: 30px;
            color: red;
            text-align:center;
/*             display: none; */
        }
        .usertype{
            position: absolute;
            left: 94px;
            width: 300px;
        }
        .submit{
            position: absolute;
            width: 270px;
            height: 34px;
            left: 20px;
            bottom: 200px;
            background-color: cadetblue;
            color: #f1f1f1;
            border:0;
            outline: none;
            cursor: pointer;
            letter-spacing: 5px;
        }
        .forget{
            position: absolute;
            left: 20px;
            bottom: 170px;
            color: blue;
            font-size: 12px;
            cursor: pointer;
        }
    </style>
       <script src="<%= basePath %>/static/js/jquery-1.10.1.min.js"></script>
       
    <script>
    	//获取路径
		function getContextPath(){   
		    var pathName = document.location.pathname;   
		    var index = pathName.substr(1).indexOf("/");   
		    var result = pathName.substr(0,index+1);   
		    return result;   
		}  
        window.onload = function () {
            var oUsername = document.getElementById('username');
            var oPassword = document.getElementById('password');
            var oForget = document.getElementById('forget');
            var oMessage = document.getElementById('psw').getElementsByTagName('label')[0];

            oUsername.focus();
            oForget.onclick = function () {
                alert("请联系管理员修改密码");
            }

        }
    </script>
  </head>
  
  <body>
   <div id="login">
        <form action="<%= basePath %>/login" method="post">
<!--             <img src="<%= basePath %>/static/img/loginback.png"/> -->
			<div class="logo">
				<img src="static/images/write.png"/><span>日志管理系统</span>
           </div> 
            <div id="name">
                <input type="text" id="username" name="username" placeholder=请输入用户名/><br/>
            </div>
            <div id="psw">
                <input type="password" id="password" name="password" placeholder="请输入密码"/><br/>
                <label class="message">${sessionScope['org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS'][0]['message']}</label>
            </div>
            <input class="submit" type="submit" id="btn_" value="登录"/>
            <span class="forget" id="forget">忘记密码</span>
        </form>
    </div>
  </body>
</html>
