<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/indexCss.css" />
<title>首页</title>
</head>
<body>
<% if(session.getAttribute("user") == null){%>
<%response.sendRedirect("/saler34/client/login.jsp"); %>
 <%} %>
<div id="card">
<div id="headline"><p>${sessionScope.user.gtype}，你好</p></div>
<div id="link">
<div class="content">
<a class="biga" href="${pageContext.request.contextPath }/client/menu_search.jsp">购物<font>CLICK</font></a><br>
<a class="biga" href="${pageContext.request.contextPath }/client/myAccount.jsp">我的账号<font>CLICK</font></a><br>
<a class="biga" href="${pageContext.request.contextPath }/findOrderByManyCondition?name=${sessionScope.user.id}">我的账单<font>CLICK</font></a>
</div>
<div id="out">
<a href="${pageContext.request.contextPath }/logoutServlet">退出账号</a></div>

</div>
</div>
</body>
</html>