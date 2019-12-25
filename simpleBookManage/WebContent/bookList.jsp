<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
/*	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache,must-revalidate" />
<meta http-equiv="expires" content="Thu,01 Jan 1970 00:00:01 GMT" />
<meta http-equiv="expires" content="0" />
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
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/searchBookServlet"
				method="post">
				<div class="form-group" style="float: right;">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary" id="searchBtn">查询</button>
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

			<table class="table table-striped">
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
				
				<!--	<c:forEach items="${requestScope.bookList}" var="book"
						varStatus="idx">
						<tr id="tr1">
							<td>${idx.index+1}</td>
							<td>${book.id}</td>
							<td>${book.name}</td>
							<td>${book.getCategoryName()}</td>
							<td>${book.price}</td>
							<td><img src="${book.coverImg}"></td>
							<td>
							<a href="${pageContext.request.contextPath}/modifyBook?bookId=${book.id}">修改</a> 
							<a href="/deleteBook?bookId=${book.id}">删除</a></td>
						</tr>
					</c:forEach> -->
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
		
		
		$(function() {
			$.ajax({
						"url" : "<%=path%>/getBookList",
						"dataType" : "json",
						"type" : "get",
						"async": false,
						"success" : function(json) {
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
												"<tr id='tr'"+i/2+1+"><td>"+i+1+"</td><td>"+book.id+"</td><td>"+
												book.name+"</td><td>"+book.categoryName+"</td><td>"+book.price+
												"</td><td><img src="+book.coverImg+"></td><td><a href='<%=path%>/modifyBook?bookId="+
														book.id+"'>修改</a> <a href='<%=path%>/deleteBookServlet?bookId="+book.id+"'>删除</a></td></tr>");
						
							}
						}
					});
		});
	</script>
</body>
</html>