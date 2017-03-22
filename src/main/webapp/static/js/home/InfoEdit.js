$(document).ready(function(){
	$("#btnSubmit").click(fnSubmitEdit);
});
function fnSubmitEdit(){
	$.ajax({
        type: "POST",
        url:"editInfo",
        data:$('#editInfoForm').serialize(),
        dataType:"json",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if("fail"==data.code){
            	failMsg(data.msg);
            }else{
            	fnEditInfoSuccess();
            }
        }
    });
	return false;//excute_form_validator();
};

function failMsg(str){
	$("#errorInfo").text(str);
};
function fnEditInfoSuccess(){
	window.parent.location.href = "gotoHome";
};