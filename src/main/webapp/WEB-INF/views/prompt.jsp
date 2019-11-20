<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>暂无该商品信息</title>
</head>
<body>

	<div id="content-box">
		<div id="commodity-list-title">
            <span>暂无商品信息</span>
            <button id="add-commodity">添加商品</button>
        </div>
    </div>

	<div id="no-commodity-info"><span>${message}</span></div>
	
	<!-- 引入JS -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/info.js"></script>
</body>
</html>