// 页面加载后执行
$(function(){
	// 默认请求全部商品信息
	getAllCommodity(-1, 1, 8);
});

// 分类菜单选择
$(document).on('click', '#center-box ul li', function(){
	// 清除搜索框内容
	$('#commodity-name-search').val('');
    // 清除其它选择状态
    $('#center-box ul li').each(function(){
        $(this).removeClass('selected');
    });

    // 设置当前为选择状态
    $(this).addClass('selected');

    // 获取选择值
    var id = $(this).attr('id');
    
    // 发起请求
    if ($.trim(id) == 'all'){
        getAllCommodity(-1, 1, 8);
    }else{
    	getAllCommodity(id, 1, 8);
    }
});


// 获取商品信息请求
function getAllCommodity(id, page, size){
	
	// 清除菜单选择状态
    $('#center-box ul li').each(function(){
        $(this).removeClass('selected');
    });

    // 设置当前为选择状态
    if (id == -1){
    	$('#center-box ul li[id=all]').addClass('selected');
    } else if (id == -2){
    	// 搜索
    	var keyword = $('#commodity-name-search').val();
    	$.get('search', {
    		keyword : keyword,
        	page : page,
        	size : size
        }, function(data){
        	$('#view-box').html(data);
        });
    	return;
    } else {
    	$('#center-box ul li[id=' + id + ']').addClass('selected');
    }
    $.get('allCommodity', {
    	sortId : id,
    	page : page,
    	size : size
    }, function(data){
    	$('#view-box').html(data);
    });
}

// 搜索框回车事件
$('#commodity-name-search').bind('keypress',function(event){
    if(event.keyCode == "13") {
    	// 清除菜单选择状态
        $('#center-box ul li').each(function(){
            $(this).removeClass('selected');
        });
    	
        var keyword = $('#commodity-name-search').val();
        
        $.get('search', {
        	keyword : keyword,
        	page : 1,
        	size : 8
        }, function(data){
        	$('#view-box').html(data);
        });
    }
});
