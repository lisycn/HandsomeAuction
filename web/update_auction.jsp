<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/7/16
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>修改商品</title>
    <link href="css/common.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body onload="showMsg('${msg}')">

<script type="text/javascript">
    function showMsg(msg) {
        if (msg != null && msg != "") {
            alert(msg);
        }
    }
</script>

<jsp:include page="header.jsp"/>
<div class="wrap">
    <!-- main begin-->
    <div class="sale">
        <h1 class="lf"><a href="index_manager.jsp">返回</a></h1>
        <%--<div class="logout right"><a href="#" title="注销">注销</a></div>--%>
    </div>
    <div class="login logns produce">
        <h1 class="blues">拍卖品信息</h1>
        <form action="updateAction.action" method="post">
            <%--防止更新时那些没出现在表单的变量被置空--%>
            <input type="hidden" name="auctionId" value="${auction.auctionId}"/>
            <input type="hidden" name="image" value="${auction.image}"/>
            <input type="hidden" name="auctionPicType" value="${auction.auctionPicType}"/>
            <dl>
                <dd>
                    <label>名称：</label>
                    <input type="text" class="inputh lf" name="auctionName" value="${auction.auctionName}"/>
                    <div class="xzkbg spbg lf"></div>
                </dd>
                <dd>
                    <label>起拍价：</label>
                    <input type="number" class="inputh lf" name="startPrice" value="${auction.startPrice}"/>
                    <div class="lf red laba">必须为数字</div>
                </dd>
                <dd>
                    <label>最小加价：</label>
                    <input type="number" class="inputh lf" name="minPrice" value="${auction.minPrice}"/>
                    <div class="lf red laba">必须为数字</div>
                </dd>
                <dd>
                    <label>开始时间：</label>
                    <input type="datetime" class="inputh lf" name="auctionStartTime"
                           onclick="return SelectDate(this,'yyyy-MM-dd');"
                           value="<fmt:formatDate value="${auction.auctionStartTime}" pattern="yyyy-MM-dd"/>"/>
                    <div class="lf red laba">格式：2011-05-05 12:30:00</div>
                </dd>
                <dd>
                    <label>结束时间：</label>
                    <input type="datetime" class="inputh lf" name="auctionEndTime"
                           onclick="return SelectDate(this,'yyyy-MM-dd');"
                           value="<fmt:formatDate value="${auction.auctionEndTime}" pattern="yyyy-MM-dd"/>"/>
                    <div class="lf red laba">格式：2011-05-05 12:30:00</div>
                </dd>
                <dd class="dds">
                    <label>描述：</label>
                    <textarea name="description" cols="" rows="" class="textarea">${auction.description}</textarea>
                </dd>
                <dd>
                    <label>修改图片：</label>
                    <div class="lf salebd"><a href="#"><img src="${auction.image}" width="100" height="100"/></a></div>
                    <input name="" type="file" class="marg10"/>
                </dd>
                <dd class="hegas">
                    <input name="" type="submit" value="保 存" class="spbg buttombg buttombgs f14 lf buttomb"/>
                    <input onclick="javascript:history.back()" value="取 消"
                           class="spbg buttombg buttombgs f14 lf buttomb"/>
                </dd>
            </dl>
        </form>
    </div>
    <!-- main end-->
    <!-- footer begin-->

</div>
<!--footer end-->

</div>

<script src="js/WebCalendar.js" type="text/javascript"></script>

</body>
</html>
