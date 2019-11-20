// 添加商品按钮单击事件
$(document).on('click', '#add-commodity', function(){
    showAddCommodityWindow();
});

// 编辑商品按钮单击事件
$(document).on('click', '.commodity-edit', function(){
    var id = $(this).attr('id');
    showEditCommodityWindow(id);
});

// 删除商品按钮单击事件
$(document).on('click', '.commodity-delete', function(){
    var id = $(this).attr('id');
    showDeleteCommodityWindow(id);
});

// 商品名称点击事件
$(document).on('click', '.commodity-name a', function(){
	var id = $(this).attr('id');
	
	// 请求商品数据
	$.get('info',{
		id : id
	},function(data){
		$('#view-box').html(data);
	});
});

// 分页按钮点击
$(document).on('click', '#pagination ul li', function(){
	if ($(this).is('.pag-isable')){
        return;
    }
    // 获取页码
    var page = $(this).attr('id');
    var selected = $('.pag-selected').attr('id');
    if (page == selected){
        return;
    }
    
    // 获取分类
    var id = $('#center-box ul li.selected').attr('id');
    if ('all' == $.trim(id)){
    	id = -1;
    }
    
    // 判断是否为搜索结果分页
    var isSelected = false;
    $('#center-box ul li').each(function(){
    	if ($(this).is('.selected')){
    		isSelected = true;
    	}
    });
    if (!isSelected){
    	id = -2;
    }
    // 请求分页数据
    getAllCommodity(id, page, 8);
});

//分页控制
$(document).on('click', '#pagination ul li', function(){

    if ($(this).is('.no-selected')){
        return;
    }

    // 清除选中
    $('#pagination ul li').each(function(){
        $(this).removeClass('pag-selected');
    });

    $(this).addClass('pag-selected');
});
