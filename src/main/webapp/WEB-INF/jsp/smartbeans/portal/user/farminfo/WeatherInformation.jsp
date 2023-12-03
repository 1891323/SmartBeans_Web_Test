<%--
  Created by IntelliJ IDEA.
  User: 네이버시스템
  Date: 2023-11-17
  Time: 오후 3:41
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
    <title>날씨정보</title>
</head>
<body>
<div class="location">
    <ul>
        <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                             alt="메인으로"  style="position: relative; top: -5px;">
        </a></li>
        <li><a href="#">농업정보</a></li>
        <li><a href="#">날씨정보</a></li>
    </ul>
</div>
<div class="title">
    <h2>날씨정보</h2>
</div>
<div class="w_area">
    <div class="area">
        <h2>충청북도 괴산군</h2>
        <div class="now">
            <img src="<c:url value='/'/>images/main/wi_${weatherData.img}.svg" alt="날씨">
            <div class="tmp">${weatherData.tmp}º</div>
        </div>
        <div class="detail">
            <p class="today">${weatherData.today}</p>
            <span class="name">강수확률 </span><span class="value">${weatherData.pop}%</span> · <span class="name">습도 </span><span class="value">${weatherData.reh}%</span> · <span class="name">풍속 </span><span class="value">${weatherData.wsd}m/s</span>
        </div>
    </div>
    <div>
        <div class="flex-container space-around">
            <div class="flex-item">
                <p>내일</p>
                <p>${tmrw.today}</p>
            </div>
            <div class="flex-item">
                <img src="<c:url value='/'/>images/main/wi_${tmrw.img}.svg" alt="날씨">
            </div>
            <div class="flex-item">
                <p class="name">강수확률</p>
                <p class="value">${tmrw.pop}%</p>
            </div>
            <div class="flex-item">
                <p class="name">기온</p>
                <p class="value">${tmrw.tmp}º</p>
            </div>
        </div>
        <div class="flex-container space-around">
            <div class="flex-item">
                <p>모레</p>
                <p>${aftertmrw.today}</p>
            </div>
            <div class="flex-item">
                <img src="<c:url value='/'/>images/main/wi_${aftertmrw.img}.svg" alt="날씨">
            </div>
            <div class="flex-item">
                <p class="name">강수확률</p>
                <p class="value">${aftertmrw.pop}%</p>
            </div>
            <div class="flex-item">
                <p class="name">기온</p>
                <p class="value">${aftertmrw.tmp}º</p>
            </div>
        </div>
    </div>
    <div class="week">
        <div class="flex-container space-around">
            <div class="flex-item">
                <p>${weekMap.weekaf3}</p>
                <p>${dateMap.dateaf3}</p>
                <img src="<c:url value='/'/>images/${wfImg.wf3Am}.svg" alt="날씨">
                <p>강수확률 ${rnMap.rnSt3Am}%</p>
                <p>최저 ${taMap.taMin3}º</p>
                <p>최고 ${taMap.taMax3}º</p>
            </div>
            <div class="flex-item">
                <p>${weekMap.weekaf4}</p>
                <p>${dateMap.dateaf4}</p>
                <img src="<c:url value='/'/>images/${wfImg.wf4Am}.svg" alt="날씨">
                <p>강수확률 ${rnMap.rnSt4Am}%</p>
                <p>최저 ${taMap.taMin4}º</p>
                <p>최고 ${taMap.taMax4}º</p>
            </div>
            <div class="flex-item">
                <p>${weekMap.weekaf5}</p>
                <p>${dateMap.dateaf5}</p>
                <img src="<c:url value='/'/>images/${wfImg.wf5Am}.svg" alt="날씨">
                <p>강수확률 ${rnMap.rnSt5Am}%</p>
                <p>최저 ${taMap.taMin5}º</p>
                <p>최고 ${taMap.taMax5}º</p>
            </div>
            <div class="flex-item">
                <p>${weekMap.weekaf6}</p>
                <p>${dateMap.dateaf6}</p>
                <img src="<c:url value='/'/>images/${wfImg.wf6Am}.svg" alt="날씨">
                <p>강수확률 ${rnMap.rnSt6Am}%</p>
                <p>최저 ${taMap.taMin6}º</p>
                <p>최고 ${taMap.taMax6}º</p>
            </div>
            <div class="flex-item">
                <p>${weekMap.weekaf7}</p>
                <p>${dateMap.dateaf7}</p>
                <img src="<c:url value='/'/>images/${wfImg.wf7Am}.svg" alt="날씨">
                <p>강수확률 ${rnMap.rnSt7Am}%</p>
                <p>최저 ${taMap.taMin7}º</p>
                <p>최고 ${taMap.taMax7}º</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
