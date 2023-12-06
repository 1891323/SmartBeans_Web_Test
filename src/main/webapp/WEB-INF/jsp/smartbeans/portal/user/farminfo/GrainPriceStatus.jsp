<%--
  Created by IntelliJ IDEA.
  User: 네이버시스템
  Date: 2023-12-04
  Time: 오후 5:12
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
    <title>곡물별 가격 현황</title>
</head>
<body>
<div class="location">
    <ul>
        <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                             alt="메인으로"  style="position: relative; top: -5px;">
        </a></li>
        <li><a href="#">농업정보</a></li>
        <li><a href="#">곡물별 가격 현황</a></li>
    </ul>
</div>
<div class="title">
    <h2>곡물별 가격 현황</h2>
</div>
<div>
    <table class="price_status">
        <thead>
            <tr>
                <th>품목명</th>
                <th>단위</th>
                <th>당일</th>
                <th>1일전</th>
                <th>1개월전</th>
                <th>1년전</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${itemList}">
            <tr>
                <td>${item.productName}</td>
                <td>${item.unit}</td>
                <td>${item.dpr1}</td>
                <td>${item.dpr2}</td>
                <td>${item.dpr3}</td>
                <td>${item.dpr4}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
