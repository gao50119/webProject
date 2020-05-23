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
<form name="Form1" action="${pageContext.request.contextPath}/superFindOrder?user=${sessionScope.user.id}&op=find" method="post">
    <div id="head">
    <table>
		<tr>
		<td>订单编号</td>
		<td><input type="text" name="orderid" value="" /></td>
		<td>销售员编号：</td>
		<td><input type="text" name="salerid" size="15" value="" /></td>
		<td>类别 
		  <select name="category">
		    <option value ="全部类别" selected="selected">全部类别</option>
            <option value ="卡牌">卡牌</option>
            <option value ="冒险">冒险</option>
            <option value="竞技">竞技</option>
            <option value="塔防">塔防</option>
            <option value="模拟">模拟</option>
            <option value="休闲">休闲</option>
            <option value="恐怖">恐怖</option>
            <option value="RPG">RPG</option>
            <option value="策略">策略</option>
            <option value="动作">动作</option>
            <option value="射击">射击</option>
            <option value="音乐">音乐</option>
            <option value="体育">体育</option>
            <option value="格斗">格斗</option>
          </select>
		</td>
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
		<td width="10%" align="center">负责人员</td>
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
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">${order.user.id}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${order.product.gNo}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">${order.product.gName}</td>
			<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.saler.id}</td></tr>
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
		<div class="but"><input onclick="window.location.href='${pageContext.request.contextPath}/super/home.jsp'" value="返回" class="but"/></div></div>
	</td>
	</tr>
	</table>
</form>
</body>
</html>