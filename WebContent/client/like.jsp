<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/productsearchlistCss.css" />
<title>商品列表</title>
</head>
<body>
<div id="whole">
<table id="wholetable">
<tr>
<td>
    <div id="head">
    <table><tr>
	<td><div class="headcard"><a href="${pageContext.request.contextPath }/toMenuSearchServlet">购物首页</a></div></td>
	<td><div class="headcard">全部商品</div></td>
	<td><div class="headcard">商品列表</div></td>
	</tr></table>
	</div>
	
	<table>
	<tr>
	<td><div id="lefthead">
		<h1>商品目录</h1>
		<hr /></div>

		<table id="alllist">
		<tr>
		<c:forEach items="${orders}" var="order">
		<td id="picandword">
		<div id="pic">
		<p><a href="${pageContext.request.contextPath}/findProductById?gNo=${order.product.gNo}">
		<img src="${pageContext.request.contextPath}${order.product.gImgurl}" />
		</a></p>
		</div>
		<div id="card">
		<a href="${pageContext.request.contextPath}/findProductById?gNo=${order.product.gNo}">游戏名： ${order.product.gName}
		<br />售价：￥${order.product.gPrice} </a>
		</div>
		</td>
		</c:forEach>
		</tr>
		</table>
		
	</td>
	</tr>
	</table>
</td>
</tr>
</table>
</div>
</body>
</html>