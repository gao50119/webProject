<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/saler/CSS/productslistCss.css" />
<title>商品管理</title>
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/findSaler?op=find&user=${sessionScope.user.id}" method="post">
    <div id="head">
    <table>
        <tr>
        <td>销售员编号
		<input type="text" name="id" value="" /></td>
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
			<td align="center" width="30%">销售员编号</td>
			<td align="center" width="10%">销售员名称</td>
			<td align="center" width="10%">负责类别</td>
			<td width="30%" align="center">邮箱</td>
			<td width="10%" align="center">编辑</td>
			<td width="10%" align="center">删除</td>
			</tr>
		</table>
		</div>
		
		<div id="form">
		<table>
			<c:forEach items="${ul}" var="u">
                <tr>
				<td width="30%">${u.id}</td>
				<td width="10%">${u.name}</td>
				<td width="10%">${u.getgType()}</td>
				<td width="30%">${u.email}</td>
				<td width="10%">
				<a href="${pageContext.request.contextPath}/findSaler?type=edit&id=${u.id}">编辑</a>
				</td>
				<td width="10%">
				<a href="${pageContext.request.contextPath}/delSaler?id=${u.id}&user=${sessionScope.user.id}" onclick="javascript:return p_del()">
				删除
				</a>
				</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		
		
		
		</div>
		</td></tr>
		
		
    </table>
    
    <div id="add">
	<button type="button" name="add" value="添加" onclick="addProduct()">添加</button>
	</div>
    
    <table>
        <tr>
			<td><div id="button">
			<div class="but"><button class="but" type="submit" name="search" value="查询">查询</button></div>
			<div class="but"><input class="but" type="reset" name="reset" value="重置"/></div>
			<div class="but"><a href="${pageContext.request.contextPath}/super/home.jsp">返回</a></div></div>
			</td>
		</tr>
    </table>
    
</form>
</body>

<script type="text/javascript">
    //添加商品
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/super/addsaler.jsp";
	}
	//删除商品
	function p_del() {   
		var msg = "确定要删除该销售员吗？";
		if (confirm(msg)==true){   
		return true;   
		}else{   
		return false;   
		}   
	}   
	</script>
</html>