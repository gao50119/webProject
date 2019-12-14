<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/infoCss.css" />
<title>游戏详情</title>
</head>
<body>
<div id="divpagecontent">
	<table width="100%" border="0" cellspacing="0">
	<tr>
	<td>
	    <table>
	    <tr>
	    <td>
	    <div id="link"><div id="inlink">
		<div class="card"><a href="${pageContext.request.contextPath }/client/menu_search.jsp">购物首页</a></div>
		<div class="card"><a href="${pageContext.request.contextPath}/showProductByPage?gType=${p.gType}">&nbsp;${p.gType}</a></div>
		<div class="card"><font>${p.gName}</font></div>
		</div></div>
		</td>
		</tr>
		</table>
		
		<table cellspacing="0">
		<tr>
		<td>
			<table id="content">
			<tr>
			<td width="30%">
				<div id="imgcard">
				<p>
				<img src="${pageContext.request.contextPath}${p.gImgurl}" />
				</p>
				</div>
				
				<c:if test="${p.gState==1 }">
				<div>
				<div class="card"><a href="${pageContext.request.contextPath}/addCart?gNo=${p.gNo}">加入购物车</a>
				<font>${tips}</font></div>
				</div>
				</c:if>
				<c:if test="${p.gState==2 }">
				<div class="card"><font id="p">已下架</font></div>
				</c:if>
			</td>
				
			<td>
			<div id="de">
			<div class="details" id="name">${p.gName}</div>
			<div class="details">售价：<font>￥${p.gPrice}</font></div>
			<div class="details">类别：${p.gType }</div>
			</div>
			<div class="details" id="de">
			<b>内容简介：</b>
			<p>${p.gDescription}</p></div>
		    </td>
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