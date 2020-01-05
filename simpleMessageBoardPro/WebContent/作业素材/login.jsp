<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>登录</title>
		<link rel="stylesheet" href="css/login.css">

		<script type="text/javascript">
            function changeImg() {
                var img = document.getElementById("img");
                img.src = "/verificationCode.do?date=" + new Date();
            }
   
		</script>
	</head>
	<body>
		<div class="login">
			<div class="header">
				<h1>
					<a href="">登录</a>
					<a href="">注册</a>
				</h1>
				<button></button>
			</div>
			<form action="" method="post">
				<div class="name">
					<input type="text" id="name" name="username" placeholder="请输入登录用户名">
					<p></p>
				</div>
				<div class="pwd">
					<input type="password" id="pwd" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
					<p></p>
				</div>
				<div class="idcode">
					<input type="text" id="verificationCode" placeholder="请输入验证码">
					<a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;换一张</a>
					<span><img id="img" src="/verificationCode.do"/></span>
					<div class="clear"></div>
				</div>
				<div class="autoLogin">
					<label for="">
						<input type="checkbox" checked="checked">
						下次自动登录
					</label>
					<a href="">忘记密码</a>
				</div>
				<div class="btn-red">
					<input  type="submit" value="登录" id="login-btn">
				</div>
			</form>
		</div>
	</body>
</html>