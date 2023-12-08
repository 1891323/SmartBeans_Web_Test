<%--
  Created by IntelliJ IDEA.
  User: 네이버시스템
  Date: 2023-12-08
  Time: 오후 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/page.css">
    <title>주간농사정보</title>
</head>
<body>
<div class="location">
    <ul>
        <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                             alt="메인으로"  style="position: relative; top: -5px;">
        </a></li>
        <li><a href="#">농업정보</a></li>
        <li><a href="#">주간농사정보</a></li>
    </ul>
</div>
<div class="title">
    <h2>주간농사정보</h2>
</div>
</body>
</html>
