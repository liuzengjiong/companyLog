//禁止表单重复提交

//1、引入此文件，
//2、在页面中加入：<input type="hidden" id="haveSubmitForbid"></input>
//3、在表单提交时时调用 setHaveSubmitToForbid() 
/*
<input type="hidden" id="haveSubmitForbid"></input>
<script type="text/javascript" src="static/js/forbid-form-resubmit.js"></script>
 */
$(document).ready(function(){
	forbidReSubmit();
});


//设置已经提交表单了
function setHaveSubmitToForbid(){
	$("#haveSubmitForbid").val("true");
}

//禁止重复提交（禁止返回缓存中的此页面）
function forbidReSubmit(){
	if($("#haveSubmitForbid").val() == "true"){
		window.history.back();
	}
}