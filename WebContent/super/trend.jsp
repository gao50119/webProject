<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="zh-cn">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/saler/CSS/orderslistCss.css" />
<title>订单业绩</title>
</head>
<body>
	<table>
		<tr><td>
		<div id="wholeform">
		<div id="colum">
		<table style="width:100%;">
		<tr>
		<td width="40%" align="center">商品编号</td>
		<td width="40%" align="center">商品名称</td>
		<td width="20%" align="center">销售量</td>
		</tr>
		</table>
		</div>
		
		
		<div id="form">
		<table style="width:100%;">
		<c:forEach items="${list}" var="p">
			<tr>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="40%">${p.gNo}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="40%">${p.gName}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${p.getNum()}</td>
			</tr>
		</c:forEach>
		</table>
		</div>
		
		</div>
		</td></tr>
	</table>
	
	<table>
	<tr>
	<td><div id="button">
		<div class="but"><input onclick="window.location.href='${pageContext.request.contextPath}/super/home.jsp'" value="返回" class="but"/></div></div>
	</td>
	</tr>
	</table>
</body>
</html>