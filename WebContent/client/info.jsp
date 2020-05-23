<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/infoCss.css" />
<title>游戏详情</title>
</head>
<body>
<div id="divpagecontent">
<input id="type" value="${p.gType}" type="hidden" />
	<table width="100%" border="0" cellspacing="0">
	<tr>
	<td>
	    <table>
	    <tr>
	    <td>
	    <div id="link"><div id="inlink">
		<div class="card"><a href="${pageContext.request.contextPath }/toMenuSearchServlet">购物首页</a></div>
		<div class="card"><a href="${pageContext.request.contextPath}/showProductByPage?gType=${p.gType}">&nbsp;${p.gType}</a></div>
		<div class="card"><font>${p.gName}</font></div>
		</div></div>
		</td>
		</tr>
		</table>
		
		<table cellspacing="0">
		<tr>
		<td>
			<table id="content">
			<tr>
			<td width="30%">
				<div id="imgcard">
				<p>
				<img src="${pageContext.request.contextPath}${p.gImgurl}" />
				</p>
				</div>
				
				<% if(session.getAttribute("user") != null){%>
				<c:if test="${p.gState==1 }">
				<div>
				<div class="card"><a href="${pageContext.request.contextPath}/addCart?gNo=${p.gNo}">加入购物车</a>
				<font>${tips}</font></div>
				</div>
				</c:if>
				<%}%>
				
				<c:if test="${p.gState==2 }">
				<div class="card"><font id="p">已下架</font></div>
				</c:if>
			</td>
				
			<td>
			<div id="de">
			<div class="details" id="name">${p.gName}</div>
			<div class="details">售价：<font>￥${p.gPrice}</font></div>
			<div class="details">类别：${p.gType }</div>
			</div>
			<div class="details" id="de">
			<b>内容简介：</b>
			<p>${p.gDescription}</p></div>
		    </td>
		    </tr>
			</table>
		 </td>
	     </tr>
	     </table>
	</td>
    </tr>
	</table>
</div>
</body>
<script type="text/javascript">
var second=0;
var minute=0;
var hour=0;
window.setTimeout("interval();",1000);
function interval()
{
	second++;
	/*if(second==60)
	{
	second=0;minute+=1;
	}
	if(minute==60)
	{
	minute=0;hour+=1;
	}*/
	
	//var a=document.getElementById("time");
	//document.textarea.value = hour+"时"+minute+"分"+second+"秒";
	//a.value=hour+"时"+minute+"分"+second+"秒";
	window.setTimeout("interval();",1000);
}


function setCookie(name,value,days){  
	var expires=new Date(); 
	expires.setTime(expires.getTime()+days*24*60*60*1000);
	//var value_utf8 = URLEncoder.encode(value,"UTF-8"); 
	//var value_utf8 = encodeURI(encodeURI(value)); escape(escape(value))
	document.cookie=name+"="+escape(value)+";expires="+expires.toGMTString();
}


window.onbeforeunload = function() {
	var type = document.getElementById("type").value;
	setCookie("type",type,1);
	setCookie("time",second,1);
};

</script>
</html>