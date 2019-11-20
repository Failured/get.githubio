// 关闭按钮单击事件
$(document).on('click', '.edc-close-button', function(){
    closeEditCommodityWindow();
});


// 添加按钮单击事件
$(document).on('click', '.edc-add-button', function(){
    // 获取参数
	var id = $('input#commodity-id').val();
    var sort = $('#edc-sort').val();
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
    $.post('updateCommodity', {
    	id : id,
    	name : name,
    	price : price,
    	imgPath : imgPath,
    	stock : stock,
    	introduction : introduction,
    	sortId : sort
    }, function(data){
    	if ('success' == $.trim(data)){
    		closeEditCommodityWindow();
    		// 确定数据
    		var type = $('#center-box ul li.selected').attr('id');
    		if ('all' == $.trim(type)){
    			type = -1;
    		}
    		var page = $('#pagination ul li.pag-selected').attr('id');
    		// 请求刷新首页
    		getAllCommodity(type, page, 8);
    		
    	} else {
    		alert('修改失败！');
    	}
    });
});

// 显示编辑商品窗口
function showEditCommodityWindow(id){
    var html = '';
    html += '<div id="edit-commodity-box">';
    html += '<div id="edc-input-box">';
    html += '<div class="edc-title">';
    html += '<span>商品信息编辑</span>';
    html += '<div><a>说明：</a>编辑商品信息</div>';
    html += '</div>';
    html += '<div id="edc-input">';
    html += '<div class="edc-input-item">商品类别&nbsp;&nbsp;&nbsp;&nbsp;';
    html += '<select id="edc-sort">';
    html += '</select>';
    html += '</div>';
    html += '<input type="hidden" id="commodity-id">';
    html += '<div class="edc-input-item">商品名称&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="name" placeholder="请输入商品名称" autocomplete="off"></div>';
    html += '<div class="edc-input-item">商品价格&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" id="price" placeholder="请输入商品价格"></div>';
    html += '<div class="edc-input-item">商品库存&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" id="stock" placeholder="请输入商品库存"></div>';
    html += '<div class="edc-input-item">商品图片&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="img-path" placeholder="请输入商品图片路径"></div>';
    html += '<div class="edc-commodity-introduction">商品简介';
    html += '<textarea id="introduction" placeholder="请输入商品简介"></textarea>';
    html += '</div>';
    html += '<div class="edc-add-button"><button>保存</button></div>';
    html += '<div class="edc-close-button"><button></button></div>';
    html += '</div>';
    html += '</div>';
    html += '</div>';
    $('body').append(html);

    // 请求全部分类信息
    $.get('getSorts', function(json){
		$.each(json, function(index, item){
			$('#edc-sort').append('<option value="' + item.id + '">' + item.name + '</option>');
		});
	},'json');
    
    // 请求商品数据
    $.get('getCommodity', {
    	commodityId : id
    }, function(json){
        // 根据请求到的数据赋值
    	$('input#commodity-id').val(json.id);
    	$('input#name').val(json.name);
    	$('input#price').val(json.price);
    	$('input#stock').val(json.stock);
    	$('input#img-path').val(json.imgPath);
    	$('textarea#introduction').val(json.introduction);
    	$("select#edc-sort").val(json.sort.id);
    }, 'json');
}

// 关闭添加商品窗口
function closeEditCommodityWindow(){
    $('#edit-commodity-box').remove();
}