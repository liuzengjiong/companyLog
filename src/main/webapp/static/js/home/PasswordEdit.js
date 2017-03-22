$(document).ready(function(){
	$("#btnSubmit").click(fnSubmitEdit);
});
function fnSubmitEdit(){
	$.ajax({
        type: "POST",
        url:"editPassword",
        data:$('#editPasswordForm').serialize(),
        dataType:"json",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if("fail"==data.code){
            	failMsg(data.msg);
            }else{
            	fnEditPwdSuccess();
            }
        }
    });
	return false;//excute_form_validator();
};

function failMsg(str){
	$("#errorInfo").text(str);
};
function fnEditPwdSuccess(){
	window.parent.location.href = "gotoHome";
};