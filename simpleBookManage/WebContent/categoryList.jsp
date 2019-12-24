<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书后台管理</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
	<header>
		<div class="container">
			<nav>
				<a href="bookList.jsp">图书信息管理</a>
			</nav>
			<nav>
				<a href="categoryList.jsp">分类管理</a>
			</nav>

		</div>
	</header>
	<section class="banner">
		<div class="container">
			<div>
				<h1>图书管理系统</h1>
				<p>图书分类管理</p>
			</div>
		</div>
	</section>
	<section class="main">
		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>序号</th>
						<th>分类编号</th>
						<th>分类名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.categoryList}" var="ca" varStatus="idx">
						<tr>
							<td>${idx.index+1}</td>
							<td>${ca.id}</td>
							<td>${ca.name}</td>
							<td><input id="deleteBtn" type="button" value="删除"></td>
							<!-- <a href="${pageContext.request.contextPath}/deleteCategory?categoryId=${ca.id}"> -->
							<!--在循环显示数据时，此处的ca0001可以用EL表达式进行替换-->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<section class="page">
		<div class="container">
			<div id="fatie">
				<a href="addCategory.jsp"><button>新建</button></a>
			</div>
		</div>
	</section>
	<footer class="text-center"> copy@imooc </footer>
	<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		$("#deleteBtn").on("click",function(){
			var message=confirm("确认是否删除？");
			if(message==true){
				$.ajax({
						"url":"${pageContext.request.contextPath}/deleteCategory",
						"type":"post",
						"dataType":"json",
						"data":{"id":$("#categoryId").val()},
						"success":function(json){}
				});
			}else{
				
			}
		});
	</script>
</body>
</html>