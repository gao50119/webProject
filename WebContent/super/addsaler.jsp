<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/registerCss.css" />
<title>新增销售</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/addSaler?user=${sessionScope.user.id}" method="post">

<div id="bg">

<div id="card">
    <div id="welcome">新增销售
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
    
    <div class="box">
        <div class="ch">负责类别:</div>
        <div class="ip">
        <select name="category">
		    <option value ="" selected="selected">--请选择类别--</option>
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
    </div></div>
</div>

    
    <div id="button">
        <input type="reset" value="重置" />
        <input type="submit" value="注册" />
        <input onclick="window.location.href='${pageContext.request.contextPath}/findSaler'" value="返回" />
    </div>
    
</div>
</div>
</form>
</body>
</html>