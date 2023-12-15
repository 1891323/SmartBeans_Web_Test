<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/page.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <title>스마트빈스</title>
</head>
<body>
<noscript><p>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</p></noscript>
    <div class="container-fluid" style="padding: 0;">
        <div class="p_main">
            <div class="g_area">
                <div class="inner">
                    <div class="left_col">
                        <!-- 주간 농사정보 -->
                        <div class="farm_info">
                            <div class="head">
                                <h2>주간 농사정보</h2>
                                <%--<p>표준프레임워크 경량화 서비스에 대한 자주하는 질문의 답변들을 볼 수 있습니다.</p>--%>
                            </div>

                            <%--<c:forEach var="result" items="${faqList}" varStatus="status">
                                <dl>
                                    <dt><span class="q">Q</span><a href="<c:url value='/uss/olh/faq/FaqListInqire.do' />"><c:out value="${result.qestnSj}"/></a></dt>
                                    <dd><span class="a">A</span><c:out value="${fn:substring(fn:escapeXml(result.answerCn), 0, 70)}" /></dd>
                                </dl>
                            </c:forEach>--%>

                            <a href="/user/farminfo/WeeklyFarming.do" class="more">더보기</a>
                        </div>

                        <!-- 병해충 발생정보 -->
                        <div class="pest_info">
                            <div class="head">
                                <h2>병해충 발생정보</h2>
                            </div>
                            <a href="/user/farminfo/PestOccuranceInformation.do" class="more">더보기</a>
                        </div>

                        <!-- 노지 콩 재배 매뉴얼 -->
                        <div class="manual">
                            <h2>충북 괴산 노지 콩<span> 재배 매뉴얼</span></h2>
                            <a href="#LINK" class="more">더보기</a>
                        </div>
                    </div>
                    <div class="right_col">
                        <!-- 괴산군 날씨 정보 -->
                        <div class="weather_info">
                            <div class="weather_info_left">
                                <h2>괴산군 날씨 정보</h2>
                                <img src="<c:url value='/'/>images/main/wi_${weatherData.img}.svg" alt="날씨">
                                <h1>${weatherData.tmp}º</h1>
                                <h5>${weatherData.today}</h5>
                                <p>날씨 <span>${weatherData.weather}</span> · 강수확률 <span>${weatherData.pop}%</span></p>
                                <p>습도 <span>${weatherData.reh}%</span> · 풍속 <span>${weatherData.wsd}m/s</span></p>
                            </div>
                            <div class="weather_info_right">
                                <c:forEach var="tmp" items="${tmp}">
                                    <span class="hour">${fn:substring(tmp.fcstTime,0,2)}시</span>
                                </c:forEach>
                                <br>
                                <c:forEach var="imgList" items="${imgList}">
                                    <img src="<c:url value='/'/>images/main/wi_${imgList.img}.svg" alt="날씨">
                                </c:forEach>
                                <br>
                                <c:forEach var="tmp" items="${tmp}">
                                    <span class="tmp">${tmp.fcstValue}º</span>
                                </c:forEach>
                                <br>
                                <c:forEach var="wsd" items="${wsd}">
                                    <span class="wsd">${wsd.fcstValue}m/s</span>
                                </c:forEach>
                            </div>
                                <%--<ul>
                                <li><a href="#LINK" class="tech" onclick="javascript:goMenuPage('2000000'); return false;">기술지원 필요시<br>유지보수 민원</a></li>
                                <li><a href="#LINK" class="buy" onclick="javascript:goMenuPage('2000000'); return false;">구매 제품<br>A/S 민원</a></li>
                            </ul>--%>
                            <a href="/user/farminfo/WeatherInformation.do" class="more">더보기</a>
                        </div>

                        <!-- 공지사항 -->
                        <div class="notification">
                            <h2>공지사항</h2>
                           <%-- <a href="<c:url value=''/>">참여하기</a>--%>
                            <a href="/user/noti/Announcement.do" class="more">더보기</a>
                        </div>

                        <%--곡물별 가격 현황--%>
                        <div class="price">
                            <h2>곡물별 가격 현황</h2>
                            <a href="/user/farminfo/GrainPriceStatus.do" class="more">더보기</a>
                            <canvas id="myChart" width="400" height="250"></canvas>
                            <%--<div id="carouselExample" class="carousel carousel-dark slide">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <canvas id="myChart" width="200" height="250"></canvas>
                                    </div>
                                    <div class="carousel-item">
                                        <img src="..." class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="..." class="d-block w-100" alt="...">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>--%>
                        </div>
                        <script type="text/javascript">
                            const context = document.getElementById('myChart').getContext('2d');
                            const myChart = new Chart(context, {
                                type: 'bar',
                                data: {
                                    labels: ['6일전', '5일전', '4일전', '3일전', '2일전', '1일전', '오늘'],
                                    datasets: [
                                        {
                                            type: 'line',
                                            label: '평균가격',
                                            borderColor: 'rgb(255, 99, 132)',
                                            borderWidth: 2,
                                            fill: false,
                                            data: ['140', '500', '1200', '390', '800', '130', '250'],
                                            yAxisID: 'y',
                                        },
                                        {
                                            type: 'bar',
                                            label: '거래물량',
                                            backgroundColor: 'rgb(75, 192, 192)',
                                            data: ['100', '150', '200', '110', '80', '50', '180'],
                                            borderColor: 'white',
                                            borderWidth: 2,
                                            yAxisID: 'y1',
                                        }
                                    ]
                                },
                                options: {
                                    interaction: {
                                        mode: 'index',
                                        intersect: false,
                                    },
                                    scales: {
                                        y: {
                                            type: 'linear',
                                            display: true,
                                            position: 'left',
                                        },
                                        y1: {
                                            type: 'linear',
                                            display: true,
                                            position: 'right',
                                            grid: {
                                                drawOnChartArea: false,
                                            },
                                        },
                                    },
                                    plugins: {
                                        tooltip: {
                                            padding: 10,
                                            bodySpacing: 5,
                                            usePointStyle: true,
                                            filter: (item) => item.parsed.y !== null,
                                            callbacks: {
                                                label: (context) => {
                                                    let label = context.dataset.label;

                                                    return context.parsed.y !== null &&
                                                    label === '평균가격' ? label + ': ' + context.parsed.y + '원/kg'
                                                        : label + ': ' + context.parsed.y + '톤'
                                                },
                                            },
                                        }
                                    },
                                }
                            });
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <%--슬라이더--%>
        <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="2000">
                    <div class="position-relative">
                        <img src="${pageContext.request.contextPath}/images/main/slide01.jpg" class="d-block w-100 img-fluid" alt="...">
                        <p class="position-absolute top-50 start-50 translate-middle">자동화 장비·기계 지원</p>
                    </div>
                </div>
                <div class="carousel-item" data-bs-interval="2000">
                    <div class="position-relative">
                        <img src="${pageContext.request.contextPath}/images/main/slide02.jpg" class="d-block w-100 img-fluid" alt="...">
                        <p class="position-absolute top-50 start-50 translate-middle">영농지원 기반 시설</p>
                    </div>
                </div>
                <div class="carousel-item" data-bs-interval="2000">
                    <div class="position-relative">
                        <img src="${pageContext.request.contextPath}/images/main/slide03.jpg" class="d-block w-100 img-fluid" alt="...">
                        <p class="position-absolute top-50 start-50 translate-middle">스마트 SPC/APC</p>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
</body>
</html>