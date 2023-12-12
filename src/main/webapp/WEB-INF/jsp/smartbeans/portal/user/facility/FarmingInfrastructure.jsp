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
        margin-top:19%;
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
        <li><a href="#">영농지원 기반 시설</a></li>
    </ul>
</div>

<div class="content">
    <h2 name ="">영농지원 기반 시설</h2>

    <div id="all" class="category-content">
        <div class="page-content">
            <div class="page-content-name">
                <div class="name-line"></div>
            </div>

            <div class="content-box">
                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">목표</h3>
                    <li>기존 시설물의 스마트화를 통해 편의성 및 효율성 제고</li>
                    <li>각 사업별 데이터 획득을 통한 데이터서비스 및 빅데이터 활용</li>
                    <li>작물의 수확 이후 선별·보관·수매, 유통 전반의 시스템 개선</li>
                    <li>스마트 농업 지원센터 신축과 빅데이터 통합 플랫폼 구축을 통한 스마트농업 기초 마련</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="margin-bottom:1.5rem; color: #365C4A;">사업 내용</h3>

                    <div class="content-box-mini">

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/plan-content-img07.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">SPC 스마트화</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/facility-content-img01.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">APC(저온 저장고) 스마트화</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/facility-content-img02.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">스마트 유통관리 시스템</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/facility-content-img03.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">스마트 농업 지원센터</p>
                        </div>

                        <div class="page-content-minibox">
                            <div class="image-box">
                                <img src="<c:url value="/images/facility/facility-content-img04.svg"/>"  style= height:10% alt="썸네일">
                            </div>

                            <p class="box-text">빅데이터 통합 플랫폼</p>
                        </div>

                    </div>
                </div>

                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">추진기간</h3>
                    <li>2020년 ~ 2022년 (3년)</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">사업량</h3>
                    <li>SPC&APC 설비 2식, ERP SW 1식, 센터 1식, 플랫폼HW·SW 1식</li>
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
                    <li>각 시스템에서 수집된 데이터들은 스마트유통관리 시스템 ERP 서버에 DB로 전송 되고,</li>
                    <p style="margin-left:2.3%">생산자가 필요한 정보 (선별 결과, 보관, 수매결과 등)를 생산</p>
                    <li>단지 내 스마트농업지원센터로 전송하여 데이터를 공유함</li>
                </div>

                <div class="page-content-box2" style="border:none; padding-left:18px; padding-right:18px;">
                    <img src="<c:url value="/images/facility/facility-content-img05.svg"/>"  style="width:100%; float:left;" alt="썸네일">
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
