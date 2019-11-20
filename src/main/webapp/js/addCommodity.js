// 关闭按钮单击事件
$(document).on('click', '.adc-close-button', function() {
	closeAddCommodityWindow();
});

//添加按钮单击事件
$(document).on('click', '.adc-add-button', function(){
    // 获取参数
    var sort = $('#adc-sort').val();
    var name = $('#name').val();
    var price = $('#price').val();
    var stock = $('#stock').val();
    var imgPath = $('#img-path').val();
    var introduction = $('#introduction').val();

    // 效验输入参数
    if (sort == null){
        alert('读取类型失败，无法添加');
        return;
    }
    if ('' == $.trim(name)){
        alert('请输入商品名称');
        return;
    }
    if ('' == $.trim(price)){
        alert('请输入商品价格');
        return;
    }
    if ('' == $.trim(stock)){
        stock = 0;
    }

    // 发送添加请求
    $.post('addCommodity', {
    	name : name,
    	price : price,
    	imgPath : imgPath,
    	stock : stock,
    	introduction : introduction,
    	sortId : sort
    }, function(data){
    	if ('success' == $.trim(data)){
    		closeAddCommodityWindow();
    		// 请求刷新首页
    		getAllCommodity(-1, 1, 8);
    		
    	} else {
    		alert('添加失败！');
    	}
    });
});

// 显示添加商品窗口
function showAddCommodityWindow() {
	var html = '';
	html += '<div id="add-commodity-box">';
	html += '<div id="adc-input-box">';
	html += '<div class="adc-title">';
	html += '<span>商品信息添加</span>';
	html += '<div><a>说明：</a>添加商品信息</div>';
	html += '</div>';
	html += '<div id="adc-input">';
	html += '<div class="adc-input-item">商品类别&nbsp;&nbsp;&nbsp;&nbsp;';
	html += ' <select id="adc-sort">';
	html += '</select>';
	html += '</div>';
	html += '<div class="adc-input-item">商品名称&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="name" placeholder="请输入商品名称" autocomplete="off"></div>';
	html += '<div class="adc-input-item">商品价格&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" id="price" placeholder="请输入商品价格"></div>';
	html += '<div class="adc-input-item">商品库存&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" id="stock" placeholder="请输入商品库存"></div>';
	html += '<div class="adc-input-item">商品图片&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="img-path" placeholder="请输入商品图片路径"></div>';
	html += '<div class="adc-commodity-introduction">商品简介';
	html += '<textarea id="introduction" placeholder="请输入商品简介"></textarea>';
	html += '</div>';
	html += '<div class="adc-add-button"><button>添加</button></div>';
	html += '<div class="adc-close-button"><button></button></div>';
	html += '</div></div></div>';
	$('body').append(html);

	// 请求全部分类信息
	$.get('getSorts', function(json) {
		$.each(json, function(index, item) {
			$('#adc-sort').append(
					'<option value="' + item.id + '">' + item.name
							+ '</option>');
		});
	}, 'json');
}
// 关闭添加商品窗口
function closeAddCommodityWindow() {
	$('#add-commodity-box').remove();
}

