<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="zh-cn">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/CSS/orderslistCss.css" />
<title>Insert title here</title>
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/findOrderByManyCondition?type=admin" method="post">
    <div id="head">
    <table>
		<tr>
		<td>订单编号</td>
		<td><input type="text" name="id" value="" /></td>
		<td>买家编号：</td>
		<td><input type="text" name="name" size="15" value="" /></td>
		</tr>
	</table>
	</div>
	
	<table>
		<tr><td>
		<div id="wholeform">
		<div id="colum">
		<table>
		<tr>
		<td width="15%" align="center">订单编号</td>
		<td width="10%" align="center">订单费用</td>
		<td width="15%" align="center">购买时间</td>
		<td width="15%" align="center">购买用户</td>
		<td width="20%" align="center">商品编号</td>
		<td width="15%" align="center">商品名称</td>
		<td width="10%" align="center">删除订单</td>
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
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">${order.user.id }</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
			<a href="${pageContext.request.contextPath}/findProductById?gNo=${order.product.gNo}&type=admin">${order.product.gNo }</a></td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">
			<a href="${pageContext.request.contextPath}/findProductById?gNo=${order.product.gNo}&type=admin">${order.product.gName}</a></td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
			<a href="${pageContext.request.contextPath}/deleteOrders?type=admin&oNo=${order.oNo}">删除</a></td></tr>
		</c:forEach>
		</table>
		</div>
		
		</div>
		</td></tr>
	</table>
	
	<table>
	<tr>
	<td><div id="button">
		<div class="but"><button type="submit" id="search" name="search" class="but">查询</button></div>
		<div class="but"><input type="reset" name="reset" value="重置" class="but" /></div>
		<div class="but"><input type="button" onclick="history.go(-1)" value="返回" class="but" /></div></div>
	</td>
	</tr>
	</table>
</form>
</body>
</html>