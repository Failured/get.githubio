<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>详细页面</title>
</head>
<body>

    <!-- 商品信息页面 -->
    <div id="commodity-info-box">
        <!-- 上半部分 -->
        <div id="on-info-box">
            <!-- 商品图片 -->
            <div class="commodity-img-info">
                <img src="images/${commodity.imgPath}" width="390" height="390">
            </div>


            <!-- 商品信息 -->
            <div class="commodity-info">
                <!-- 商品名称 -->
                <div class="name-info">
                    <span>${commodity.name}</span>
                </div>

                <!-- 商品价格 -->
                <div class="price-info">
                    <a>￥</a><span>${commodity.price}</span>
                </div>


                <!-- 其它信息 -->
                <div class="other-info">
                    <div class="other-info-center">
                        <span>成交量&nbsp;&nbsp;<a>${commodity.volume}</a></span>
                        <a class="vertical-line"></a>
                        <span>库存&nbsp;&nbsp;<a>${commodity.stock}</a></span>
                        <a class="vertical-line"></a>
                        <span>点击量&nbsp;&nbsp;<a>${commodity.clickVolume}</a></span>
                        <!-- 清除浮动 -->
                        <div class="clear-both-box"></div>
                    </div>
                </div>


                <!-- 商品简介 -->
                <div class="introduction-info">
                    <span>${commodity.introduction}</span>
                </div>
            </div>

        </div>
    </div>
    
    <!-- 引入JS -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/info.js"></script>
</body>
</html>