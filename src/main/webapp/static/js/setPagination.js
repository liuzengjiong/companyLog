//日志列表

$(document).ready(function(){
	setPagination();
});


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

function refreshThisPage(){
	var rows = Number($("#rows").text());
	var requestPage =  Number($("#page").text());
	gotoPage(requestPage,rows);
}

function gotoPage(requestPage,rows){
	var params = "";
	var url = $("#listUrl").text();
	if(url.indexOf("?")== -1){
		params = "?page="+requestPage+"&rows="+rows;
	}else{
		params = "&page="+requestPage+"&rows="+rows;
	}
	window.location.href = url+params;
}

