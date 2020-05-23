<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/menuCss.css" />
<title>首页</title>
</head>
<body>
<table>
    <% if(session.getAttribute("user") == null){%>
    <tr><td>
    <div id="hello"><a href="${pageContext.request.contextPath }/client/login.jsp">登录</a></div></td></tr>
    <%}else{ %>
    <tr><td>
    <div id="hello"><p>${sessionScope.user.name}，你好</p></div></td></tr>
    <tr>
    <td><div class="cross"><a href="${pageContext.request.contextPath }/client/myAccount.jsp">我的账号</a></div></td>
    <td><div class="cross"><a href="${pageContext.request.contextPath }/client/cart.jsp">购物车</a></div></td>
    <td><div class="cross"><a href="${pageContext.request.contextPath }/logoutServlet">退出账号</a></div></td>
    <td><div class="cross"><a href="${pageContext.request.contextPath }/recommendServlet?id=${sessionScope.user.id}">猜你喜欢</a></div>
    </td></tr>
    <%}%>
</table>
<div id="cardMenu">
<div id="divmenu">
    <div id="select">
    <table>
    <tr>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=卡牌">卡牌</a></div></td>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=冒险">冒险</a></div></td>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=竞技">竞技</a></div></td>
	</tr>
	<tr>	
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=塔防">塔防</a></div></td>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=模拟">模拟</a></div></td>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=休闲">休闲</a></div></td>
	</tr>
	<tr>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=恐怖">恐怖</a></div></td> 
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=RPG">RPG</a></div></td> 
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=策略">策略</a></div></td>
	</tr>
	<tr>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=动作">动作</a></div></td>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=射击">射击</a></div></td> 
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=音乐">音乐</a></div></td>
	</tr>
	<tr>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=体育">体育</a></div></td>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage?gType=格斗">格斗</a></div></td>
		<td><div class="cross"><a href="${pageContext.request.contextPath}/showProductByPage">全部商品</a></div></td>
	</tr>
	</table>
	</div>		
</div>
</div>
<div id="divsearch">
<form action="${pageContext.request.contextPath }/MenuSearchServlet" id="searchform">
	<table>
		<tr>
			<td>
				<input type="text" name="textfield" class="inputtable" id="textfield" value="请输入商品名"
				onmouseover="this.focus();" onclick="my_click(this, 'textfield');" onBlur="my_blur(this, 'textfield');"/> 
				<button id="searchbut" onclick="search()">搜索</button>
			</td>
		</tr>
	</table>
</form>
</div>
</body>

<script type="text/javascript">
/**
 * my_click和my_blur均是用于前台页面搜索框的函数
 */
//鼠标点击搜索框时执行
function my_click(obj, myid){
	//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
	if (document.getElementById(myid).value == document.getElementById(myid).defaultValue){
	  document.getElementById(myid).value = '';
	  obj.style.color='#000';
	}
}
//鼠标不聚焦在搜索框时执行
function my_blur(obj, myid){
	//鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
	if (document.getElementById(myid).value == ''){
	 document.getElementById(myid).value = document.getElementById(myid).defaultValue;
	 obj.style.color='#000';
 }
}

/**
 * 点击搜索按钮执行的函数
 */
function search(){
	document.getElementById("searchform").submit();
}
</script>
</html>