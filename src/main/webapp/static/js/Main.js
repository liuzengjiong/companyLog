//说明：调整页面布局
var fnResize = function () {
    var tmpBodyHeight = $("body").height();
    var tmpBodyWidth = $("body").width();
    if ($.browser.msie && $.browser.version == 6) {
        $(".main").width(tmpBodyWidth - 220);
        $(".main").height(tmpBodyHeight   - 24 - 6);
        $(".left_menu").height(tmpBodyHeight - 24 - 6);
        $(".well").height(tmpBodyHeight - 24 - 6);
    }
    $("#frmContent").height(tmpBodyHeight);
    window.MainHeight = tmpBodyHeight;
    window.MainWidth = tmpBodyWidth - 220;
    $(".left_menu").height(tmpBodyHeight);
    $(".well").height(tmpBodyHeight-50);
};
$(window).resize(fnResize);
$(document).ready(function(){
	fnResize();
	$._messengerDefaults = {extraClasses: 'messenger-fixed messenger-theme-block messenger-on-top'};
	var params = "?page=1&rows="+SiteConfig.page_default_rows;
	$("a[permission-code^='p']").click(function(){
		$("#frmContent").attr("src",($(this).attr("url")+params));
	});
	$("#btnEditPwd").click(function(){
		$("#frmContent").attr("src","gotoPasswordEdit");
		return false;
	});
	$("#btnStaffInfo").click(function(){
//		window.DialogStaffInfo.show();
		$("#frmContent").attr("src","gotoInfoEdit");
		return false;
	});
	$("#btnQuit").click(function(){
//		$("#frmContent").attr("src","logout");
		window.location.href = "logout";
		return false;
	});

	
//	$("#btnMsgCenter").click(function(){
//		window.DialogMyRemind.show();
//	});
//	GetStaffReminds();
});

function GetStaffReminds(){
	var params = {
		"method":"doGetStaffRemindCount"
	};
	var postUrl = SiteConfig.DoMain+"actions/ajax/ajaxActions.action";
	CreatePost(params,postUrl,function(result){
		if(result.data > 0){
			ShowMsgPrompt();
		}else{
			HideMsgPrompt();
		}
	},true);
	clearTimeout(window.GetStaffRemind);
	window.GetStaffRemind = setTimeout("GetStaffReminds()",60000);
};

function ShowMsgPrompt(){
	var tmpOpacity = parseFloat($("#btnMsgCenter > i").css("opacity"));
	tmpOpacity = tmpOpacity == 1 ? 0 :tmpOpacity;
	tmpOpacity += 0.1;
	$("#btnMsgCenter > i").css("opacity",tmpOpacity);
	clearTimeout(window.ShowMessagePrompt);
	window.ShowMessagePrompt = setTimeout("ShowMsgPrompt()",50);
};
function HideMsgPrompt(){
	clearTimeout(window.ShowMessagePrompt);
	$("#btnMsgCenter > i").css("opacity",1);
};