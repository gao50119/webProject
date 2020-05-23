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
<form name="Form1" action="${pageContext.request.contextPath}/findProductByManyCondition?category=${sessionScope.user.getgType()}&user=${sessionScope.user.id}" method="post">
    <div id="head">
    <table>
        <tr>
        <td>商品编号
		<input type="text" name="id" value="" /></td>
		<td>类别 ${sessionScope.user.getgType()}
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
			<td align="center" width="20%">商品名称</td>
			<td align="center" width="20%">商品价格</td>
			<td width="10%" align="center">商品类别</td>
			<td width="10%" align="center">编辑</td>
			<td width="10%" align="center">下架</td>
			</tr>
		</table>
		</div>
		
		<div id="form">
		<table>
			<c:forEach items="${ps}" var="p">
                <tr>
				<td width="30%">${p.gNo }</td>
				<td width="20%">${p.gName }</td>
				<td width="20%">${p.gPrice }</td>
				<td width="10%">${p.gType}</td>
				<td width="10%">
				<a href="${pageContext.request.contextPath}/findProductById?gNo=${p.gNo}&type=admin">编辑</a>
				</td>
				<td width="10%">
				<c:if test="${p.gState == 1}">
				<a href="${pageContext.request.contextPath}/deleteProduct?gNo=${p.gNo}&gType=${sessionScope.user.getgType()}&user=${sessionScope.user.id}" onclick="javascript:return p_del()">
				下架
				</a></c:if>
				<c:if test="${p.gState == 2}"><a>已下架</a>
				</c:if>
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
			<div class="but"><a href="${pageContext.request.contextPath}/saler/login/home.jsp">返回</a></div></div>
			</td>
		</tr>
    </table>
    
</form>
</body>

<script type="text/javascript">
    //添加商品
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/saler/products/add.jsp";
	}
	//删除商品
	function p_del() {   
		var msg = "您确定要下架该商品吗？";   
		if (confirm(msg)==true){   
		return true;   
		}else{   
		return false;   
		}   
	}   
	</script>
</html>