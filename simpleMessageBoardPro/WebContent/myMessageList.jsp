<%@ page contentType="text/html;charset=utf-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>

<html>
<head>
<meta charset="UTF-8">
<title>我的留言</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript">
	
</script>
</head>

<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<section class="banner">
		<div class="container">
			<div>
				<h1>慕课网留言板——我的留言</h1>
				<p>慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。
				</p>
			</div>
		</div>
	</section>
	<section class="main">
		<div class="container">
			<c:forEach items="${requestScope.pageBean.list}" var="message"
				varStatus="idx">
				<div class="alt-item">
					<div class="alt-head">
						<div class="alt-info">
							<span>作者：</span><span><a href="">${message.getUser().getName()}</a></span>
							<span>时间：</span><span>${message.modifyTime}</span>
						</div>
					</div>
					<div class="alt-content">
						<h3>${message.title}</h3>
						<p>${message.content}</p>
					</div>
					<div align="right">
						<span><a href="<%=path %>/showMessageServlet?mid=${message.mid}">修改</a></span>
						<span><a href="<%=path %>/deleteMessageServlet?mid=${message.mid}">删除</a></span>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
	<section class="page">
		<c:if test="${pageBean.rowsCount==0}">
			<div class="container"><h3>还没有留言，快去添加吧~</h3></div>
		</c:if>
		<c:if test="${pageBean.rowsCount>0}">
		<div class="container" id="pagefy">
			<ul>
				<c:if test="${pageBean.pageNum != 1}">
					<li><a href="<%=path%>/myMessageServlet?pageNum=1">首页</a></li>
					<li><a
						href="<%=path %>/myMessageServlet?pageNum=${pageBean.pageNum-1}">上一页</a></li>
				</c:if>
				<c:forEach var="i" begin="1"
					end="${requestScope.pageBean.pagesCount}">
					<c:if test="${pageBean.pageNum==i}">
						<li><span class="first-page">${i}</span></li>
					</c:if>
					<c:if test="${pageBean.pageNum != i}">
						<li><a href="<%=path %>/myMessageServlet?pageNum=${i}">${i}</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${pageBean.pageNum != pageBean.pagesCount}">
					<li><a
						href="<%=path %>/myMessageServlet?pageNum=${pageBean.pageNum+1}">下一页</a></li>
					<li><a
						href="<%=path %>/myMmessageServlet?pageNum=${pageBean.pagesCount}">末页</a></li>
				</c:if>
				<li>共${pageBean.pagesCount}页</li>
			</ul>
		</div>
		</c:if>
	</section>
	<footer> copy@慕课网 </footer>
</body>
</html>