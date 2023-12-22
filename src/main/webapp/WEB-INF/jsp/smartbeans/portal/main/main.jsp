<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties" %>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="java.net.URL"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                            </div>
                            <%
                                InputStream input = application.getClassLoader().getResourceAsStream("config.properties");

                                Properties properties = new Properties();

                                if (input != null) {
                                    properties.load(input);
                                } else {
                                    throw new RuntimeException("config.properties not found");
                                }

                                if(true) {
                                String apiKey = properties.getProperty("NONGSARO_API_KEY");
                                String serviceName = "weekFarmInfo";
                                String operationName = "weekFarmInfoList";
                                String pageNo = "1";
                                String numOfRows = "4";

                                //XML 받을 URL 생성
                                String parameter = "/"+serviceName+"/"+operationName;
                                parameter += "?apiKey="+ apiKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows;

                                //서버와 통신
                                URL apiUrl = new URL("http://api.nongsaro.go.kr/service"+parameter);
                                InputStream apiStream = apiUrl.openStream();

                                Document doc = null;
                                try{
                                    //xml document
                                    doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apiStream);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }finally{
                                    apiStream.close();
                                }

                                int size = 0;

                                NodeList items = null;
                                NodeList downUrls = null;
                                NodeList fileNames = null;
                                NodeList hitCts = null;
                                NodeList regDts = null;
                                NodeList subjects = null;
                                NodeList writerNms = null;

                                items = doc.getElementsByTagName("item");
                                size = doc.getElementsByTagName("item").getLength();
                                downUrls = doc.getElementsByTagName("downUrl");
                                fileNames = doc.getElementsByTagName("fileName");
                                hitCts = doc.getElementsByTagName("hitCt");
                                regDts = doc.getElementsByTagName("regDt");
                                subjects = doc.getElementsByTagName("subject");
                                writerNms = doc.getElementsByTagName("writerNm");

                                if(size==0){%>
                            <h3>조회한 정보가 없습니다.</h3>
                            <%	}else{ %>
                            <table width="100%">
                                <colgroup>
                                    <col width="50%"/>
                                    <col width="10%"/>
                                    <col width="20%"/>
                                    <col width="30%"/>
                                </colgroup>
                                <%
                                    for(int i=0; i<size; i++){
                                        //파일다운로드
                                        String downUrl = downUrls.item(i).getFirstChild() == null ? "" : downUrls.item(i).getFirstChild().getNodeValue();
                                        //파일명
                                        String fileName = fileNames.item(i).getFirstChild() == null ? "" : fileNames.item(i).getFirstChild().getNodeValue();
                                        //조회수
                                        String hitCt = hitCts.item(i).getFirstChild() == null ? "" : hitCts.item(i).getFirstChild().getNodeValue();
                                        //등록일
                                        String regDt = regDts.item(i).getFirstChild() == null ? "" : regDts.item(i).getFirstChild().getNodeValue();
                                        //제목
                                        String subject = subjects.item(i).getFirstChild() == null ? "" : subjects.item(i).getFirstChild().getNodeValue();
                                        //작성자
                                        String writerNm = writerNms.item(i).getFirstChild() == null ? "" : writerNms.item(i).getFirstChild().getNodeValue();
                                %>
                                <tr>
                                    <td><%=subject%></td>
                                    <td align="center"><%=writerNm%></td>
                                    <td align="center"><%=regDt%></td>
                                    <td align="center"><a href="<%=downUrl%>"><img src="http://www.nongsaro.go.kr/ps/img/icon/icon_file.gif"></a></td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                            <%}}%>
                            <a href="/user/farminfo/WeeklyFarming.do" class="more">더보기</a>
                        </div>

                        <!-- 병해충 발생정보 -->
                        <div class="pest_info">
                            <div class="head">
                                <h2>병해충 발생정보</h2>
                            </div>
                            <%
                                if(true) {
                                    String apiKey = properties.getProperty("NONGSARO_API_KEY");
                                    String serviceName = "dbyhsCccrrncInfo";
                                    String operationName = "dbyhsCccrrncInfoList";
                                    String pageNo = "1";
                                    String numOfRows = "4";

                                    //XML 받을 URL 생성
                                    String parameter = "/"+serviceName+"/"+operationName;
                                    parameter += "?apiKey="+ apiKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows;

                                    //서버와 통신
                                    URL apiUrl = new URL("http://api.nongsaro.go.kr/service"+parameter);
                                    InputStream apiStream = apiUrl.openStream();

                                    Document doc = null;
                                    try{
                                        //xml document
                                        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apiStream);
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }finally{
                                        apiStream.close();
                                    }

                                    int size = 0;

                                    NodeList items = null;
                                    NodeList cntntsSjs = null;
                                    NodeList updusrEsntlNms = null;
                                    NodeList registDts = null;
                                    NodeList cntntsRdcnts = null;
                                    NodeList downFiles = null;
                                    NodeList rtnOrginlFileNms = null;
                                    NodeList cntntsNos = null;

                                    items = doc.getElementsByTagName("item");
                                    size = doc.getElementsByTagName("item").getLength();
                                    cntntsSjs = doc.getElementsByTagName("cntntsSj");
                                    updusrEsntlNms = doc.getElementsByTagName("updusrEsntlNm");
                                    registDts = doc.getElementsByTagName("registDt");
                                    cntntsRdcnts = doc.getElementsByTagName("cntntsRdcnt");
                                    downFiles = doc.getElementsByTagName("downFile");
                                    rtnOrginlFileNms = doc.getElementsByTagName("rtnOrginlFileNm");
                                    cntntsNos = doc.getElementsByTagName("cntntsNo");

                                    if(size==0){%>
                            <h3>조회한 정보가 없습니다.</h3>
                            <%	}else{ %>
                            <table width="100%">
                                <colgroup>
                                    <col width="50%"/>
                                    <col width="10%"/>
                                    <col width="20%"/>
                                    <col width="30%"/>
                                </colgroup>
                                <%
                                    for(int i=0; i<size; i++){
                                        //컨텐츠 제목
                                        String cntntsSj = cntntsSjs.item(i).getFirstChild() == null ? "" : cntntsSjs.item(i).getFirstChild().getNodeValue();
                                        //등록자
                                        String updusrEsntlNm = updusrEsntlNms.item(i).getFirstChild() == null ? "" : updusrEsntlNms.item(i).getFirstChild().getNodeValue();
                                        //등록 일자
                                        String registDt = registDts.item(i).getFirstChild() == null ? "" : registDts.item(i).getFirstChild().getNodeValue();
                                        //조회수
                                        String cntntsRdcnt = cntntsRdcnts.item(i).getFirstChild() == null ? "" : cntntsRdcnts.item(i).getFirstChild().getNodeValue();
                                        //파일경로
                                        String downFile = downFiles.item(i).getFirstChild() == null ? "" : downFiles.item(i).getFirstChild().getNodeValue();
                                        //파일명
                                        String rtnOrginlFileNm = rtnOrginlFileNms.item(i).getFirstChild() == null ? "" : rtnOrginlFileNms.item(i).getFirstChild().getNodeValue();
                                        //컨텐츠 번호
                                        String cntntsNo = cntntsNos.item(i).getFirstChild() == null ? "" : cntntsNos.item(i).getFirstChild().getNodeValue();
                                %>
                                <tr>
                                    <td><%=cntntsSj%></td>
                                    <td align="center"><%=updusrEsntlNm%></td>
                                    <td align="center"><%=registDt%></td>
                                    <td align="center"><a href="<%=downFile%>"><img src="http://www.nongsaro.go.kr/ps/img/icon/icon_file.gif"></a></td>
                                </tr>
                                <%
                                    }
                                %>
                            </table>
                            <%}}%>
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
                            <a href="/user/farminfo/WeatherInformation.do" class="more">더보기</a>
                        </div>

                        <!-- 공지사항 -->
                        <div class="notification">
                            <h2>공지사항</h2>
                            <a href="/user/noti/Announcement.do" class="more">더보기</a>
                        </div>

                        <%--곡물별 가격 현황--%>
                        <div class="price">
                            <h2>곡물별 가격 현황</h2>
                            <p>콩</p>
                            <a href="/user/farminfo/GrainPriceStatus.do" class="more">더보기</a>
                            <canvas id="myChart" width="300" height="170"></canvas>
                            <%--<div id="carouselExample" class="carousel carousel-dark slide">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <canvas id="myChart" width="300" height="170"></canvas>
                                    </div>
                                    <div class="carousel-item">
                                        <canvas id="myChart" width="300" height="170"></canvas>
                                    </div>
                                    <div class="carousel-item">
                                        <canvas id="myChart" width="300" height="170"></canvas>
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
                                    labels: [${date6}, ${date5}, ${date4}, ${date3}, ${date2}, ${date1}],
                                    datasets: [
                                        {
                                            type: 'line',
                                            label: '평균가격',
                                            borderColor: 'rgb(255, 165, 0)',
                                            borderWidth: 2,
                                            fill: false,
                                            data: [${avebfr6}, ${avebfr5}, ${avebfr4}, ${avebfr3}, ${avebfr2}, ${avebfr1}],
                                            yAxisID: 'y',
                                        },
                                        {
                                            type: 'bar',
                                            label: '거래물량',
                                            backgroundColor: 'rgb(70, 162, 142)',
                                            data: [${amt6}, ${amt5}, ${amt4}, ${amt3}, ${amt2}, ${amt1}],
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
                                                        : label + ': ' + context.parsed.y + 'kg'
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