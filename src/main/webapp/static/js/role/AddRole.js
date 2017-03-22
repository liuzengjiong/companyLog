$(document).ready(function(){
	$("#btnSubmit").click(fnSubmitAddOneRole);
});

function fnSubmitAddOneRole(){
	$.ajax({
        type: "POST",
        url:"role/addOneRole",
        data:$('#addRoleForm').serialize(),
        dataType:"json",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if("fail"==data.code){
            	failMsg(data.msg);
            }else{
            	fnAddNewRoleSuccess();
            }
        }
    });
	return false;//excute_form_validator();
};

function fnAddNewRoleSuccess(){
	window.parent.DialogAddRoleResult = "success";
	window.parent.DialogAddRole.close();
};

function failMsg(str){
	$("#roleNameInfo").text(str);
};
