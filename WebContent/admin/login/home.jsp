<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/CSS/homeCss.css" />
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
<title>Insert title here</title>
</head>
<body>
<div id="card">
<div id="headline"><p>管理员，你好</p></div>
<div id="link">
<div class="content">
    <a class="biga" href="${pageContext.request.contextPath}/listProduct">商品管理<font>CLICK</font></a><br>
    <a class="biga" href="${pageContext.request.contextPath}/admin/products/download.jsp">销售榜单<font>CLICK</font></a><br>
    <a class="biga" href="${pageContext.request.contextPath}/findOrders?&type=admin">订单管理<font>CLICK</font></a>
</div>
<div id="out">
<a href="javascript:void(0)" onclick="exitSys()">退出系统</a></div>
</div>
</div>
</body>
</html>