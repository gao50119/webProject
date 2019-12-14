<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/registerCss.css" />
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/RegisterServlet" method="post">

<div id="bg">

<div id="card">
    <div id="welcome">
          欢迎注册本商城
    </div>
<div id="details">
    <div class="box">
        <div class="ch">用户名:</div>
        <div class="ip">
        <input type="text" name="id" value="${form.id }">
        <div class="error">${form.errors.id }</div>
    </div></div>

    <div class="box">
        <div class="ch">昵称:</div>
        <div class="ip">
        <input type="text" name="name" value="${form.name }" />
        <div class="error">${form.errors.name}</div>
    </div></div>
    
    <div class="box">
        <div class="ch">密码:</div>
        <div class="ip">
        <input type="password" name="password">
        <div class="error">${form.errors.password }</div>
    </div></div>
    
    <div class="box">
        <div class="ch">确认密码:</div>
        <div class="ip">
        <input type="password" name="password2">
        <div class="error">${form.errors.password2 }</div>
    </div></div>
    
    <div class="box">
        <div class="ch">邮箱:</div>
        <div class="ip">
        <input type="text" name="email" value="${form.email }">
        <div class="error">${form.errors.email }</div>
    </div></div>
</div>

    
    <div id="button">
        <input type="reset" value="重置" />
        <input type="submit" value="注册" />
    </div>
    
</div>
</div>
</form>
</body>
</html>