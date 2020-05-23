<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/saler/CSS/addCss.css" />
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/addProduct?user=${sessionScope.user.id}" method="post" enctype="multipart/form-data">
    <!-- enctype="multipart/form-data"实现文件上传 -->
    <div id="bg">
    <div id="details">
    <table>
		<tr><td>商品名称</td><td><input type="text" name="gName" /></td></tr>
		<tr><td>商品价格</td><td><input type="text" name="gPrice" /></td></tr>
	<tr>
		<td>商品类别</td><td>${sessionScope.user.getgType()}
		<input type="hidden" name="gType" value="${sessionScope.user.getgType()}" />
		</td>
	</tr>
	<tr>
		<td>商品图片</td>
		<td colspan="3"><div class="button"><input type="file" name="upload" size="30" value="" /></div></td>
	</tr>
	<tr>
		<td>商品评分</td>
		<td><input type="text" name="gScore" /></td>
	</tr>
	<tr>
	    <td>商品描述</td>
	    <td><textarea name="gDescription" cols="30" rows="3" style="WIDTH: 96%"></textarea></td>
	</tr>
	<tr>
		<td>
		</td>
	</tr>
    </table>
    
    <div class="button" id="bottom">
		<input type="submit" value="确定">	
		<input type="reset" value="重置">
		<input onclick="window.location.href='${pageContext.request.contextPath}/listProduct?gType=${sessionScope.user.getgType()}'" value="返回" />
	</div>				
    
    </div>
    </div>
</form>
</body>
</html>