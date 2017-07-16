<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/7/13
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>用户注册</title>
    <link href="css/common.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrap">
    <!-- main begin-->
    <div class="zclf login logns">
        <h1 class="blue">用户注册</h1>
        <form action="register.action" method="post">
            <dl>
                <dd>
                    <label>
                        <small>*</small>
                        用户名</label>
                    <input type="text" class="inputh lf" name="username" value=""/>
                    <div class="lf red laba">用户名要求不低于6个字符</div>
                </dd>
                <dd>
                    <label>
                        <small>*</small>
                        密码</label>
                    <input type="text" class="inputh lf" name="password" value=""/>
                    <div class="lf red laba">密码要求不低于6个字符</div>
                </dd>
                <dd>
                    <label>
                        <small>*</small>
                        身份证号</label>
                    <input type="text" class="inputh lf" name="idNumber" value=""/>
                    <div class="lf red laba">身份证号必填</div>
                </dd>
                <dd>
                    <label>
                        <small>*</small>
                        电话</label>
                    <input type="text" class="inputh lf" name="phone" value=""/>
                    <div class="lf red laba">电话号码必填</div>
                </dd>
                <dd>
                    <label>
                        <small>*</small>
                        住址</label>
                    <input type="text" class="inputh lf" name="address" value=""/>
                </dd>
                <dd>
                    <label>
                        <small>*</small>
                        邮政编码</label>
                    <input type="text" class="inputh lf" name="postNumber" value=""/>
                </dd>
                <dd class="hegas">
                    <label>
                        <small>*</small>
                        验证码</label>
                    <input type="text" class="inputh inputs lf" value=""/>
                    <span class="wordp lf"><img src="images/img2.jpg" width="96" height="27" alt=""/></span>
                    <span class="blues lf"><a href="" title="">看不清</a></span>
                </dd>
                <dd class="hegas">
                    <label>&nbsp;</label>
                    <input name="" type="checkbox" id="rem_u"/>
                    <label for="rem_u" class="labels">我同意<span class="blues">《服务条款》</span></label>
                </dd>
                <dd class="hegas">
                    <label>&nbsp;</label>
                    <input name="" type="submit" value="立即注册" class="spbg buttombg buttombgs f14 lf"/>
                </dd>

                <span>${msg}</span>

            </dl>
        </form>
    </div>
    <!-- main end-->
    <!-- footer begin-->

</div>
<!--footer end-->

</div>
</body>
</html>
