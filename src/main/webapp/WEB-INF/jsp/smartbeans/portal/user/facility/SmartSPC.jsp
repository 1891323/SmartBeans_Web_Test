<%--
  Created by IntelliJ IDEA.
  User: Neighborsystem
  Date: 2023-12-08
  Time: 오후 3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .footer{
        margin-top:12%;
    }
</style>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/page.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/user/machinesupport.css">
</head>
<body>

<div class="location">
    <ul>
        <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                             alt="메인으로"  style="position: relative; top: -1px;">
        </a></li>
        <li><a href="#">단지내 주요 시설물</a></li>
        <li><a href="#">스마트 SPC/APC</a></li>
    </ul>
</div>

<div class="content">
    <h2 name ="">스마트 SPC/APC</h2>

    <div id="all" class="category-content">
        <div class="page-content">
            <div class="page-content-name">
                <div class="name-line"></div>
            </div>

            <div class="content-box">
                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">목표</h3>
                    <li>ICT기술을 토대로 기초기반조성을 통한 스마트농업의 기초 마련</li>
                    <li>대상지 내 기초 환경 데이터 취득 및 수집·분석</li>
                    <li>각 사업별 데이터 획득을 통한 데이터서비스 및 빅데이터 활용</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="margin-bottom:1.5rem; color: #365C4A;">사업 내용</h3>

                    <div class="content-box-mini">

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/plan-content-img01.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">자동 관수 시스템</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/plan-content-img02.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">환경 센서</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/foundation-content-img03.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">통합 영상 관제</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/foundation-content-img04.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">네트워크 구축</p>
                        </div>

                    </div>
                </div>

                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">추진기간</h3>
                    <li>2020년 ~ 2022년 (3년)</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">사업량</h3>
                    <li>관수 3식, 센서 184대, 관제시스템 1식, 카메라 10대, 네트워크 망 3식</li>
                </div>
            </div>
        </div>

        <div class="page-content">
            <div class="page-content-name">
                <div class="name-line"></div>
                <div class="name-name">시스템 구성</div>
            </div>

            <div class="content-box">
                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">시스템 구성</h3>
                    <p>각 시스템에서 수집된 데이터들은 자가망(광케이블, Wi-Fi6, LoRa)을 통해 스마트농업지원센터 내 DB로 전송되고</p>
                    <p>통합플랫폼의 분석을 통해 가시화되어 사용자에게 모바일이나 Web으로 표출</p>
                </div>

                <div class="page-content-box2" style="border:none; padding-left:18px; padding-right:18px;">
                    <img src="<c:url value="/images/facility/foundation-content-img05.svg"/>"  style="width:100%; float:left;" alt="썸네일">
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
