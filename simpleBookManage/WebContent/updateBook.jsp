<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
                  	<div style="display:inline-block;">
                  		<div style="display:inline-block;margin-left:15px;">
                    		<input type="button" id="switchImgBtn" value="重新选择"
                    	style="position:relative;float:left;display:inline-block;">
                    		<input type="file" id="bookPic" name="bookPic"
                    	style="position:absolute;opacity:0;" onchange="setImagePreview(this,coverImg)" >
                     	</div>
                  	 	<img id="coverImg" style="margin-top:5px;width:160px;height:200px;margin-left:15px;padding:1px 1px 1px 1px;display:block;position:prative; " src="${requestScope.book.coverImg}">
                  	 </div> 
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
                        <input type="text" name="hiddenText" id="hiddenText" style="display:hidden;border:none;" >
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
						if(json[i].id==$("#categoryId").val()){
							continue;
						}
						$("#categoryId").append("<option value="+json[i].id+">"+json[i].name+"</option>");
					}
				}							
			});
		});
    	
    	$("#modifyBtn").on("click",function(){
			var arrmsg = new Array(
					new Array("bookName","图书名不能为空!"), 
					new Array("categoryId","图书分类不能为空!"), 
					new Array("bookPrice","图书价格不能为空！"));
			if ($("#bookPic").val()=="" ||$("#bookPic").val()==null){
				if($("#coverImg").src==""){
					alert("图书图片不能为空！");
					return false;
				}else{
					$("#hiddenText").val($("#coverImg")[0].src.replace("<%=serverPath%>",''));
				}
			}
			
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
    	
    	function setImagePreview(docObj,imgObjPreview){
    		var name=docObj.value;
			var regImg=/.jpg|.png|.jpeg|.fit$/;
			if(regImg.test(name)==false){
				alert("文件类型错误，必须为图片！");
				docObj.value=null;
				return;
			}
			if(docObj.files && docObj.files[0]){
				document.getElementById("coverImg").src=window.URL.createObjectURL(docObj.files[0]);
			}else{
				//IE，使用滤镜
				docObj.select();
				var imgSrc=document.selection.createRange().text;
				//图片异常的捕捉，防止用户修改后缀来伪造图片 ,实测没起作用，todo
				try{
					document.getElementById(imgObjPreview).style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					document.getElementById(imgObjPreview).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src=imgSrc;
				}catch(e){
					alert("您上传的图片格式不正确，请重新选择！");
					return false;
				}
				document.selection.empty();	
			}
    		
			return true;
    	}
        </script>
    </body>
</html>
