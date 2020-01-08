<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
    
            <div class="container">
                <% if (null != request.getSession().getAttribute("existUser")) {%>
                	<div class="rightNavDiv">
                    	<nav>
                        	<a href="<%=request.getContextPath() %>/myMessageServlet">我的留言</a>
                    	</nav>
                    	<nav>
                        	<a href="<%=request.getContextPath() %>/showUser.jsp">我的信息</a>
                    	</nav>
                    </div>
                    <div class="leftNavDiv">
                		<nav>
            				<a href="<%=request.getContextPath() %>/addMessageCategory.jsp">新建分类</a>
            				<a href="<%=request.getContextPath() %>/addMessage.jsp">新建留言</a>
            			</nav>
            		</div>
                <%} else { %>
                	<div class="rightNavDiv">
                    	<nav>
                        	<a href="<%=request.getContextPath() %>/login.jsp">登录</a>
                        	<a href="<%=request.getContextPath() %>/login.jsp">注册</a>
                    	</nav>
                    </div>
                <% } %>
                
            </div>