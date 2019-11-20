<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>商品列表</title>
</head>
<body>
    
    <!-- 商品信息区域 -->
    <div id="content-box">
        <div id="commodity-list-title">
            <span>${title}</span>
            <button id="add-commodity">添加商品</button>
        </div>


        <div id="commodity-list-box">

			<!-- 遍历商品信息 -->
			<c:forEach var="commodity" items="${commoditys}">
				<!-- 商品项 -->
	            <div class="commodity-item">
	                <div class="commodity-img">
	                    <img src="images/${commodity.imgPath}" width="210" height="210">
	                </div>
	
	                <div class="commodity-price">
	                    <a>￥</a><span>${commodity.price}</span>
	                </div>
	
	                <div class="commodity-name">
	                    <a id="${commodity.id}">${commodity.name}</a>
	                </div>
	                <div class="commodity-operate">
	                    <button class="commodity-edit" id="${commodity.id}">编辑</button>
	                    <button class="commodity-delete" id="${commodity.id}">删除</button>
	                </div>
	            </div>
			</c:forEach>
            

            <!-- 清除浮动 -->
            <div class="clear-both-box"></div>
        </div>

        <!-- 分页 -->
        <div id="pagination">
            <ul>
                <!-- 上一页 -->
                <c:if test="${pageDto.previousPage}">
                	<li class="no-selected" id="${pageDto.previous}">⇠</li>
                </c:if>
                <c:if test="${!pageDto.previousPage}">
                	<li class="no-selected pag-isable">⇠</li>
                </c:if>
                
                <!-- 遍历分页数据 -->
                <c:forEach var="page" items="${pageDto.pageList}">
               		<li id="${page}" <c:if test="${pageDto.pageNow==page}"> class="pag-selected" </c:if>>${page}</li>
                </c:forEach>
                
                <!-- 下一页 -->
                <c:if test="${pageDto.nextPage}">
	                <li class="no-selected" id="${pageDto.next}">⇢</li>
                </c:if>
                <c:if test="${!pageDto.nextPage}">
	                <li class="no-selected pag-isable">⇢</li>
                </c:if>
            </ul>
        </div>

    </div>


</body>
</html>