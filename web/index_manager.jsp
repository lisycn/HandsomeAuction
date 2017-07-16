<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/7/14
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>拍卖商品管理页</title>
    <link href="css/common.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body onload="onLoad()">

<jsp:include page="header.jsp"/>

<div class="wrap">
    <!-- main begin-->
    <div class="sale">
        <h1 class="lf">英俊拍卖系统</h1>
        <%--<div class="logout right"><a href="#" title="注销">注销</a></div>--%>
    </div>
    <div class="forms">
        <label for="name">名称</label>
        <input name="auctionName" type="text" value="${auctionName}" class="nwinput" id="name"/>
        <label for="names">描述</label>
        <input name="description" type="text" value="${description}" id="names" class="nwinput"/>
        <label for="time">开始时间</label>
        <input name="auctionStartTime" type="datetime"
               onclick="return SelectDate(this,'yyyy-MM-dd');"
               value="<fmt:formatDate value="${auctionStartTime}" pattern="yyyy-MM-dd"/>"
               id="time" class="nwinput"/>
        <label for="end-time">结束时间</label>
        <input name="auctionEndTime" type="datetime"
               onclick="return SelectDate(this,'yyyy-MM-dd');"
               value="<fmt:formatDate value="${auctionEndTime}" pattern="yyyy-MM-dd"/>"
               id="end-time" class="nwinput"/>
        <label for="price">起拍价</label>
        <input name="startPrice" type="number" value="${startPrice}" id="price" class="nwinput"/>
        <input name="" type="button" value="查询" onclick="search()" class="spbg buttombg f14  sale-buttom"/>
        <input type="button" value="发布" onclick="addAuction()" class="spbg buttombg f14  sale-buttom buttomb"/>
    </div>
    <div class="items">
        <ul class="rows even strong">
            <li>名称</li>
            <li class="list-wd">描述</li>
            <li>开始时间</li>
            <li>结束时间</li>
            <li>起拍价</li>
            <li class="borderno">操作</li>
        </ul>

        <c:forEach var="auction" items="${requestScope.auctionList}" varStatus="status">
            <ul class="rows<c:if test="${status.index%2==1}"> even</c:if>">
                <li><a href="javascript:void(0);" title="">${auction.auctionName}</a></li>
                <li class="list-wd">${auction.description}</li>
                <li><fmt:formatDate value="${auction.auctionStartTime}" pattern="yyyy-MM-dd"/></li>
                <li><fmt:formatDate value="${auction.auctionEndTime}" pattern="yyyy-MM-dd"/></li>
                <li><fmt:formatNumber value="${auction.startPrice}" type="currency"/></li>
                <li class="borderno red">
                    <a href="getAuctionInfo.action?auctionId=${auction.auctionId}">修改</a>|
                    <a href="javascript:void(0);" onclick="del(${auction.auctionId},'${auction.auctionName}')">删除</a>
                </li>
            </ul>
        </c:forEach>

        <div class="page">
            <a href="javascript:void(0);" onclick="changePage(0)">首页</a>
            <a href="javascript:void(0);" onclick="changePage(${currentPage-1})">上一页</a>

            <c:forEach var="i" begin="0" end="${(empty totalPage || totalPage<=0) ? 0:totalPage-1}" step="1">
                <c:if test="${currentPage==i}">
                    <span class="red">${i+1}</span>
                </c:if>
                <c:if test="${currentPage!=i}">
                    <a href="javascript:void(0);" onclick="changePage(${i})">${i+1}</a>
                </c:if>
            </c:forEach>

            <a href="javascript:void(0);" onclick="changePage(${currentPage+1})">下一页</a>
            <a href="javascript:void(0);" onclick="changePage(${totalPage-1})">尾页</a>
        </div>
    </div>
    <!-- main end-->
</div>

<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="js/wang.js" type="text/javascript"></script>
<script src="js/WebCalendar.js" type="text/javascript"></script>
<script>
    function onLoad() {
        getDataFromAction();
        showMsg("${msg}");
    }

    function showMsg(msg) {
        if (msg != null && msg != "") {
            alert(msg);
        }
    }

    function getDataFromAction() {
        <c:if test="${empty hadGetAuctionList || hadGetAuctionList!=true}">
        window.location.href = "searchAuctions.action";
        </c:if>
    }

    function addAuction() {
        window.location.href = "add_auction.jsp";
    }

    function search() {
        var paramMap = {
            auctionName: $("input[name=auctionName]").val(),
            description: $("input[name=description]").val(),
            auctionStartTime: $("input[name=auctionStartTime]").val(),
            auctionEndTime: $("input[name=auctionEndTime]").val(),
            startPrice: $("input[name=startPrice]").val()
        };
        var newHref = setParamInUrl(window.location.href, paramMap);
        alert(newHref);
        window.location.href = newHref;
    }

    function changePage(page) {
        if (page != "${currentPage}" && page >= "0" && page < "${totalPage}") {
            var newHref = updateParamInUrl(window.location.href, {currentPage: page});
            window.location.href = newHref;
        }
    }

    function del(auctionId, auctionName) {
        if (confirm("你真的确认要删除吗？商品名称：" + auctionName)) {
            window.location.href = "deleteAction.action?auctionId=" + auctionId + "&auctionName=" + auctionName;
        }
    }
</script>

</body>
</html>
