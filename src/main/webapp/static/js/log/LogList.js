//日志列表

$(document).ready(function(){
	$("#btnNewLog").click(gotoAddLog);
	$("#btnNewReport").click(gotoAddReport);
	$("#btnMyReport").click(gotoMyReport);
	setPagination();
});

function gotoAddLog(){
	window.location.href="log/gotoAdd";
}
function gotoAddReport(){
	window.location.href = "log/gotoReportAdd"
}
function gotoMyReport(){
	window.location.href = "log/gotoReportListOfAuthor";
}

function setPagination(){
	var total = Number($("#total").text());
	var rows = Number($("#rows").text());
	var requestPage =  Number($("#page").text());
	var totalPage;
	if(total == 0){
		totalPage = 1;
	}else{
		totalPage = Math.ceil(total/rows);
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
        	gotoPage(requestPage,rows);
        }
    });
}

function gotoPage(requestPage,rows){
	window.location.href = "log/gotoListOfOne?page="+requestPage+"&rows="+rows;
}

