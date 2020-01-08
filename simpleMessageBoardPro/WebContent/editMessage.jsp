<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>编辑留言</title>
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
                <h3><small>修改留言</small></h3>
            </div>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/editMessageServlet" method="post">
                <input type="hidden" name="mid" value="${requestScope.message.mid}">
                <div class="form-group">
                    <label for="inputTitle" class="col-sm-2 control-label">标题 ：</label>
                    <div class="col-sm-8">
                        <input name="title" class="form-control" id="inputTitle" value="${requestScope.message.title}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputContent" class="col-sm-2 control-label">内容 ：</label>
                    <div class="col-sm-8">
                        <textarea name="content" class="form-control" rows="3" id="inputContent">${requestScope.message.content}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="">返回</a>
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
    </body>
</html>
