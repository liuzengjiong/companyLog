$(document).ready(function(){
	$("#btnSubmit").click(fnSubmitEditRole);
});

function fnSubmitEditRole(){
	$.ajax({
        type: "POST",
        url:"role/editOneRole",
        data:$('#editRoleForm').serialize(),
        dataType:"json",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if("fail"==data.code){
            	failMsg(data.msg);
            }else{
            	fnEditRoleSuccess();
            }
        }
    });
	return false;//excute_form_validator();
};

function failMsg(str){
	$("#roleNameInfo").text(str);
};
function fnEditRoleSuccess(){
	window.parent.DialogEditRoleResult = "success";
	window.parent.DialogEditRole.close();
};