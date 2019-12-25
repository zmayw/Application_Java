<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
%>	
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>修改图书信息</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/dept/list.do">
                        图书信息管理
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${sessionScope.existUser.name}</h1>
                <p>请小心的修改图书信息。。。</p>
            </div>
            <div class="page-header">
                <h3><small>修改</small></h3>
            </div>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/updateBookServlet" method="post" enctype="multipart/form-data" >

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书编号 ：</label>
                    <div class="col-sm-8">
                        <input name="bookId" class="form-control" id="bookId" readonly="readonly" value="${requestScope.book.id}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书名称 ：</label>
                    <div class="col-sm-8">
                        <input name="bookName" class="form-control" id="bookName" value="${requestScope.book.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="categoryId" class="col-sm-2 control-label">分类 ：</label>
                    <select id="categoryId" name="categoryId" class="col-sm-2 form-control" style="width: auto;margin-left: 15px">
                       <!--  <option value="" selected="${requestScope.book.categoryId}">${requestScope.book.getCategoryName()}</option>-->     
                    </select>
                </div>

                 <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">价格 ：</label>
                    <div class="col-sm-8">
                        <input name="bookPrice" class="form-control" id="bookPrice" value="${requestScope.book.price}">
                    </div>
                  </div>
                   
                  <div class="form-group" >
                    <label for="name" class="col-sm-2 control-label">图书封面 ：</label>
                    <input type="file" id="bookPic" name="bookPic" style="padding-left: 15px">
                    <img id="coverImg" sytle="margin-top:5px" src="${requestScope.book.coverImg}">
                  </div>

                  <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">备注 ：</label>
                    <div class="col-sm-8">
                        <input name="remarks" class="form-control" id="remarks" value="${requestScope.book.description}">
                    </div>
                  </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" id="modifyBtn" class="btn btn-primary">修改</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript">
		/*
        $(function(){
			$.ajax({
				"url":"<%=path%>/modifyBook",
				"dataType":"json",
				"type":"get",
				"success":function(json){
					$("#bookId").val(json.id);
					$("#bookName").val(json.name);
					$("#categoryId").val(json.categoryId);
					$("#bookPrice").val(json.bookPrice);
					$("#remarks").val(json.description);
				}
			});
		});
		*/
    	$(function(){
    		$.ajax({
    			"url":"<%=path%>/getCategoryList",
    			"dataType":"json",
    			"type":"get",
    			"success":function(json){
    				for(var i=0;i<json.length;i++){
						var category=json[i];
						if(category.id==$("#categoryId").val()){
							continue;
						}
						$("#categoryId").append("<option value="+category.id+">"+category.name+"</option>");
					};
					$("#categoryId").val("${requestScope.book.categoryId}");
    			}
    			
    		});
    	});
    	
    	$("#modifyBtn").on("click",function(){
			var arrmsg = new Array(
					new Array("bookName","图书名不能为空!"), 
					new Array("categoryId","图书分类不能为空!"), 
					new Array("bookPrice","图书价格不能为空！"),
					new Array("bookPic", "图书封面不能为空!"));
			for (var i = 0; i < arrmsg.length; i++) {
				if ($("#" + arrmsg[i][0]).val() == null
						|| $("#" + arrmsg[i][0]).val() == "") {
					alert(arrmsg[i][1]);
					$("#" + arrmsg[i][0]).focus();
					return false;
				}
			}
			
    		var regPrice=/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    		if(regPrice.test($("#bookPrice").val())==false){
    			alert("价格格式不正确！就为保留1-2位小数位的小数。")
    			return false;
    		}
    	});
    	
    	console.log($("before change:"+"#bookPic").val());
    	$("#bookPic").on("change",function(){
    		$.ajax({
    			"url":"<%=path%>/updateImg",
    			"data":{"imgPath":$("#bookPic").val()},
    			"dateType":"json",
    			"async":false,
    			"type":"post",
    			"success":function(){
    				conlose.log("after change:"+json.path);
    				$("#coverImg").src=json.path;
    			},
    		})
    	})
        </script>
    </body>
</html>
