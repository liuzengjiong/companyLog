

$(document).ready(function(){  
	initCollect();
   
});

function initCollect(){
	$("[id=collect]").each(function(){
		 $(this).html("<span style='color:black'>已收藏</span>[取消]");
		 $(this).click(collect);
	 });
	 
}

function collect(){
	var dom = $(this);
	
	window.parent.DialogConfirmCancelCollect.options.action = function(){
		var logId = dom.attr("logId");
		 $.ajax({
		        url: "log/collect",  
		        dataType: "json",   //返回格式为json
		        data: {
		        	id:logId
		        },  
		        type: "POST",   //请求方式
		        beforeSend: function () {
		            //请求前的处理
		        },
		        success: function (data) {
		            //请求成功时处理
		            if (data.code == "success") {
		            	window.parent.Messenger().post({
		    				message:data.msg
		    			   ,hideAfter:3
		    			});
		            	//调用forbid
		            	setHaveSubmitToForbid();
		            	//调用setPagination.js的方法
		            	refreshThisPage();
		            }else{
		            	alert(data.msg);
		            }
		        }
		    });
	}
	window.parent.DialogConfirmCancelCollect.show();
}

