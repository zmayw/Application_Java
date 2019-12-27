<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
				<p>图书信息管理</p>
			</div>
		</div>
	</section>
	<section class="main">
		<div class="container">
			<form class="form-horizontal">
				<div class="form-group" style="float: right;">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-primary" id="searchBtn">查询</button>
						&nbsp;&nbsp;&nbsp;
					</div>
				</div>
				<div class="form-group" style="float: right; width: 95.5%;">
					<div id="searchDiv">
						<span style="margin: 0 10px 0 5px;">图书Id:</span> <input
							name="searchId" id="searchId" class="form-control"
							placeholder="输入要查询的图书Id" style="width: 200px; display: inline;">
						<span>图书名称:</span> <input name="searchName" id="searchName"
							class="form-control" placeholder="输入要查询的图书名称"
							style="width: 200px; display: inline;"> <span>图书分类:</span>
						<select id="searchCategoryId" name="searchCategoryId"
							style="width: 200px; display: inline;" class="form-control">
							<option value="" selected="selected">请选择</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<div class="container">

			<table class="table table-striped" id="bookListTbl">
				<thead>
					<tr>
						<th>序号</th>
						<th>图书编号</th>
						<th>图书名称</th>
						<th>分类</th>
						<th>价格</th>
						<th>图书封面</th>
						<th>操作</th>

					</tr>
				</thead>
				<tbody id="contentBody">		
				</tbody>
			</table>
		</div>
	</section>
	<section class="page">
		<div class="container">
			<div id="fatie">
				<a href="addBook.jsp"><button>新建</button></a>
			</div>
		</div>
	</section>
	<footer> copy@慕课网 </footer>
	<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		
		$(function() {
			$.ajax({
				"url" : "<%=path%>/getCategoryList",
				"dataType" : "json",
				"type" : "get",
				"success" : function(json) {
					for (var i = 0; i < json.length; i++) {
						var category = json[i];
						$("#searchCategoryId").append(
								"<option value="+category.id+">"+ category.name + "</option>");
					}
				}
			});
		});
		

		$(function(){
			$.ajax({
						"url" : "<%=basePath%>getBookList",
						"dataType" : "json",
						"type" : "get",
						"success" : function(json) {
							addTableContent(json);
						}	
					});
		});
		
		function addTableContent(json){
			$("#contentBody>tr").remove();
			if (json.length == 0) {
				$("#contentBody").append(
						"<tr><td colspan='7' style='color:red;text-align:center;'>没有数据</td><tr>");
				return;
			}
			for (var i = 0; i < json.length; i++) {
				var book = json[i];
				console.log(book);
				$("#contentBody").append(
								"<tr id='tr"+((i+1)%2)+"'><td>"+(i+1)+"</td><td>"+book.id+"</td><td>"+
								book.name+"</td><td>"+book.categoryName+"</td><td>"+book.price+
								"</td><td><img src="+book.coverImg+"></td><td><a href='<%=path%>/modifyBook?bookId="+
										book.id+"'>修改</a> <a href='<%=path%>/deleteBook?bookId="+book.id+"' onclick='return delConfirm()')>删除</a></td></tr>");
		
			}	
		}
		
		function delConfirm(){
			var message=confirm("确认是否删除？");
			if(message){
				return true;
			}else{
				return false;
			}
		};
		
		$("#searchBtn").on("click",function(){
			var id=$("#searchId").val().trim();
			var name=$("#searchName").val().trim();
			var categoryId=$("#searchCategoryId").val();
			$.ajax({
				"url" :"<%=basePath%>searchBook",
				"data": {"searchId":id,"searchName":name,"searchCategoryId":categoryId},
				"dataType" : "json",
				"type":"POST",
				"success" : function(json) {
					addTableContent(json);
				}	
			})
		});
	</script>
</body>
</html>