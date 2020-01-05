<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>新建留言分类</title>
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
                <p>分类的创建要慎重哦</p>
            </div>
            <div class="page-header">
                <h3><small>新建分类</small></h3>
            </div>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/addCategoryServlet" method="post">
                <div class="form-group">
                    <label for="inputCname" class="col-sm-2 control-label">分类名称：</label>
                    <div class="col-sm-8">
                        <input name="cname" class="form-control" id="inputCname" placeholder="名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputDesc" class="col-sm-2 control-label">分类描述 ：</label>
                    <div class="col-sm-8">
                        <textarea name="cdesc"  class="form-control" rows="3" id="inputCdesc" placeholder="描述"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">保存分类</button>&nbsp;&nbsp;&nbsp;
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
    </body>
</html>
