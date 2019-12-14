<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/listCss.css" />
<title>Insert title here</title>
</head>
<body>
    <div id="top">
        <p>${sessionScope.user.name}，你好</p>
        <div id="toppart">
        <a href="${pageContext.request.contextPath }/index.jsp">首页</a>
        <a href="${pageContext.request.contextPath }/client/menu_search.jsp">购物</a>
        </div>
    </div>
    
    <div id="whole">
    <div id="head">
	<table>
		<tr>
		<td width="15%" align="center">订单编号</td>
		<td width="10%" align="center">订单费用</td>
		<td width="15%" align="center">购买时间</td>
		<td width="20%" align="center">购买用户</td>
		<td width="15%" align="center">商品编号</td>
		<td width="15%" align="center">商品名称</td>
		<td width="10%" align="center">删除</td>
		</tr>
	</table>
	</div>
	<div id="form">
	<table>
		<c:forEach items="${orders}" var="order">
			<tr>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">${order.oNo}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.oMoney}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">${order.ordertime}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${order.user.name }</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">
			<a href="${pageContext.request.contextPath}/findProductById?gNo=${order.product.gNo}">${order.product.gNo }</a></td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">
			<a href="${pageContext.request.contextPath}/findProductById?gNo=${order.product.gNo}">${order.product.gName}</a></td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
			<a href="${pageContext.request.contextPath}/deleteOrders?type=client&oNo=${order.oNo}&id=${order.user.id}">删除</a></td>
		</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	
</body>
</html>