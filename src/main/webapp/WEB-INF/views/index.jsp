<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/commodityList.css">
    <link rel="stylesheet" href="css/addCommodity.css">
    <link rel="stylesheet" href="css/editCommodity.css">
    <link rel="stylesheet" href="css/deleteCommodity.css">
    <link rel="stylesheet" href="css/info.css">
</head>
<body>
    
    <!-- 分类导航栏 -->
    <div id="sort-navigation-bar">
        <div id="center-box">
            <!-- 分类列表 -->
            <ul>
                <li class="selected" id="all">全部商品</li>
                <!-- 遍历分类 -->
                <c:forEach var="sort" items="${sorts}">
                	<li id="${sort.id}">${sort.name}</li>
                </c:forEach>
            </ul>

            <!-- 商品搜索输入框 -->
            <div id="search-input">
                <input type="text" id="commodity-name-search" placeholder="请输入商品名称关键字查询">
            </div>

            <!-- 用户区域 -->
            <div id="user-box">
                <a class="user-logout" href="logout"></a>
                <a class="user-name">${sessionScope.username}</a>
                <!-- 清除浮动 -->
                <div class="clear-both-box"></div>
            </div>

            <!-- 清除浮动 -->
            <div class="clear-both-box"></div>
        </div>
        
    </div>

	
	<div id="view-box"></div>


    <!-- 引入JS -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/index.js"></script>
    <script src="js/addCommodity.js"></script>
    <script src="js/editCommodity.js"></script>
    <script src="js/deleteCommodity.js"></script>
    <script src="js/commodityList.js"></script>
</body>
</html>