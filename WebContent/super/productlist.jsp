<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/saler/CSS/productslistCss.css" />
<title>商品列表</title>
</head>
<body>
<form name="Form1" action="${pageContext.request.contextPath}/findProductByManyCondition?type=super&user=${sessionScope.user.id}" method="post">
    <div id="head">
    <table>
        <tr>
        <td>商品编号
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
		<td>商品名称
		<input type="text" name="name" value=""/></td>
        </tr>
    </table>
    </div>
    
    
    <table>
		<tr><td>
		<div id="wholeform">
		<div id="colum">
		<table>
		    <tr>
			<td align="center" width="30%">商品编号</td>
			<td align="center" width="30%">商品名称</td>
			<td align="center" width="20%">商品价格</td>
			<td width="10%" align="center">商品类别</td>
			<td width="10%" align="center">商品状态</td>
			</tr>
		</table>
		</div>
		
		<div id="form">
		<table>
			<c:forEach items="${ps}" var="p">
                <tr>
				<td width="30%">${p.gNo }</td>
				<td width="30%">${p.gName }</td>
				<td width="20%">${p.gPrice }</td>
				<td width="10%">${p.gType}</td>
				<td width="10%">
				<c:if test="${p.gState == 1}"><a>正在销售</a></c:if>
				<c:if test="${p.gState == 2}"><a>已下架</a></c:if>
				</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		
		
		
		</div>
		</td></tr>
		
		
    </table>
    
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
</html>