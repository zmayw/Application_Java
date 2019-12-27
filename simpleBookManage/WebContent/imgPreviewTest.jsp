<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书后台管理</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript">
function setImagePreview(docObj,localImgId,imgObjPreview){
    		var name=docObj.value;
			var regImg=/.jpg|.png|.jpeg|.fit$/;
			if(regImg.test(name)==false){
				alert("文件类型错误，必须为图片！");
				document.getElementById("txtSrc").value=null;
				return;
			}
			if(docObj.files && docObj.files[0]){
				document.getElementById("imgDiv").style.display="block";
				document.getElementById("img").src=window.URL.createObjectURL(docObj.files[0]);
			}else{
				docObj.select();
				var imgSrc=document.selection.createRange().text;
				localImagId.style.width="300px";
				localImagId.style.height="200px";
				
				try{
					localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src=imgSrc;
				}catch(e){
					alert("您上传的图片格式不正确，请重新选择！");
					return false;
				}
				imgObjPreview.style.display="none";
				document.selection.empty();
				
			}
    		
			return true;
    	}
</script>
</head>
<body>
	<h2>hello world!</h2>
  	<!-- test preview image -->
        <div style="display:none" align="center" id="imgDiv">
        	<img alt="" src="" id="img" name="图片预览" width="200" height="200" id="previewImg">
        </div>
        <form action="${pageContext.request.contextPath}/imgTest" method="post" enctype="multipart/form-data" name="form1">
        	<input name="imgA" type="file" id="txtSrc" onChange="setImagePreview(this,imgDiv,img);">
        	<input type="submit" name="submit" value="上传">
        </form>
</body>
</html>