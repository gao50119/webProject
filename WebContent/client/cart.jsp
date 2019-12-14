<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/cartCss.css" />
<title>购物车</title>
</head>
<body>
<body class="main">
	<p:user/>
	<div>
		<table width="100%" border="0" cellspacing="0">
		<tr>
		<td>
		    <div id="top">
			<div style="margin:5px 10px 5px 0px">
			<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
			</div></div>
			
			
			<table>
			<tr>
			<td>
			<div id="whole">
			    <div id="form">
				<table width="100%">
				<tr>
				<td>
				    <div id="colum">
					<table width="100%">
					<tr>
					<td width="30%">序号</td>
					<td width="40%">商品名称</td>
					<td width="15%">价格</td>
					<td width="15%">取消</td>
					</tr>
					</table>
					</div>
					
					<div id="middle">
					<!-- 循环输出商品信息 -->
					<c:set var="total" value="0" /> 
					<c:forEach items="${cart}" var="entry" varStatus="vs">
					<table width="100%">
					<tr>
						<td width="30%">${vs.count}</td>
						<td width="40%">${entry.key.gName }</td>
						<td width="15%">${entry.key.gPrice }</td>
						<td width="15%">
					    <!-- 删除商品 -->
						<a href="${pageContext.request.contextPath}/changeCart?gNo=${entry.key.gNo}&count=0"
						style="color:#eeeeee; font-weight:bold; font-size: 15px;" onclick="javascript:return cart_del()">删除</a>
						</td>
					</tr>
					</table>
					<c:set value="${total+entry.key.gPrice}" var="total" />
					</c:forEach>
					</div>
                    
                    <div id="bottom">
					<table>
					<tr>
					<td>
					<font>合计：&nbsp;&nbsp;${total}元</font>
					</td>
					</tr>
					</table>
					
					<div>
					<!--继续购物 -->
					<a href="${pageContext.request.contextPath}/client/menu_search.jsp">继续购物</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--结账 -->
					<a href="${pageContext.request.contextPath}/payServlet">结账</a>
					<span>${tips}</span>
					</div>
					</div>
				</td>
				</tr>
				</table>
				</div>
		    </div>
			</td>
			</tr>
			</table>
			
		</td>
		</tr>
		</table>
	</div>
</body>
</body>
</html>