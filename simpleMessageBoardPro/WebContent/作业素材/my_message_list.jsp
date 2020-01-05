<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>我的留言</title>
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script type="text/javascript">
            
        </script>
    </head>

    <body>
        <header>
            <div class="container">
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <nav>
                        <a href="">我的留言</a>
                    </nav>
                    <nav>
                        <a href="">我的信息</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="">登录</a>
                        <a href="">注册</a>
                    </nav>
                <% } %>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>慕课网留言板——我的留言</h1>
                    <p>慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。 </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                
                    <div class="alt-item">
                        <div class="alt-head">
                            <div class="alt-info">
                                <span>作者：<a href=""></a></span>
                                <span>时间：</span>
                            </div>
                        </div>
                        <div class="alt-content">
                            <h3>慕课网好</h3>
                            <p>慕课网好</p>
                        </div>
                        <div align="right">
                            <table>
                                <tr><td>   
                                        <button class="btn btn-primary" type="submit">修改</button>                                
                                </td>
                                <td>
                                        <button class="btn btn-primary" type="submit">删除</button>                                  
                                </td></tr>
                            </table>
                        </div>
                    </div>
             
            </div>
        </section>
        <section class="page">
            <div class="container">
               

           <!-- 分页内容参考视频中老师源码 -->
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
</html>