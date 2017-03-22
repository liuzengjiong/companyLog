

//编辑日志
$(document).ready(function(){
	forbidReSubmit();
	$("#btnSubmit").click(submit);
	setGroupSelect();
	setRoleSelect();
});

function submit(){
	//获取html内容，返回: <p>hello</p>
    var html = editor.getContent();
    //获取纯文本内容，返回: hello
    var txt = editor.getContentTxt();
	$("#content").val(html);
	$("#summary").val(txt);
	$("#editForm").attr("action","log/update").submit();
	setHaveSubmitToForbid();
	return false;
}

function setGroupSelect(){
	setGroupTip();
	$("#groupSelect").change(setGroupTip);
}
function setGroupTip(){
	var groupTip = $("#groupSelect option:selected").attr("groupTip");
	$("#groupTipDiv").html(groupTip);
}
function setRoleSelect(){
	setRoleTip();
	$("#rolesSelect").change(setRoleTip);
}
function setRoleTip(){
	var roleTip = "";
	$("#rolesSelect option:selected").each(function(){
		if(roleTip.length!=0){
			roleTip += "、 ";
		}
		roleTip += $(this).text();
	})
	if(roleTip.length == 0){
		roleTip = "所有人都";
	}
	roleTip = roleTip + "可以看到这篇日志";
	$("#roleTipDiv").html(roleTip);
}
