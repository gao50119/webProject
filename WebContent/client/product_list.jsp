<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/CSS/productlistCss.css" />
<script src="https://cdn.bootcdn.net/ajax/libs/FileSaver.js/1.3.8/FileSaver.js"></script>
<title>商品列表</title>
</head>
<body>
<div id="whole">
<input id="time" type="hidden" />
<input id="type" value="${bean.category}" type="hidden" />
<table id="wholetable">
<tr>
<td>
	<div id="head">
	<table><tr>
	<td><div class="headcard"><a href="${pageContext.request.contextPath }/toMenuSearchServlet">购物首页</a></div></td>
	<td><div class="headcard">${bean.category}</div></td>
	<td><div class="headcard">商品列表</div></td>
	</tr></table>
	</div>
	
	<table>
	<tr>
	<td><div id="lefthead">
		<h1>商品目录</h1>
		<hr />
		<h1>${bean.category}</h1>&nbsp;&nbsp;&nbsp;&nbsp;<font>共${bean.totalCount}种商品</font>
		<hr /></div>

		<table id="alllist">
		<tr>
		<c:forEach items="${bean.ps}" var="p" varStatus="vs">
		<td id="picandword">
		<div id="pic">
		<p><a href="${pageContext.request.contextPath}/findProductById?gNo=${p.gNo}">
		<img src="${pageContext.request.contextPath}${p.gImgurl}" /> 
		</a></p>
		</div>
		<div id="card">
		<a href="${pageContext.request.contextPath}/findProductById?gNo=${p.gNo}"> ${p.gName}<br />售价：￥${p.gPrice} </a>
		</div>
		</td>
		</c:forEach>
		</tr>
		</table>
		
		<div id="page">				
		<ul>
		<c:if test="${bean.currentPage!=1}">
		<li>
		<a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage-1}&gType=${bean.category}">&lt;&lt;上一页</a>
		</li>
		</c:if>
		
		<c:if test="${bean.currentPage==1}">
		<li>&lt;&lt;上一页</li>
		</c:if>
		
		<c:forEach begin="1" end="${bean.totalPage}" var="pageNum">
			<c:if test="${pageNum==bean.currentPage}">
			<li>${pageNum }</li>
			</c:if>
			<c:if test="${pageNum!=bean.currentPage}">
			<li><a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pageNum}&gType=${bean.category}">${pageNum}</a>
			</li>
			</c:if>
		</c:forEach>

		<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
		<li>下一页 &gt;&gt;</li>
		</c:if>
		<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
		<li>
		<a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage+1}&gType=${bean.category}">下一页&gt;&gt;</a>
		</li>
		</c:if>
		</ul>
		</div>
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
	
	var a=document.getElementById("time");
	//document.textarea.value = hour+"时"+minute+"分"+second+"秒";
	a.value=hour+"时"+minute+"分"+second+"秒";
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