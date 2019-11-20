// 页面加载后执行
$(function(){
	// 清除菜单栏选中状态
	$('#center-box ul li').each(function(){
        $(this).removeClass('selected');
    });
});