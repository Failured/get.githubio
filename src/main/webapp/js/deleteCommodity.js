// 取消按钮单击事件
$(document).on('click', 'button.delete-c-cancel', function(){
    closeDeleteCommodityWindow();
});
// 确定按钮单击事件
$(document).on('click', 'button.delete-c-ok', function(){
    // 获取ID
   var id = $('input#commodity-id').val();
   
    // 请求删除
   $.get('deleteCommodity', {
	   id : id
   }, function(data){
	   if ('success' == $.trim(data)){
   		closeDeleteCommodityWindow();
   		// 确定数据
		var type = $('#center-box ul li.selected').attr('id');
		if ('all' == $.trim(type)){
			type = -1;
		}
		var page = $('#pagination ul li.pag-selected').attr('id');
		// 请求刷新首页
		getAllCommodity(type, page, 8);
   		
   	} else {
   		alert('删除失败！');
   	}
   });
});

// 显示是否删除弹窗
function showDeleteCommodityWindow(id){
    var html = '';
    html += '<div id="delete-commodity-box">';
    html += '<div id="delete-commodity-back">';
    html += '<input type="hidden" id="commodity-id">';
    html += '<div class="delete-c-title"><span>确认删除？</span></div>';
    html += '<div class="delete-c-button">';
    html += '<button class="delete-c-ok">确定</button>';
    html += '<button class="delete-c-cancel">取消</button></div>';
    html += '</div>';
    html += '</div>';
    $('body').append(html);

    $('input#commodity-id').val(id);
}

// 关闭是否删除弹窗
function closeDeleteCommodityWindow(){
    $('#delete-commodity-box').remove();
}