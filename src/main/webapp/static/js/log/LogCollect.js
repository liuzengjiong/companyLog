

$(document).ready(function(){  
	initCollect();
   
});

function initCollect(){
	$("[id=collect]").each(function(){
		 var haveCollect = $(this).attr("haveCollect");
		 if(haveCollect == "true"){
		 		$(this).html("<span style='color:black'>已收藏</span>[取消]");
	 	}else{
	 		$(this).html("收藏");
	 	}
		 $(this).click(collect);
	 });
	 
}

function collect(){
	var dom = $(this);
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
	            	if(data.data){ //收藏操作data.data == true
	            		dom.html("<span style='color:black'>已收藏</span>[取消]");
	            	}else{
	            		dom.html("收藏");
	            	}
	            	window.parent.Messenger().post({
	    				message:data.msg
	    			   ,hideAfter:3
	    			});
	            }else{
	            	alert(data.msg);
	            }
	        }
	    });
}

