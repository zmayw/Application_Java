<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>

<section class="page">
	<div class="container" id="pagefy">
		<ul>
			<c:if test="${pageBean.pageNum != 1}">
				<li><a href="<%=path%>/messageServlet?pageNum=1">首页</a></li>
				<li><a
					href="<%=path %>/messageServlet?pageNum=${pageBean.pageNum-1}">上一页</a></li>
			</c:if>
			<c:forEach var="i" begin="1"
				end="${requestScope.pageBean.pagesCount}">
				<c:if test="${pageBean.pageNum==i}">
					<li><span class="first-page">${i}</span></li>
				</c:if>
				<c:if test="${pageBean.pageNum != i}">
					<li><a href="<%=path %>/messageServlet?pageNum=${i}">${i}</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${pageBean.pageNum != pageBean.pagesCount}">
				<li><a
					href="<%=path %>/messageServlet?pageNum=${pageBean.pageNum+1}">下一页</a></li>
				<li><a
					href="<%=path %>/messageServlet?pageNum=${pageBean.pagesCount}">末页</a></li>
			</c:if>
			<li>共${pageBean.pagesCount}页</li>
		</ul>
		<!-- 分页内容参考视频中老师源码 -->
	</div>
</section>
