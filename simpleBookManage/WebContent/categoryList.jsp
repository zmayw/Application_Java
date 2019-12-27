<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
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
			<table class="table table-striped" id="formTable">
				<thead>
					<tr>
						<th>序号</th>
						<th>分类编号</th>
						<th>分类名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<!--<c:forEach items="${requestScope.categoryList}" var="ca" varStatus="idx">
						<tr>
							<td>${idx.index+1}</td>
							<td>${ca.id}</td>
							<td>${ca.name}</td>
							<td><input id="deleteBtn" type="button" value="删除"></td>						
						</tr>
					</c:forEach>-->
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
	
	/*	$("#deleteBtn").on("click",function(){
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
		});*/
		function delConfirm(){
			var message=confirm("确认是否删除？");
			if(message){
				return true;
			}else{
				return false;
			}
		};
		$(function(){
			$.ajax({
				"url":"<%=path%>/getCategoryList",
				"dataType":"json",
				"type":"get",
				"success":function(json){
					if (json.length == 0) {
						$("#formTable").append(
								"<tr><td colspan='4' style='color:red;text-align:center;'>没有数据</td><tr>");
						return;
					}
					for(var i=0;i<json.length;i++){
						$("#formTable").append(
					"<tr><td>"+(i+1)+"</td><td>"+json[i].id+"</td><td>"+json[i].name+"</td><td><a href='<%=path%>/deleteCategory?categoryId="+json[i].id+"' onclick='return delConfirm()'>删除</a></td></tr>");
					}
				}
			});
		});
	</script>
</body>
</html>