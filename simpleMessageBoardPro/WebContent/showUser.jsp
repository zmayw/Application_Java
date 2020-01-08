<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>我的信息</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/messageServlet">
                        返回留言板
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${existUser.name}!</h1>
                <p>信息都在这里了 ^_^</p>
            </div>
            <div class="page-header">
                <h3><small>个人信息</small></h3>
            </div>
            <div class="">
                <div class="">
                	<a href="${pageContext.request.contextPath}/editUser.jsp" style="font-size:14px;font-color:#777;text-decoration:underline;">编辑个人信息</a>
                </div>
             </div>
             <form class="form-horizontal" action="" method="post">
                <div class="form-group">
                    <label for="photo" class="col-sm-2 control-label">头像 ：</label>
                    <div class="col-sm-6">
                        <img name="photo" id="photo" src="${existUser.filePath}" style="width:100px;height:100px;">
                    </div>
                </div>   
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">用户 ：</label>
                    <div class="col-sm-6">
                        <input name="name" class="form-control" id="name" value="${existUser.name}" readonly>
                    </div>
                </div>             
                 <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">邮箱 ：</label>
                    <div class="col-sm-6">
                        <input name="email" class="form-control" id="email" value="${existUser.email}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sex" class="col-sm-2 control-label">性别 ：</label>
                    <div class="col-sm-6" id="sexRadioDiv" >
                    	<span style="width:30px;">男</span><input id="male" type="radio" name="sex" value="男"
                    	<c:if test="${existUser.sex.equals('男')}">checked="checked"</c:if> />
						<span style="width:30px;">女</span><input id="female" type="radio" name="sex" value="女" 
						<c:if test="${existUser.sex.equals('女')}">checked="checked"</c:if> />
						<span style="width:30px;">保密</span><input id="secret" type="radio" name="sex" value="保密" 
						<c:if test="${existUser.sex.equals('保密')}">checked="checked"</c:if> />
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>        
    </body>
</html>
