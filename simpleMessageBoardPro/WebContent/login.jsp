<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="login">
		<div class="header" id="headerDiv">
			<ul>
				<li id="loginLi" class="selected"><a href="#">登录</a></li>
				<li id="registerLi"><a href="#">注册</a></li>
			</ul>
			<button></button>
		</div>
		<div class="formDiv" id="loginDiv">
			<form action="${pageContext.request.contextPath}/loginServlet"
				method="post">
				<div class="name">
					<span>用户名</span> <input type="text" id="usernameL" name="usernameL"
						placeholder="姓名必须为3-12个字母数字或下划线">
					<p></p>
				</div>
				<div class="pwd">
					<span>密&nbsp;&nbsp;&nbsp;码</span> <input type="password"
						id="passwordL" placeholder="密码必须为6-12位纯数字" name="passwordL">
					<p></p>
				</div>
				<div class="code">
					<span>验证码</span> <input type="text" id="verifyCode"
						name="verifyCode" style="width: 125px">
					&nbsp;&nbsp;&nbsp;&nbsp; <img id="codeImg"
						src="${pageContext.request.contextPath}/checkImg"
						style="width: 130px; height: 42px; vertical-align: middle;"></img>
					<p></p>
				</div>
				<div class="btn-red">
					<span style="color: red; width: 350px;">${resultMsg}</span> <input
						type="submit" value="登录" id="login-btn">
				</div>
			</form>
		</div>
		<div class="formDiv" id="registerDiv">
			<form action="${pageContext.request.contextPath}/registerServlet"
				method="post" enctype="multipart/form-data">
				<div class="name">
					<span>用户名</span><input type="text" id="usernameR" name="usernameR"
						placeholder="姓名必须为3-12个字母数字或下划线">
				</div>
				<div class="pwd">
					<span>密码</span><input type="password" id="passwordR"
						name="passwordR" placeholder="密码必须为6-12位纯数字">
				</div>
				<div class="pwd">
					<span>确认密码</span><input type="password" id="repasswordR"
						name="repasswordR">
				</div>
				<div class="code">
					<span>邮箱</span><input type="text" id="emailR" name="emailR">
				</div>
				<div style="height:35px;line-height:35px;">
					<span>性别</span>
					<span style="width:30px;">男</span><input id="male" type="radio" checked="checked" name="sexR" value="男"
					style="width:30px;height:19px;line-height:19px;display:inline;"> 
					<span style="width:30px;">女</span><input id="female" type="radio" name="sexR" value="女" style="width:30px;height:19px;line-height:19px;display:inline;">
					<span style="width:30px;">保密</span><input id="secret" type="radio" name="sexR" value="保密" style="width:30px;height:19px;line-height:19px;display:inline;">
				</div>
				<div class="code">
					<span>上传头像</span><input type="file" name="file"
						style="width: 200px; height: 40px; line-height: 40px;" /> <input
						type="submit" value="注册" id="register-btn"
						style="width: 60px; height: 50px; line-height: 50px; margin-left: 15px;" />
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		var flag = true;
		$("#loginLi").on("click", function() {
			if (!flag) {
				$("#loginLi").addClass("selected");
				$("#registerLi").removeClass("selected");
				$("#registerDiv").css("display", "none");
				$("#loginDiv").css("display", "block");
				flag = true;
			}
		});

		$("#registerLi").on("click", function() {
			if (flag) {
				$("#registerLi").addClass("selected");
				$("#loginLi").removeClass("selected");
				$("#loginDiv").css("display", "none");
				$("#registerDiv").css("display", "block");
				flag = false;
			}
		});

		$("#register-btn")
				.on(
						"click",
						function() {
							var arrmsg = new Array(new Array("usernameR",
									"用户名不能为空!"), new Array("passwordR",
									"密码不能为空!"), new Array("repasswordR",
									"确认密码不能为空!"),
									new Array("emailR", "邮箱不能为空!"));

							for (var i = 0; i < arrmsg.length; i++) {
								if ($("#" + arrmsg[i][0]).val() == null
										|| $("#" + arrmsg[i][0]).val() == "") {
									alert(arrmsg[i][1]);
									$("#" + arrmsg[i][0]).focus();
									return false;
								}
							}

							var regName = /^[a-zA-Z0-9_]{3,12}$/;
							if (regName.test($('#usernameR').val()) == false) {
								alert("姓名必须为3-12个字母数字或下划线！");
								return false;
							}

							/*
							var regMobile=/^[1][0-9]{10}$/;
							if(regMobile.test($('#mobile').val())==false){
								alert("您输入的手机号格式不对！");
								return false;
							}
							 */

							var regPassword = /^[0-9]{6,12}$/;
							if (regPassword.test($("#passwordR").val()) == false) {
								alert("密码必须为6-12位纯数字！");
								$("#repasswordR").focus();
								return false;
							} else if ($('#passwordR').val() != $(
									'#repasswordR').val()) {
								alert("确认密码与第一次输入密码不一致！");
								$("#repasswordR").focus();
								return false;
							}

							var regMail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
							if (regMail.test($('#emailR').val()) == false) {
								alert("您输入的邮箱格式不对！");
								return false;
							}

							return true;
						});

		$("#login-btn").on("click", function() {
			var username = $("#usernameL").val();
			var password = $("#passwordL").val();
			var verifyCode = $("#verifyCode").val();

			if (username == "" || username == null) {
				alert("用户名不能为空！");
				return false;
			}

			if (password == "" || password == null) {
				alert("密码不能为空！");
				return false;
			}

			if (verifyCode == "" || password == null) {
				alert("验证码不能为空！")
				return false;
			}

			return true;

		});
		
		$("#codeImg")
				.on(
						"click",
						function() {
							var codeImg = document.getElementById("codeImg");
							codeImg.src = "${pageContext.request.contextPath}/checkImg?time="
									+ new Date().getTime();
						});
	</script>
</body>
</html>