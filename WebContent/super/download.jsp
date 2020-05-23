<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/saler/CSS/downloadCss.css" />
<title>销售报表</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/download?user=${sessionScope.user.id}" method="post">
<div id="bg">
    <table>
        <tr>
        <td>请输入年份<input class="topbut" type="text" name="year" size="15" value="" /></td>
        </tr>
        <tr>
		<td>请选择月份
			<select name="month">
			<option value="0">--选择月份--</option>
			<option value="1">一月</option>
			<option value="2">二月</option>
			<option value="3">三月</option>
			<option value="4">四月</option>
			<option value="5">五月</option>
			<option value="6">六月</option>
			<option value="7">七月</option>
			<option value="8">八月</option>
			<option value="9">九月</option>
			<option value="10">十月</option>
			<option value="11">十一月</option>
			<option value="12">十二月</option>
			</select>
		</td>
        </tr>
        <tr>
        <td>请选择类别
			<select name="category">
			<option value ="全部类别" selected="selected">--选择类别--</option>
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
        <tr>
        <td id="buttd">
        <div id="button">
        <input type="submit" name="search" value="下载" /><br>
		<input type="reset" name="reset" value="重置" /><br>
		<input onclick="window.location.href='${pageContext.request.contextPath}/super/home.jsp'" value="返回" />
		</div>
		</td>
        </tr>
    </table>
</div>
</form>
</body>
</html>