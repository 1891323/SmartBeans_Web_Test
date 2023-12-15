<%--
  Created by IntelliJ IDEA.
  User: 네이버시스템
  Date: 2023-12-08
  Time: 오후 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Properties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <meta content="no-cache" http-equiv="pragma">

    <link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/page.css">
    <title>주간농사정보</title>

    <!-- 공통 CSS, JS 선언 -->
    <link href="http://api.nongsaro.go.kr/css/api.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://api.nongsaro.go.kr/js/framework.js"></script>
    <script type="text/javascript" src="http://api.nongsaro.go.kr/js/openapi_nongsaro.js"></script>

    <%
        InputStream input = application.getClassLoader().getResourceAsStream("config.properties");

        Properties properties = new Properties();

        if (input != null) {
            properties.load(input);
        } else {
            throw new RuntimeException("config.properties not found");
        }

        String apiKey = properties.getProperty("NONGSARO_API_KEY");
    %>
    <script type="text/javascript">
        nongsaroOpenApiRequest.apiKey = "<%= apiKey %>";
        nongsaroOpenApiRequest.serviceName = "weekFarmInfo";
        nongsaroOpenApiRequest.operationName = "weekFarmInfoList";
        nongsaroOpenApiRequest.htmlArea = "nongsaroApiLoadingArea";
        nongsaroOpenApiRequest.callback = "http://localhost:9090/user/farminfo/FarmInfoCallback.do";
    </script>
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
    <h2>주간 농사정보</h2>
</div>
<div class="weekfarm_area">
    <div id="nongsaroApiLoadingArea"></div>
</div>
</body>
</html>
