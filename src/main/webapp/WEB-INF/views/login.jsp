<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    
    <!-- 登录窗口 -->
    <div id="login-bakc">
        <div class="login-title-back">
            <span>商品管理系统</span><a>登录</a>
        </div>

        <!-- 用户名输入框 -->
        <div class="input-div">
            <input type="text" name="username" id="username" placeholder="请输入用户名">
        </div>

        <!-- 密码输入框 -->
        <div class="input-div">
            <input type="password" name="password" id="password" placeholder="请输入密码">
        </div>

        <!-- 登录按钮 -->
        <div class="input-div">
            <button>登录</button>
        </div>
    </div>


    <!-- 引入JS -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/login.js"></script>
</body>
</html>