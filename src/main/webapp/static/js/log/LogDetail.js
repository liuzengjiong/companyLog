
var total = 0; //初始条数
var requestPage = 1; //请求页数
var pageRows = 10; //每页显示行数


window.onload = function () {    
    
    //刷新列表
    refleshList();
    $("#btnReplyTip").click(cancelReply);
    $("#btnSubmit").click(submit);
}

//刷新列表
function refleshList() {
	var logId = $("#logId").val();
    $.ajax({
        url: "comment/list",  
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步
        data: {
        	"page":requestPage,
        	"rows":pageRows,
        	"logId":logId
        },  
        type: "GET",   //请求方式
        beforeSend: function () {
            //请求前的处理
        },
        success: function (data) {
            //请求成功时处理
            if (data.code == "success") {
            	dealSuccess(data);
            	setPagination();
            }else{
            	alert(data.msg);
            }
        }
    });
}



//设置分页控件
function setPagination(){
    $("#div_pagination").html('<ul id="pagination" class="pagination-sm"></ul>');  //该控件在不刷新页面情况下无法更改页数，只能用此方法实现页数的变动
	var totalPage;
	if(total == 0){
		totalPage = 1;
	}else{
		totalPage = Math.ceil(total/pageRows);
	}
	$('#div_pagination ul').twbsPagination({
        totalPages: totalPage,	//Math.ceil为向上取整
        visiblePages: 20,
        startPage: requestPage,
        first: "首页",  
        prev: "上一页",  
        next: "下一页",  
        last: "未页", 
        initiateStartPageClick:false,
        onPageClick: function (event, page) {
        	requestPage = page;
        	refleshList();
        }
    });
}


function submit(){
	//获取html内容，返回: <p>hello</p>
    var html = editor.getContent();
    var replyId = $("#replyTip").attr("replyId");
    var logId = $("#btnSubmit").attr("logId");
    
    $.ajax({
        url: "comment/addOne",  
        dataType: "json",   //返回格式为json
        async: true, //请求是否异步，默认为异步
        data: {
        	"content":html,
        	"replyId":replyId,
        	"logId":logId
        },  
        type: "POST",   //请求方式
        beforeSend: function () {
            //请求前的处理
        },
        success: function (data) {
            //请求成功时处理
            if (data.code == "success") {
            	var lastPage = Math.ceil((total+1)/pageRows);
            	requestPage = lastPage;
            	refleshList();
            	editor.setContent("");
            	cancelReply();
            	window.parent.Messenger().post({
    				message:"评论成功！"
    			   ,hideAfter:3
    			});
            }else{
            	alert(data.msg);
            }
        }
    });
}

function reply(replyId,nickname){
	$("#replyTip").attr("replyId",replyId);
	$("#replyTip").html("回复   "+nickname);
	$("#btnReplyTip").show();
}

function cancelReply(){
	$("#replyTip").attr("replyId","");
	$("#replyTip").html("");
	$("#btnReplyTip").hide();
}

function dealSuccess(data){
	$("#commentList").html("");
	total=  data.total;
	$("#commetNum").html(total);
	if(total==0){
		$("#commentList").html("还没有人评论哦，快来抢沙发吧");
		return;
	}
	var comments = data.data;
	for (var i = 0; i < comments.length; i++) {
		/* 评论DIV */
		 var commentDiv = document.createElement('div');
		 var commentTxt = "<div class='content'>"+comments[i].content+"</div>";
         $(commentDiv).html(commentTxt);
         $(commentDiv).addClass("commentDiv");
         if(comments[i].replyId){
        	 /*被回复的评论DIV*/
        	 var replyDiv = document.createElement('div');
        	 var replyNameSpan = document.createElement('span');
        	 replyNameSpan.innerHTML = "回复：   "+comments[i].replyName;
        	 var replyTxt = "<div class='replyContent'>"+comments[i].replyContent+"</div>";
        	 $(replyDiv).html(replyTxt);
        	 $(replyDiv).prepend($(replyNameSpan));
        	 
        	 $(replyNameSpan).addClass("replyNameSpan");
        	 $(replyDiv).addClass("replyDiv");
        	 
        	 $(commentDiv).prepend( $(replyDiv) );
         }
         
         var informationDiv = document.createElement('div');
         var html = "<span class=\"informationSpan\">"+comments[i].nickname+"</span>"
         			+" 于 "
         			+"<span class=\"informationSpan\">"+comments[i].createTime+"</span>"
         			+" 评论。"
         			
         			+"<a href='javascript:reply(\""+comments[i].id+"\",\""+comments[i].nickname+"\")' >回复</a> ";
         $(informationDiv).html(html).addClass("informationDiv");
         
         
         $(commentDiv).append($(informationDiv));
         
         $("#commentList").append(  $(commentDiv) );
//         '<hr> style="border:1 dashed #987cb9" width="auto" color="#987cb9" size="1" />'
//         $(oTd_4).addClass("display_time");
         //ids.splice(0,ids.length); //清空数组
	}
}

