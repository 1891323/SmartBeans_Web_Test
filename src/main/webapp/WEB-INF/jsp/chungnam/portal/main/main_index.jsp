<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard-dynamic-subset.css" />
<link rel="stylesheet" href="/css/egovframework/main/style.css">

<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="/js/egovframework/com/main/main.js"></script>
</head>
<html>
<body>
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <main>
        <jsp:include page="content.jsp"/>
    </main>
    <footer>
            <jsp:include page="footer.jsp"/>
     </footer>
</body>
</html>