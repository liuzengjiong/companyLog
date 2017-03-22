$(document).ready(function(){
	$("#btnSubmit").click(fnSubmitEditStaff);
});
function fnSubmitEditStaff(){
	$.ajax({
        type: "POST",
        url:"user/editOneStaff",
        data:$('#editStaffForm').serialize(),
        dataType:"json",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if("fail"==data.code){
            	failMsg(data.msg);
            }else{
            	fnEditStaffSuccess();
            }
        }
    });
	return false;//excute_form_validator();
};

function failMsg(str){
	$("#errorInfo").text(str);
};
function fnEditStaffSuccess(){
	window.parent.DialogEditStaffResult = "success";
	window.parent.DialogEditStaff.close();
};