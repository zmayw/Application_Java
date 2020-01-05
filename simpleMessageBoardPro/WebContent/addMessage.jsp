<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>新建留言</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        慕课网留言板
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${existUser.name}!</h1>
                <p>既然来了，就说点什么吧</p>
            </div>
            <div class="page-header">
                <h3><small>新建留言</small></h3>
            </div>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/addMessageServlet" method="post">
                <div class="form-group">
                    <label for="inputTitle" class="col-sm-2 control-label">标题 ：</label>
                    <div class="col-sm-8">
                        <input name="title" class="form-control" id="inputTitle" placeholder="title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputCategory" class="col-sm-2 control-label">分类 ：</label>
                    <div class="col-sm-8">
                        <select name="category" id="category" class="form-control" >
                        	<option selected="selected">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputContent" class="col-sm-2 control-label">内容 ：</label>
                    <div class="col-sm-8">
                        <textarea name="content"  class="form-control" rows="3" id="inputContent" placeholder="Content"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">发布留言</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="">查看留言</a>
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
         <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript">
        	$(function(){
        		$.ajax({
        			"url":"<%=basePath%>getCategoryList",
        			"dataType":"json",
        			"type":"get",
        			"success":function(json){
        				console.log(json);
        				for(var i=0;i<json.length;i++){
        					$("#category").append("<option value="+json[i].cid+" >"+json[i].name+"</option>");
        				}
        			}
        		});
        	});
        </script>
    </body>
</html>
