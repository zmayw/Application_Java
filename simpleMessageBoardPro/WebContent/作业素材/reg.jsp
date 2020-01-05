<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>注册</title>
        <link rel="stylesheet" href="css/reg.css">

        <script type="text/javascript">
            function changeImg() {
                var img = document.getElementById("img");
                img.src = "/verificationCode.do?date=" + new Date();
            }

            
        </script>
    </head>
    <body>
    <div class="reg">
        <div class="header">
            <h1>
                <a href="">登录</a>
                <a href="">注册</a>
            </h1>
            <button></button>
        </div>
        <form action="" method="post">
            <div class="name">
                <input type="text" id="name" name="name" placeholder="请输入用户名">
                <p></p>
            </div>
            <div class="pwd">
                <input type="password" id="pwd1" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
                <p></p>
            </div>
            <div class="confirm-pwd">
                <input type="password" id="pwd2" placeholder="确认密码">
                <p></p>
            </div>
            <div class="idcode">
                <input type="text" id="verificationCode" placeholder="请输入验证码">
                <a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;换一张</a>
                <span><img id="img" src="/verificationCode.do"/></span>
                <div class="clear"></div>
            </div>
            <div class="btn-red">
                <input  type="submit" value="注册" id="reg-btn">
            </div>
        </form>
    </div>
    </body>
</html>
