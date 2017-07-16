<%@ page import="com.handsome.auction.bean.User" %>
<%@ page import="com.handsome.auction.service.RecordService" %><%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/18
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        body {
            position: relative;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: gainsboro;
            height: 40px;
            line-height: 40px;
            padding: 0 20px;
            width: 100%;
            position: fixed;
            z-index: 1;
            box-shadow: 0 0 10px grey;
        }

        header .brand {
            font-size: 20px;
            color: black;
            font-weight: bold;
        }

        /*-----------头部的菜单栏--------*/

        header .menu {
            display: inline-block;
            position: absolute;
            right: 100px;
        }

        header .menu a {
            font-size: 14px;
            margin: 0 10px;
            color: black;
            text-decoration: none;
        }

        header .menu a:hover {
            color: orangered;
        }

        /*------------------中间的内容元素----------------*/

        content {
            display: block;
            padding: 50px 0;
            background-color: #fcf8e3;
        }
    </style>
</head>
<body>

<header>
    <a class="brand" href="index.jsp">英俊拍卖系统</a>
    <div class="menu">
        <%
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
        %>
        <a href="login.jsp">登录</a>
        <a href="register.jsp">注册</a>
        <%
        } else if (user.getRoot() == 0) {
            RecordService recordService = new RecordService();
            long count = recordService.getCount(user.getUserId());
        %>
        <%--TODO 用户个人信息页--%>
        欢迎您，<a href="#">${sessionScope.user.username}</a>
        <a href="javascript:void(0);">我的竞拍记录(<%=count%>)</a>
        <a href="logout.action">[退出登录]</a>
        <%
        } else {
        %>
        <%--TODO 管理员个人信息页--%>
        欢迎您，<a href="#">${sessionScope.user.username}</a>
        <a href="logout.action">[退出登录]</a>
        <%
            }
        %>
    </div>
</header>

</body>
</html>
