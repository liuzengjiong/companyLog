
//日志列表
$(document).ready(function(){
	$("#btnMyCollection").click(gotoMyCollection);
	$("#btnKeyword").click(keyWordSearch);
	setPagination();
});

function gotoMyCollection(){
	var params = "?page=1&rows="+SiteConfig.page_default_rows;
	window.location.href = "log/gotoListOfCollection"+params;
}

//设置分页控件

function setPagination(){
	var total = Number($("#total").text());
	var rows = Number($("#rows").text());
	var requestPage =  Number($("#page").text());
//    $("#div_pagination").html('<ul id="pagination" class="pagination-sm"></ul>');  //该控件在不刷新页面情况下无法更改页数，只能用此方法实现页数的变动
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

function keyWordSearch(){
	requestPage = 1;
	var rows = Number($("#rows").text());
	gotoPage(requestPage,rows);
}

