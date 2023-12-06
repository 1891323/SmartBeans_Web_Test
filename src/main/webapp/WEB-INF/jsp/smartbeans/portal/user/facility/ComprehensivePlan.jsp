<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    src="https://code.jquery.com/jquery-3.6.4.min.js"

    function showCategory(category) {
        // 모든 카테고리 내용 숨김
        var categoryContents = document.querySelectorAll('.category-content');
        categoryContents.forEach(function (content) {
            content.classList.remove('active-content');
        });

        document.getElementById(category).classList.add('active-content');

        var catbuttons = document.querySelectorAll('catbutton');
        catbuttons.forEach(function (catbutton) {
            catbutton.classList.remove('active');
        });

        event.target.classList.add('active');
    }

    window.onload = function () {
        initializePage();
    };

    function initializePage() {
        // 초기에 스마트 기반조성 카테고리를 활성화
        showCategory('all');
    }

</script>


<!DOCTYPE html>
<html>

<head>
    <title></title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/page.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/user/comprehensiveplan.css">
</head>

<body>

<div class="location">
    <ul>
        <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                             alt="메인으로"  style="position: relative; top: -1px;">
        </a></li>
        <li><a href="#">단지내 주요 시설물</a></li>
        <li><a href="#">종합 계획</a></li>
    </ul>
</div>

