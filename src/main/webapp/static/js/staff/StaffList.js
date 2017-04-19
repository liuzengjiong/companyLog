$(document).ready(function() {
	$("#btnNewStaff").click(fnCreateNewStaff);
	$("#btnKeyword").click(keyWordSearch);
	$('button[operate="deleteStaff"]').click(fnDeleteOneStaff);
	$('button[operate="editStaff"]').click(fnEditOneStaff);
	$('button[operate="resetPwd"]').click(fnResetStaffPassword);
	
});

function fnCreateNewStaff() {
	window.parent.DialogCreateStaff.options.onClose = function() {
		if (window.parent.DialogCreateStaffResult !== undefined && window.parent.DialogCreateStaffResult === "success") {
			window.location = "user/gotoList";
		}
	}
	window.parent.DialogCreateStaff.show();
};

function fnDeleteOneStaff(){
	var staffId = $(this).attr("staffId");
	window.parent.DialogConfirm.options.action = function(){
		$.ajax({
	        type: "POST",
	        url:"user/deleteOneStaff",
	        data:{
	        	id:staffId
	        },
	        dataType:"json",
	        error: function(request) {
	            alert("Connection error");
	        },
	        success: function(data) {
	            if("fail"==data.code){
	            	alert(data.msg);
	            }else{
	        		window.parent.Messenger().post({
	        			message:"员工已删除！"
	        		   ,hideAfter:3
	        		});
	        		window.location = "user/gotoList";
	            }
	        }
	    });
	};
	window.parent.DialogConfirm.show();
};

function fnEditOneStaff(){
	var content = window.parent.DialogEditStaff.options.content;
	var staffId = $(this).attr("staffId");
	var contentUrl = "user/gotoEditStaff/"+staffId;
	$(content).attr("src",contentUrl);
	window.parent.DialogEditStaff.options.onClose = function(){
		if (window.parent.DialogEditStaffResult !== undefined && window.parent.DialogEditStaffResult === "success") {
			window.location = "user/gotoList";
			window.parent.Messenger().post({
				message:"员工信息已更改！"
			   ,hideAfter:3
			});
		}
	};
	window.parent.DialogEditStaff.show();
};

function fnResetStaffPassword(){
	var staffId = $(this).attr("staffId");
	window.parent.DialogConfirmResetPwd.options.action = function(){
		$.ajax({
	        type: "POST",
	        url:"user/resetOneStaffPwd",
	        data:{
	        	id:staffId
	        },
	        dataType:"json",
	        error: function(request) {
	            alert("Connection error");
	        },
	        success: function(data) {
	            if("fail"==data.code){
	            	alert(data.msg);
	            }else{
	        		window.parent.Messenger().post({
	        			message:"密码已重置！"
	        		   ,hideAfter:3
	        		});
	            }
	        }
	    });
	};
	window.parent.DialogConfirmResetPwd.show();
};


function keyWordSearch(){
	requestPage = 1;
	var rows = Number($("#rows").text());
	gotoPage(requestPage,rows);
}
