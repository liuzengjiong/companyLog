$(document).ready(function(){
	InitializeConfirmWindow();
	InitializeConfirmResetPwd();
	InitializeConfirmCancelCollectWindow();
});

function InitializeConfirmWindow(){
	window.DialogConfirm = $.scojs_confirm({
		target : '#confirm_delete',
		content : "确定要删除么？",
		action : function() {
			this.close();
		},
		buttonText : {
			"left" : "确定",
			"right" : "取消"
		}
	});
};

function InitializeConfirmCancelCollectWindow(){
	window.DialogConfirmCancelCollect = $.scojs_confirm({
		target : '#confirm_calcel_Collect',
		content : "确定要取消收藏么？",
		action : function() {
			this.close();
		},
		buttonText : {
			"left" : "确定",
			"right" : "取消"
		}
	});
};

function InitializeConfirmResetPwd(){
	window.DialogConfirmResetPwd = $.scojs_confirm({
		target : '#confirm_resetPwd',
		content : "确定重置该员工密码？",
		action : function() {
			this.close();
		},
		buttonText : {
			"left" : "确定",
			"right" : "取消"
		}
	});
};