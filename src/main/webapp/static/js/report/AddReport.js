var total = 0; //初始条数
var requestPage = 1; //请求页数
var pageRows = 10; //每页显示行数


//新建汇报
$(document).ready(function(){
	initializeDatepicker();
	$("#btnSubmit").click(submit);
	$("#back").click(back);
	$("#beginDate").change(refreshWorkLog);
	$("#endDate").change(refreshWorkLog);
});


function submit(){
	$("#addForm").attr("action","log/reportAdd").submit();
	setHaveSubmitToForbid();
	return false;
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

/**
 * 返回按钮
 */
function back(){
	window.history.back();
}


function refreshWorkLog(){
	var authorId = $("#authorId").text();
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	if(!beginDate || !endDate){
		return;
	}
	 $.ajax({
	        url: "log/getWorkListByTime",  
	        dataType: "json",   //返回格式为json
	        async: true, //请求是否异步，默认为异步
	        data: {
	        	"page":requestPage,
	        	"rows":pageRows,
	        	"authorId":authorId,
	        	"beginDate":beginDate,
	        	"endDate":endDate
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
        	refreshWorkLog();
        }
    });
}

function dealSuccess(data){
	
	total =  data.total;
	if(total==0){
		$("#workLogTip").html("<h4>汇报时间段内无工作日志</h4>");
		$(".workLogContainer").hide();
		return;
	}
	$("#workLogTip").html("");
	$(".workLogContainer").show();
	var logs = data.data;
	 $("#workLogBody").html("");
	for (var i = 0; i < logs.length; i++) {
		 
		 var number = (requestPage-1)*pageRows+1+i;
		 
		 var numberTd = "<td>"+number+"</td>";
		 var titleTd = "<td style='width:40%;'><a href='log/detail/"+logs[i].id+"'>"+logs[i].title+"</a></td>";
		 var summaryTd = "<td style='font-size:0.6em;width:40%;'>"+logs[i].summary+"</td>";
		 var createTimeTd = "<td>"+logs[i].createTime+"</td>";
		 
		 var trDom = $("<tr>"+numberTd+titleTd+summaryTd+createTimeTd+"</tr>");
		 $("#workLogBody").append(trDom);
	}
}
