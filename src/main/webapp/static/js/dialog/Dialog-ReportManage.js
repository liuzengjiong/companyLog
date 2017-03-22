$(document).ready(function(){
	
});

function InitializeReportReceiver(){
	if(window.DialogReportReceiver === undefined){
		var iframeElement = $("<iframe>").attr({
			"frameborder" : "0",
			"scrolling" : "yes",
			"src" : "user/gotoReportReceiverList"
		}).css({
			"width" : "50%",
			"height" : "500px"
		});
		window.DialogSubmitReport = $.scojs_modal({
			target : '#modal_reportReceiver',
			title : '选择汇报领导',
			content:iframeElement
		});
	}
};


//工作汇报提交
function InitializeReportSubmit(){
	if(window.DialogSubmitReport === undefined){
		var iframeElement = $("<iframe>").attr({
			"frameborder" : "0",
			"scrolling" : "no"
		}).css({
			"width" : "100%",
			"height" : "500px"
		});
		window.DialogSubmitReport = $.scojs_modal({
			target : '#modal_submitReport',
			title : '提交工作汇报',
			content:iframeElement
		});
	}
};

//编辑一个汇报
function InitializeEditOneReport(){
	if(window.DialogEditOneReport === undefined){
		var iframeElement = $("<iframe>").attr({
			"frameborder" : "0",
			"scrolling" : "no"
		}).css({
			"width" : "100%",
			"height" : "350px"
		});
		window.DialogEditOneReport = $.scojs_modal({
			target : '#modal_editReport',
			title : '汇报编辑',
			content:iframeElement
		});
	}
};

//查看领导评价
function InitializeViewLeaderEvalu(){
	if(window.DialogViewLeaderEvalu === undefined){
		window.DialogViewLeaderEvalu = $.scojs_modal({
			target : '#modal_viewleaderevalu',
			title : '领导评价',
			height:400
		});
	}
};

//领导评价确认框
function InitializeConfirmLeaderEvalu(){
	window.DialogConfirmLeaderEvalu = $.scojs_confirm({
		target : '#confirm_leaderEvalu',
		content : "评价成功是否返回？",
		action : function() {
			this.close();
		},
		buttonText : {
			"left" : "是",
			"right" : "否"
		}
	});
};

//提醒窗体
function InitializeDoGetMyRemind(){
	if(window.DialogMyRemind === undefined){
		window.DialogMyRemind = $.scojs_modal({
			target : '#modal_myRemind',
			title : '消息中心',
			remote: SiteConfig.DoMain+"actions/reportActions.action?method=doGetMyRemind",
			onClose : function(){
				GetStaffReminds();
			}
		});
	}
};


