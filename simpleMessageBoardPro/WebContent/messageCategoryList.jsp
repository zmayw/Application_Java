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
        <link rel="stylesheet" href="css/list.css">
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
                    <h1>慕课网留言板</h1>
                    <p>慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。 </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
            	<div>
            	<table class="datalistTable">
            	<thead><tr>
            		<th>编号</th>
            		<th>分类名称</th>
            		<th>分类描述</th>
            	</tr>
            	</thead>
            	<tbody>
                <c:forEach items="${requestScope.mcList}" var="category" >
                   <tr>
                   	<td>${category.cid}</td>
                   	<td>${category.name}</td>
                   	<td>${category.desc}</td>
                   </tr>
               </c:forEach>
               </tbody>
               </table>
               </div>
            </div>
        </section>
        <section class="page">
            <div class="container">
               

           <!-- 分页内容参考视频中老师源码 -->
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>

    </body>
</html>