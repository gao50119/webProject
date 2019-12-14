<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎，登陆</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/loginCss.css" />
<script>
function turnToRegister() {
	window.location.href="../client/register.jsp";
}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
<div id="bg">
<div id="mainPart">
    <div class="but">
        <div class="ch">用户名:</div>
        <div>
        <input type="text" name="username">
    </div></div>
    <br>
    <div class="but">
        <div class="ch">密码:</div>
        <div>
        <input type="password" name="password">
    </div></div>
    
    <div class="font">${requestScope["register_message"]}</div>
    <div id="clickbut">
        <input type="submit" value="登录" />
        <input type="button" value="注册" onclick="turnToRegister()" />
    </div>
</div>
</div>
</form>
</body>
</html>