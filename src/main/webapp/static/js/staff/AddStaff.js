$(document).ready(function(){
	$("#btnSubmit").click(fnAddNewStaffInfo);
});

function fnAddNewStaffInfo(){
//	var s = $("#roleSelect option:selected").val();
	$.ajax({
        type: "POST",
        url:"user/addOne",
        data:$('#addStaffForm').serialize(),
        dataType:"json",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if("fail"==data.code){
            	failMsg(data.msg);
            }else{
            	fnAddNewStaffSuccess();
            }
        }
    });
	return false;
	//return excute_form_validator();
};

function fnAddNewStaffSuccess(){
	window.parent.DialogCreateStaffResult = "success";
	window.parent.Messenger().post({
		message:"新员工已添加！"
	   ,hideAfter:3
	});
	window.parent.DialogCreateStaff.close();
};

function failMsg(str){
	$("#errorInfo").text(str);
};
