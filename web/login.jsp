<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/7/13
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>用户登录</title>
    <link href="css/common.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrap">
    <!-- main begin-->
    <div class="main">
        <div class="sidebar">
            <p><img src="images/img1.jpg" width="443" height="314" alt=""/></p>
        </div>
        <div class="sidebarg">
            <form action="login.action" method="post">
                <div class="login">
                    <dl>
                        <dt class="blues">用户登陆</dt>
                        <dd>
                            <label for="username">用户名：</label>
                            <input type="text" class="inputh" placeholder="用户名" name="username" value="${username}"
                                   id="username"/>
                        </dd>
                        <dd>
                            <label for="password">密 码：</label>
                            <input type="text" class="inputh" placeholder="密码" name="password" value="${password}"
                                   id="password"/>
                        </dd>
                        <dd>
                            <label class="lf" for="passwords">验证码：</label>
                            <input type="text" class="inputh inputs lf" placeholder="验证码" name="passwords"
                                   id="passwords"/>
                            <span class="wordp lf"><img src="Number.jsp" width="96" height="27" alt=""/></span>
                            <span class="blues lf"><a href="login.jsp" title="">看不清</a></span>
                        </dd>
                        <dd>
                            <input name="" type="checkbox" id="rem_u"/>
                            <span for="rem_u">下次自动登录</span>
                        </dd>
                        <dd class="buttom">
                            <input name="" type="submit" value="登 录" class="spbg buttombg f14 lf"/>
                            <input onclick="go_register()" value="注 册" class="spbg buttombg f14 lf"/>
                            <span class="blues  lf"><a href="" title="">忘记密码?</a></span>
                            <div class="cl"></div>
                        </dd>

                        <div>${msg}</div>

                    </dl>
                </div>
            </form>
        </div>
        <div class="cl"></div>
    </div>
    <!-- main end-->

    <!-- footer begin-->
</div>
<!--footer end-->

<script type="text/javascript">
    function go_register() {
        location.href = "register.jsp";
    }

    //TODO 防止用户注销后点击后退
</script>

</div >
</body>
</html>