<div class="content">
    <h2 name ="">종합 계획</h2>

    <div class="planmap">
        <h1 style="font-size:2rem">데이터로 일궈가는 알콩달콩 괴산</h1>
        <h1>괴산 노지스마트농업 시범사업 종합계획</h1>
        <img src="<c:url value="/images/facility/plan-map.png"/>"  style= width:100% alt="썸네일">
    </div>

    <div class="categorybox">
        <catbutton onclick="showCategory('all')">스마트 기반조성</catbutton>
        <catbutton onclick="showCategory('promotion')">자동화 장비 기계지원</catbutton>
        <catbutton onclick="showCategory('webzine')">기존시설 스마트화</catbutton>
        <catbutton onclick="showCategory('press')">빅데이터 기반의 노지스마트 농업</catbutton>
    </div>

    <div id="all" class="category-content">
        <div class="page-content">
            <div class="page-content-name">
                <div class="name-line"></div>
                <div class="name-name">스마트 기반 조성</div>
            </div>

            <div class="content-box">
                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">자동관수 시스템</h3>
                    <li>점적관수(지표36,3ha, 지중5ha), 스프링클러10ha : 관수량 및 노동력 절감, 생산력 증대 시스템 도입</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">환경센서</h3>
                    <li>토양센서(184대) : 작황과 밀접한 연관성을 지닌 토양 데이터 수집</li>
                    <li>기상센서(3대) : 실시간 기상 데이터 수집</li>
                </div>

                <div class="page-content-box3">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">통신망 구성</h3>
                    <li>광케이블(1식) : 통신망의 확장성, 안전성,운영효율성을 고려한 기반설비</li>
                    <li>Wi-Fi 망 : 스마트농기계, CCTV데이터 수집을 위한 대용량 정보수집</li>
                    <li>LoRa 망 : 센서데이터와 같은 저용량 정보수집</li>
                </div>

                <div class="page-content-boxmini">
                    <div class="page-content-minibox">
                        <div class="image-box">
                            <img src="<c:url value="/images/facility/plan-content-img01.svg"/>"  style= height:5% alt="썸네일">
                        </div>

                        <p class="box-text">관수</p>
                    </div>

                    <div class="page-content-minibox">
                        <div class="image-box">
                            <img src="<c:url value="/images/facility/plan-content-img02.svg"/>"  style= height:5% alt="썸네일">
                        </div>

                        <p class="box-text">환경센서 (지하/지상)</p>
                    </div>

                    <div class="page-content-miniboxlast">
                        <div class="image-box">
                            <img src="<c:url value="/images/facility/plan-content-img03.svg"/>"  style= height:5% alt="썸네일">
                        </div>

                        <p class="box-text">통신망설치</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="promotion" class="category-content">
        <div class="page-content">
            <div class="page-content-name">
                <div class="name-line"></div>
                <div class="name-name">자동화 장비 기계지원</div>
            </div>

            <div class="content-box">
                <div class="page-content-box1">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">농기계스마트화</h3>
                    <li>스마트농기계(트렉터 4대, 콩 콤바인 2대, 부착형농기계 18대, 기타장비 5대) : 농작업 공동운영(집단영농화)</li>
                </div>

                <div class="page-content-box2">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">병충해 예찰 및 항공방제</h3>
                    <li>IT페로몬트랩(10대) : 지점 별 병해충 포집 트랩장비 설치</li>
                    <li>방제드론(4대) : 자율 비행을 통한 정밀 변량 살포 수행</li>
                </div>

                <div class="page-content-box3">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">생육진단 및 수확량 예측</h3>
                    <li>생육진단 및 수확량 예측</li>
                    <li>회전익 드론(3대) : 저고도 촬영(5m 이하, 결주정보 및 병해충)</li>
                    <li>고정익 드론(2대) : 고고도 촬영(150m 이상, 식생지수 및 도복피해)</li>
                    <li>영상수집 분석S/W : 종합분석하여 생육진단 및 작황조사·분석</li>
                </div>

                <div class="page-content-box4">
                    <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">유해조수 인식 및 퇴치</h3>
                    <li>지능형 영상 분석 및 퇴치기(5대) : 지점 별 유해조수 감지 및 퇴치</li>
                </div>

                <div class="page-content-boxmini">
                    <div class="page-content-minibox">
                        <div class="image-box">
                            <img src="<c:url value="/images/facility/plan-content-img04.svg"/>"  style= height:5% alt="썸네일">
                        </div>

                        <p class="box-text">농기계 스마트화</p>
                    </div>

                    <div class="page-content-minibox">
                        <div class="image-box">
                            <img src="<c:url value="/images/facility/plan-content-img05.svg"/>"  style= height:5% alt="썸네일">
                        </div>

                        <p class="box-text">병충해 예찰 및 항공방제</p>
                    </div>

                    <div class="page-content-miniboxlast">
                        <div class="image-box">
                            <img src="<c:url value="/images/facility/plan-content-img06.svg"/>"  style= height:5% alt="썸네일">
                        </div>

                        <p class="box-text">생육진단 및 수확량 예측</p>
                    </div>
                </div>

            </div>

        </div>
    </div>

    <div id="webzine" class="category-content">
        <div class="page-content-name">
            <div class="name-line"></div>
            <div class="name-name">기존시설 스마트화</div>
        </div>

        <div class="content-box">

            <div class="page-content-box1">
                <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">스마트농업지원센터 (건축면적 : 682㎡,연면적 : 1,364㎡)</h3>
                <li>1F 스마트농기계보관창고 : 농기계 정비 관리, 일정 및 이력관리</li>
                <li>2F 빅데이터센터 : 관제 및 데이터서버실, 사업단 운영관리공간 조성</li>
            </div>

            <div class="page-content-box2">
                <h3 style="line-height:1rem; margin-bottom:1.5rem; color: #365C4A;">SPC 스마트화</h3>
                <li>SPC 스마트화 : SPC시설 개선, 공정별 데이터 수집</li>
                <li>APC 스마트화 : 저온저장고 중앙 제어, 보관 활용성 개선</li>
                <li>유통관리시스템(ERP) : 수확하여 입고되는 유통 전 과정의 전산화</li>
            </div>

            <div class="page-content-boxmini">
                <div class="page-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/plan-content-img07.svg"/>"  style= height:5% alt="썸네일">
                    </div>

                    <p class="box-text">SPC 스마트화</p>
                </div>

                <div class="page-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/plan-content-img08.svg"/>"  style= height:5% alt="썸네일">
                    </div>

                    <p class="box-text">스마트 농업 지원센터</p>
                </div>
            </div>

        </div>

    </div>

    <div id="press" class="category-content">
        <div class="page-content-name">
            <div class="name-line"></div>
            <div class="name-name"><p>빅데이터 기반의</p> 노지스마트 농업</div>
        </div>

        <div class="page-content-minibox2">

            <div class="minibox-title-box">
                <div class="title-box-num">01</div>
                <div class="title-box-tit"> 데이터 수집</div>
                <div class="title-box-cat">영농현장</div>
            </div>

            <div class="minibox-first-content">
                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-gps-navigation-5894199.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">발생</p>
                </div>

                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-digital-6283155.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">수집</p>
                </div>

                <div class="first-content-miniboxlast">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-wifi-area-108275.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">전송</p>
                </div>
            </div>

            <div class="content-line"></div>

            <div class="minibox-sec-content">
                <div class="image-box">
                    <img src="<c:url value="/images/facility/plan-detail-img01.svg"/>" style="height:10%" alt="썸네일">

                    <p class="box-text">데이터 수집 - 7항목, 총 105종</p>
                </div>

            </div>
        </div>

        <div class="page-content-minibox2">

            <div class="minibox-title-box">
                <div class="title-box-num">02</div>
                <div class="title-box-tit"> 데이터 처리 및 저장</div>
                <div class="title-box-cat">플랫폼</div>
            </div>

            <div class="minibox-first-content">
                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/link.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">분류</p>
                </div>

                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-trash-7356775.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">전처리</p>
                </div>

                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-recycling-4459945.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">가공</p>
                </div>

                <div class="first-content-miniboxlast">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-save-file-2810399.png"/>" style=height:30%
                             alt="썸네일">
                    </div>

                    <p class="box-text">저장</p>
                </div>
            </div>

            <div class="content-line"></div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-top:10%; margin-bottom:7%;">
                    <div style="float:left; font-weight:bold;">분류</div>
                    <div style="margin-left:5%">정형/반정형/비정형 유형 구분</div>
                </div>
            </div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-bottom:7%">
                    <div style="float:left; font-weight:bold;">전처리</div>
                    <div style="margin-left:5%">중복값/파생변수/결측처리</div>
                </div>
            </div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-bottom:7%">
                    <div style="float:left; font-weight:bold;">가공</div>
                    <div style="margin-left:5%">정규화/표준화/비식별조치</div>
                </div>
            </div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-bottom:9%">
                    <div style="float:left; font-weight:bold;">저장</div>
                    <div style="margin-left:5%">주제별 데이터웨어하우스 저장</div>
                </div>
            </div>
        </div>

        <div class="page-content-minibox2">

            <div class="minibox-title-box">
                <div class="title-box-num">03</div>
                <div class="title-box-tit"> 데이터 분석</div>
                <div class="title-box-cat">플랫폼</div>
            </div>

            <div class="minibox-first-content">
                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-profit-growth-4672328.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">추이</p>
                </div>

                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-analytics-11629295.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">통계</p>
                </div>

                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-map-1534777.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">지도</p>
                </div>

                <div class="first-content-miniboxlast">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-video-call-4012303.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">영상</p>
                </div>
            </div>

            <div class="content-line"></div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-top:10%; margin-bottom:7%;">
                    <div style="float:left; font-weight:bold;">분류</div>
                    <div style="margin-left:5%">정형/반정형/비정형 유형 구분</div>
                </div>
            </div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-bottom:7%">
                    <div style="float:left; font-weight:bold;">전처리</div>
                    <div style="margin-left:5%">중복값/파생변수/결측처리</div>
                </div>
            </div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-bottom:7%">
                    <div style="float:left; font-weight:bold;">가공</div>
                    <div style="margin-left:5%">정규화/표준화/비식별조치</div>
                </div>
            </div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-bottom:9%">
                    <div style="float:left; font-weight:bold;">저장</div>
                    <div style="margin-left:5%">주제별 데이터웨어하우스 저장</div>
                </div>
            </div>

            <div class="minibox-sec-content">
                <div class="sec-content-box" style="margin-bottom:9%">
                    <div style="float:left; font-weight:bold;">저장</div>
                    <div style="margin-left:5%">주제별 데이터웨어하우스 저장</div>
                </div>
            </div>
        </div>

        <div class="page-content-minibox2">

            <div class="minibox-title-box">
                <div class="title-box-num">04</div>
                <div class="title-box-tit"> 기초 데이터 활용</div>
                <div class="title-box-cat">농업인</div>
            </div>

            <div class="minibox-first-content">
                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-notification-2326010.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">알람</p>
                </div>

                <div class="first-content-minibox">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-search-file-2889682.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">조회</p>
                </div>

                <div class="first-content-miniboxlast">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-settings-126472.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">설정</p>
                </div>
            </div>

            <div class="content-line"></div>

            <div class="minibox-sec-content">
                <div class="image-box">
                    <img src="<c:url value="/images/facility/plan-detail-img02.svg"/>"  style= height:14.9% alt="썸네일">
                </div>
            </div>
        </div>

        <div class="page-content-minibox2">

            <div class="minibox-title-box">
                <div class="title-box-num">05</div>
                <div class="title-box-tit"> 응용 데이터 활용</div>
                <div class="title-box-cat">농업인</div>
            </div>

            <div class="minibox-first-content">
                <div class="first-content-miniboxlast">
                    <div class="image-box">
                        <img src="<c:url value="/images/facility/free-icon-info-5393011.png"/>"  style= height:30% alt="썸네일">
                    </div>

                    <p class="box-text">의사결정</p>
                </div>
            </div>

            <div class="content-line"></div>

            <div class="minibox-sec-content">
                <div class="image-box">
                    <img src="<c:url value="/images/facility/plan-detail-img03.svg"/>"  style= height:10% alt="썸네일">
                </div>
            </div>
        </div>



    </div>

</div>
</body>
</html>
