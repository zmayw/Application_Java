<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
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
                <h1>Hello, ${sessionScope.existUser.name}!</h1>
                <p>信息都在这里了 ^_^</p>
            </div>
            <div class="page-header">
                <h3><small>个人信息</small></h3>
            </div>
             <form class="form-horizontal" action="${pageContext.request.contextPath}/userServlet" method="post" enctype="multipart/form-data" >
             	<input name="id" value="${sessionScope.existUser.id}" style="display:none;">
             	<input id="hiddenText" name="hiddenText" value="" style="display:hidden;border:none;">
                <div class="form-group">
                    <label for="photo" class="col-sm-2 control-label">头像 ：</label>
                    <div class="col-sm-6">       	
                    	<img id="showPic" src="${sessionScope.existUser.filePath}" style="width:100px;height:100px;"></img>
                        <div style="display:inline-block;margin-left:15px;">
                    		<input type="button" id="switchImgBtn" value="重新选择"
                    	style="position:relative;float:left;display:inline-block;" />
                    		<input type="file" id="userPhoto" name="userPhoto" style="position:absolute;opacity:0;"
                    		 onchange="setImagePreview(this,'showPic')" />
                     	</div>
                    </div>
                </div>   
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">用户 ：</label>
                    <div class="col-sm-6">
                        <input name="name" class="form-control" id="name" value="${sessionScope.existUser.name}">
                    </div>
                </div>             
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码 ：</label>
                    <div class="col-sm-6">
                        <input type="password" name="password" class="form-control" id="password" value="${sessionScope.existUser.password}">
                    </div>
                </div>
                 <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">邮箱 ：</label>
                    <div class="col-sm-6">
                        <input name="email" class="form-control" id="email" value="${existUser.email}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="sex" class="col-sm-2 control-label">性别 ：</label>
                    <div class="col-sm-6" id="sexRadioDiv">
                    	<span style="width:30px;">男</span><input id="male" type="radio" name="sex" value="男"
                    	<c:if test="${existUser.sex.equals('男')}">checked="checked"</c:if> />
						<span style="width:30px;">女</span><input id="female" type="radio" name="sex" value="女" 
						<c:if test="${existUser.sex.equals('女')}">checked="checked"</c:if> />
						<span style="width:30px;">保密</span><input id="secret" type="radio" name="sex" value="保密" 
						<c:if test="${existUser.sex.equals('保密')}">checked="checked"</c:if> />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                    	<button type="submit" id="saveBtn" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
                        <button type="reset" class="btn btn-primary">取消</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript">    
        	function setImagePreview(docObj,imgObjPreview){
        		var name=docObj.value;
        		var regImg=/.jp|.png|.jpeg|.fit$/;
        		if(regImg.test(name)==false){
        			alert("文件类型错误，必须为图片！");
        			docObj.value=null;
        			return;
        		}
        		if(docObj.files && docObj.files[0]){
        			document.getElementById(imgObjPreview).src=window.URL.createObjectURL(docObj.files[0]);
        		}else{
        			docObj.select();
        			var imgSrc=document.selection.createRange().text;
        			try{
        				document.getElementById(imgObjPreview).style.filter="progid:DXImageTrandsform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
        				document.getElementById(imgObjPreview).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src=imgSrc;
        			}catch(e){
        				alert("您上传的图片格式不正确，请重新选择！");
        				return false;
        			}
        			document.selection.empty();
        		}
        		return true;
        	}
        	
        	$("#saveBtn").on("click",function(){
        		var arrmsg=new Array(new Array("name","用户名不能为空"),
        				new Array("password","密码不能为空"),
        				new Array("email","邮箱不能为空"),
        				new Array("sex","性别不能为空"));
        		if($("#userPhoto").val()==""||$("userPhoto").val()==null){
        			if($("#showPic").src==""){
        				alert("头像不能为空！");
        				return false;
        			}else{     				
        				$("#hiddenText").val($("#showPic")[0].src.replace("<%=serverPath%>",''));
        			}
        		}
        		
        		for(var i=0;i<arrmsg.length;i++){
        			if($("#${arrmsg[i][0]}").val()==null ||$("#${arrmsg[i][0]}").val()==""){
        				alert(arrmsg[i][1]);
        				$("#${arrmsg[i][1]}").focus();
        				return false;
        			}
        		}
        	});
		</script>
        
    </body>
</html>
