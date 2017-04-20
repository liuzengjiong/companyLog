$(document).ready(function(){
	$("#btnNewRole").click(fnNewRole);
	$('button[operate="deleteRole"]').click(fnDeleteOneRole);
	$('button[operate="editRole"]').click(fnEditOneRole);
	$("#btnKeyword").click(keyWordSearch);
});

function fnNewRole(){
	window.parent.DialogAddRole.options.onClose = function(){
		if (window.parent.DialogAddRoleResult !== undefined && window.parent.DialogAddRoleResult === "success") {
			window.parent.DialogAddRoleResult = undefined;
			window.location = "role/gotoList"
			window.parent.Messenger().post({
				message:"新角色已添加！"
			   ,hideAfter:3
			});
		}
	};
	window.parent.DialogAddRole.show();
};

function fnEditOneRole(){
	var roleid = $(this).attr("roleid");
	var content = window.parent.DialogEditRole.options.content;
	var contentUrl = "role/gotoEditRole/"+roleid;
	$(content).attr("src",contentUrl);
	window.parent.DialogEditRole.options.onClose = function(){
		if (window.parent.DialogEditRoleResult !== undefined && window.parent.DialogEditRoleResult === "success") {
			window.location = "role/gotoList";
			window.parent.Messenger().post({
				message:"角色信息已更改！"
			   ,hideAfter:3
			});
		}
	};
	window.parent.DialogEditRole.show();
};

function fnDeleteOneRole(){
	var roleId = $(this).attr("roleid");
//	$("#hidDeleteRoleID").val($(this).attr("roleid"));
	window.parent.DialogConfirm.options.action = function(){
		$.ajax({
	        type: "POST",
	        url:"role/deleteOneRole",
	        data:{
	        	id:roleId
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
	        			message:"角色信息已删除！"
	        		   ,hideAfter:3
	        		});
	        		window.location = "role/gotoList";
	            }
	        }
	    });

	};
	window.parent.DialogConfirm.show();
};

function keyWordSearch(){
	requestPage = 1;
	var rows = Number($("#rows").text());
	gotoPage(requestPage,rows);
}
