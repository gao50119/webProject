<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/saler/CSS/homeCss.css" />
<script type="text/javascript">
function exitSys() {
    var flag = window.confirm("确认退出系统吗?");
	if (flag) {
		//window.top.open('', '_parent', '');
		//window.top.close();
		window.location.href="${pageContext.request.contextPath}/client/login.jsp";
	}
}
</script>
<title>首页</title>
</head>
<body>
<div id="card">
<% if(session.getAttribute("user") != null){%>
<div id="headline"><p>${sessionScope.user.name}，你好</p></div>

<div id="link">
<div class="content">
    
    <a class="biga" href="${pageContext.request.contextPath}/findSaler">销售员管理<font>CLICK</font></a><br>
    <a class="biga" href="${pageContext.request.contextPath}/super/download.jsp">销售报表<font>CLICK</font></a><br>
    <a class="biga" href="${pageContext.request.contextPath}/findProductByManyCondition?type=super">商品查询<font>CLICK</font></a><br>
    <a class="biga" href="${pageContext.request.contextPath}/superFindOrder">销售业绩<font>CLICK</font></a><br>
    <a class="biga" href="${pageContext.request.contextPath}/SaleTrendServlet">销售趋势<font>CLICK</font></a><br>
    <a class="biga" href="${pageContext.request.contextPath}/userLike">用户画像<font>CLICK</font></a>
</div>
<div id="out">
<a href="${pageContext.request.contextPath}/logoutServlet">退出系统</a></div>
</div>
<%} else{%>
<div id="headline"><p><a href="${pageContext.request.contextPath }/client/login.jsp">登录</a></p></div>
<div id="link"></div>
<div class="content"></div>
<%} %>
</div>
</body>
</html>