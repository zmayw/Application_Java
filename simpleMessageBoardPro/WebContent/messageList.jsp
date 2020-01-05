<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>留言板</title>
        <link rel="stylesheet" href="css/index.css">
    </head>

    <body>
        <header>
            <div id="menuDiv" class="container">
                <% if (null != request.getSession().getAttribute("existUser")) {%>
                    <nav>
                        <a href="<%=path %>/myMessageServlet">我的留言</a>
                    </nav>
                    <nav>
                        <a href="">我的信息</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="">登录</a>
                        <a href="">注册</a>
                    </nav>
                <% } %>
                
            </div>
            <div class="container">
            	<a href="<%=path%>/addMessageCategory.jsp">新建分类</a>
            </div>
            <div class="container">
            	<a href="<%=path%>/addMessage.jsp">新建留言</a>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>慕课网留言板</h1>
                    <p>慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。 </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <c:forEach items="${requestScope.pageBean.list}" var="message" varStatus="idx" >
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
                    </div>
               </c:forEach>
            </div>
        </section>
        <section class="page">
            <div class="container" id="pagefy">
               <ul>
               	<c:if test="${pageBean.pageNum != 1}">
               		<li><a href="<%=path %>/messageServlet?pageNum=1">首页</a></li>
               		<li><a href="<%=path %>/messageServlet?pageNum=${pageBean.pageNum-1}">上一页</a></li>
               	</c:if>
               	<c:forEach var="i" begin="1" end="${requestScope.pageBean.pagesCount}">
               		<c:if test="${pageBean.pageNum==i}">
               			<li><span class="first-page">${i}</span></li>
               		</c:if>
               		<c:if test="${pageBean.pageNum != i}">
               			<li><a href="<%=path %>/messageServlet?pageNum=${i}">${i}</a></li>
               		</c:if>
               	</c:forEach>
               	<c:if test="${pageBean.pageNum != pageBean.pagesCount}">              	
               		<li><a href="<%=path %>/messageServlet?pageNum=${pageBean.pageNum+1}">下一页</a></li>
               		<li><a href="<%=path %>/messageServlet?pageNum=${pageBean.pagesCount}">末页</a></li>
               	</c:if>
               	<li>共${pageBean.pagesCount}页</li>
               </ul>
           <!-- 分页内容参考视频中老师源码 -->
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
</html>