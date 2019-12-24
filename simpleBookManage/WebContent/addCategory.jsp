<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新建图书分类</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/add.css">
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$("#saveBtn").on("click", function() {
		var id=$("#categoryId").val();
		var name=$("#categoryName").val();
		if(id=="" || id==null){
			alert("分类Id不能为空！");
			return false;
		}
		if(name=="" || name==null){
			alert("分类名称不能为空！");
			return false;
		}
		
		var regId = /^ca[0-9]{4}$/;
		if (regId.test(id) == false) {
			alert("格式不对，分类Id必须以ca开头+4位数字！");
			return false;
		}
	});
</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href=""> 图书分类管理 </a>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1>Hello, ${sessionScope.existUser.name}</h1>
			<p>请小心地新增图书分类，要是建了一个错误的就不好了。。。</p>
		</div>
		<div class="page-header">
			<h3>
				<small>新建</small>
			</h3>
		</div>
		<form class="form-horizontal"
			action="${pageContext.request.contextPath}/addCategoryServlet"
			method="post">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">分类ID ：</label>
				<div class="col-sm-8">
					<input name="categoryId" id="categoryId" class="form-control"
						id="categoryId">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">分类名称 ：</label>
				<div class="col-sm-8">
					<input name="categoryName" id="categoryName" class="form-control"
						id="categoryName">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<span style="color:red;">${msg }</span>
					<button type="submit" id="saveBtn" class="btn btn-primary">保存</button>
					&nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
	</div>
	<footer class="text-center"> copy@imooc </footer>
</body>
</html>
