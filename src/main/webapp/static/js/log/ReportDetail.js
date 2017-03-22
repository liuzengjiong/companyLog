$(document).ready(function() {
	$("#btnSubmit").click(submit);
	$("#back").click(back);
	initializeDatepicker();
});
/**
 * 提交按钮
 */
function submit(){
	$("#reportForm").attr("action","report/update");
	setHaveSubmitToForbid();
	$("#reportForm").submit();
	
	return false;
}

/**
 * 返回按钮
 */
function back(){
	window.history.back();
}
function initializeDatepicker(){
	$(".date-picker").datepicker({
	    language: "zh-CN",
	    autoclose: true,
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm-dd"//日期格式
	});
}
