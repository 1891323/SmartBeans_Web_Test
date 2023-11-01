<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

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
    <title>스마트빈스</title>
</head>
<body>
<noscript><p>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</p></noscript>
    <div class="container">
        <div class="p_main">
            <div class="g_area">
                <div class="inner">
                    <div class="left_col">
                        <!-- FAQ -->
                        <div class="faq">
                            <div class="head">
                                <h2>질문<span>FAQ</span></h2>
                                <p>표준프레임워크 경량화 서비스에 대한 자주하는 질문의 답변들을 볼 수 있습니다.</p>
                            </div>

                            <%--<c:forEach var="result" items="${faqList}" varStatus="status">
                                <dl>
                                    <dt><span class="q">Q</span><a href="<c:url value='/uss/olh/faq/FaqListInqire.do' />"><c:out value="${result.qestnSj}"/></a></dt>
                                    <dd><span class="a">A</span><c:out value="${fn:substring(fn:escapeXml(result.answerCn), 0, 70)}" /></dd>
                                </dl>
                            </c:forEach>--%>

                            <%--<a href="<c:url value='/uss/olh/faq/FaqListInqire.do' />" class="more">더보기</a>--%>
                        </div>

                        <!-- 홍보물 -->
                        <div class="promotion">
                            <h2>홍보물</h2>
                            <p><strong>발간자료 / 뉴스기사</strong> 등의 홍보자료를 제공하고 있습니다.</p>
                            <a href="#LINK" class="more">더보기</a>
                        </div>
                    </div>
                    <div class="right_col">
                        <!-- 한방에 신청하는 민원 -->
                        <div class="complaint">
                            <h2>한방에 신청하는 <span>민원</span></h2>
                            <ul>
                                <li><a href="#LINK" class="tech" onclick="javascript:goMenuPage('2000000'); return false;">기술지원 필요시<br>유지보수 민원</a></li>
                                <li><a href="#LINK" class="buy" onclick="javascript:goMenuPage('2000000'); return false;">구매 제품<br>A/S 민원</a></li>
                            </ul>
                            <a href="#LINK" class="more" onclick="javascript:goMenuPage('2000000'); return false;">더보기</a>
                        </div>

                        <!-- 포털 설문 참여 -->
                        <div class="survey">
                            <h2>포털 설문 <span>참여</span></h2>
                            <p>표준프레임워크 경량환경 포털 홈페이지<br>
                                이용에 대해서 사용자 여러분들께<br>
                                설문조사를 진행합니다.</p>
                            <a href="<c:url value=''/>">참여하기</a>
                        </div>
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
                    <img src="/images/1.jpg" class="d-block w-100 img-fluid" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>First slide label</h5>
                        <p>Some representative placeholder content for the first slide.</p>
                    </div>
                </div>
                <div class="carousel-item" data-bs-interval="2000">
                    <img src="/images/2.jpg" class="d-block w-100 img-fluid" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Second slide label</h5>
                        <p>Some representative placeholder content for the second slide.</p>
                    </div>
                </div>
                <div class="carousel-item" data-bs-interval="2000">
                    <img src="/images/3.jpg" class="d-block w-100 img-fluid" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Third slide label</h5>
                        <p>Some representative placeholder content for the third slide.</p>
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