<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>	
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>新建图书信息</title>
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
                <p>请小心地新增图书信息，要是建了一个错误的就不好了。。。</p>
            </div>
            <div class="page-header">
                <h3><small>新建</small></h3>
            </div>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/addBookServlet" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书编号 ：</label>
                    <div class="col-sm-8">
                        <input name="bookId" id="bookId" class="form-control" id="bookId">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书名称 ：</label>
                    <div class="col-sm-8">
                        <input name="bookName" id="bookName" class="form-control" id="bookName">
                    </div>
                </div>
                <div class="form-group">
                    <label for="categoryId" class="col-sm-2 control-label">分类 ：</label>
                    <select id="categoryId" name="categoryId" class="col-sm-2 form-control" style="width: auto;margin-left: 15px">
                       <option value="" selected="selected">请选择</option>
                    </select>
                </div>

                 <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">价格 ：</label>
                    <div class="col-sm-8">
                        <input name="bookPrice" class="form-control" id="bookPrice">
                    </div>
                  </div>
                   
                  <div class="form-group" >
                    <label for="name" class="col-sm-2 control-label">图书封面 ：</label>
                    <div style="display:inline-block;">
                    	<input type="file" id="bookPic" name="bookPic" style="padding-left: 15px" onchange="setImagePreview(this,coverImg)">
                    	<img id="coverImg" style="margin-top:5px;width:160px;height:200px;margin-left:15px;padding:1px 1px 1px 1px;display:block;position:prative;border:1px;" 
                    	src="">
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">备注 ：</label>
                    <div class="col-sm-8">
                        <input name="remarks" class="form-control" id="remarks">
                    </div>
                  </div>

                <div class="form-group">
                	
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" class="btn btn-primary" id="saveBtn" value="保存">&nbsp;&nbsp;&nbsp;
                        <span id="msgText" style="color:red;">${requestScope.msg}</span>
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript">
        	$("#saveBtn").on("click",function(){
    			var arrmsg = new Array(
    					new Array("bookId","图书ID不能为空!"),
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
        		
        		
        		var regId=/^bk[0-9]{4}$/;
        		if(regId.test($("#bookId").val())==false){
        			alert("图书ID不正确，必须以bk开头+4位数字！");
        			return false;
        		}
        		
        		var regPrice=/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
        		if(regPrice.test($("#bookPrice").val())==false){
        			alert("价格格式不正确！就为保留1-2位小数位的小数。")
        			return false;
        		}
        		
        		var regImg=/.jpg|.png|.jpeg|.fit$/;
        		if(regImg.test($("#bookPic").val())==false){
        			alert("上传文件格式不对，必须是图片！");
        			return false;
        		}
        	});
        	
        	$(function(){
        		$.ajax({
        			"url":"<%=path%>/getCategoryList",
        			"dataType":"json",
        			"type":"get",
        			"success":function(json){
        				if(json.length==0){
        					alert("请先去添加图书分类！");
        					return false;
        				}
        				for(var i=0;i<json.length;i++){
    						var category=json[i];
    						$("#categoryId").append("<option value="+category.id+">"+category.name+"</option>");
    					}
        			},
        			
        		});
        	});
        	
        	function setImagePreview(docObj,imgObjPreview){
        		var name=docObj.value;
    			var regImg=/.jpg|.png|.jpeg|.fit$/;
    			if(regImg.test(name)==false){
    				alert("文件类型错误，必须为图片！");
    				//document.getElementById("txtSrc").value=null;
    				docObj.value=null;
    				return;
    			}
    			if(docObj.files && docObj.files[0]){
    				document.getElementById("coverImg").src=window.URL.createObjectURL(docObj.files[0]);
    			}else{
    				docObj.select();
    				var imgSrc=document.selection.createRange().text;
    				document.selection.empty();	
    			}
        		
    			return true;
        	}	
        </script>
    </body>
</html>
