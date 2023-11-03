<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<meta charset="UTF-8">
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>충청남도 데이터허브 포털</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
    <link rel="stylesheet" href="/css/egovframework/main/font.css">
    <link rel="stylesheet" href="/css/egovframework/main/reset.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard-dynamic-subset.css"/>
    <link rel="stylesheet" href="/css/egovframework/main/style.css">
    <link rel="stylesheet" href="/css/component.css">
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="/css/admin/admin.css">

    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="/js/egovframework/com/main/main.js"></script>

    <tiles:insertAttribute name="commons"/>
</head>
<html>
<body>


<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="content"/>
<tiles:insertAttribute name="footer"/>


</body>
</html>
</html>