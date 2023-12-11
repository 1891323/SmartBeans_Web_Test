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
        margin-top:5%;
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
        <li><a href="#">자동화 장비·기계 지원</a></li>
    </ul>
</div>

<div class="content">
    <h2 name ="">자동화 장비·기계 지원</h2>

    <div id="all" class="category-content">
        <div class="page-content">
            <div class="page-content-name">
                <div class="name-line"></div>
            </div>

            <div class="content-box">
                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">목표</h3>
                    <li>농작업의 편의성 및 효율성 제고</li>
                    <li>각 사업별 데이터 획득을 통한 데이터서비스 및 빅데이터 활용</li>
                    <li>콩 재배 과정에 솔루션 도입을 통해 생산성 향상을 도모</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="margin-bottom:1.5rem; color: #365C4A;">사업 내용</h3>

                    <div class="content-box-mini">

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/plan-content-img04.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">농기계 스마트화</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/plan-content-img05.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">병충해 예찰 및 항공 방제</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/plan-content-img06.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">생육진단 및 수확량 예측</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/plan-content-img09.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">유해조수 인식 및 퇴치</p>
                        </div>

                    </div>
                </div>

                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">추진기간</h3>
                    <li>2020년 ~ 2022년 (3년)</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">사업내용</h3>
                    <li>농기계 장비 31대, 기타장비 1식, 항공드론 9대, 운용장비 4식, 트랩 10대</li>
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
                    <img src="<c:url value="/images/facility/automation_content_img01.svg"/>"  style="width:100%; float:left;" alt="썸네일">
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
