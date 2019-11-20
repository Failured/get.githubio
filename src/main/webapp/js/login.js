// 登录按钮单击事件
$('.input-div button').click(function(){
    // 获取参数
    var username = $('.input-div input[name="username"]').val();
    var password = $('.input-div input[name="password"]').val();

    // 效验数据
    if ('' == $.trim(username)){
        // 弹窗提示请输入用户名
        alert('请输入用户名');
        return;
    }
    if ('' == $.trim(password)){
        // 弹窗提示请输入密码
        alert('请输入密码');
        return;
    }
    
    // 请求登录
    requestLogin(username, password);
});

//绑定回车事件
$(document).keyup(function(){
	if(event.keyCode==13){
		// 获取参数
	    var username = $('.input-div input[name="username"]').val();
	    var password = $('.input-div input[name="password"]').val();
	    // 效验数据
	    if ('' == $.trim(username)){
	        // 弹窗提示请输入用户名
	        alert('请输入用户名');
	        return;
	    }
	    if ('' == $.trim(password)){
	        // 弹窗提示请输入密码
	        alert('请输入密码');
	        return;
	    }
	    
		requestLogin(username, password);
	}
});

// 请求登录
function requestLogin(username, password){
    $.post('doLogin',{
        username : username,
        password : password
    }, function(data){
    	// 判断返回结果
        if ('success' == $.trim(data)){
            // 跳转到首页
        	location.href = "/commodity-management/"; 
        } else {
            // 弹窗登录失败
        	alert('登录失败！');
        }
    });
}

