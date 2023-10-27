<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="container">
<div class="breadCrumb">
  <ul>
    <li><a href="#"><img src="/images/images/ic_sub_navi_home.svg" alt="메인으로"></a></li>
    <li><a href="#">관리자 메뉴</a></li>
    <li><a href="#">게시판 관리</a></li>
    <li><a href="#">공지사항 관리</a></li>
  </ul>
</div>
<div class="wrapper">
  <div class="lnb">
    <h3>관리자 메뉴</h3>
    <div class="lnbList">
      <ul>
        <li>
          <a href="">게시판 관리</a>
          <ul>
            <li class="lnbCnt">
              <a href="">공지사항 관리</a>
            </li>
            <li class="lnbCnt">
              <a href="">문의하기 관리</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="">사용자 관리</a>
          <ul>
            <li class="lnbCnt">
              <a href="">권한 관리</a>
            </li>
            <li class="lnbCnt">
              <a href="">메뉴 관리</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="">만족도 통계 관리</a>
          <ul>
            <li class="lnbCnt">
              <a href="">사용자별</a>
            </li>
            <li class="lnbCnt">
              <a href="">메뉴별</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="">접속 이력 관리</a>
          <ul style="display:block;">
            <li class="lnbCnt">
              <a href="">사용자별</a>
            </li>
            <li class="lnbCnt active">
              <a href="">메뉴별</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="">데이터 관리</a>
        </li>
      </ul>
    </div>
  </div>
  <div class="contents">
    <h2>메뉴별 접속 이력관리</h2>
    <div class="inner">
      <div class="greyBox">
        <input type="text" class="datepicker calendar"  readonly placeholder="yyyy-mm-dd" autocomplete="off"  onchange="$('#goSearch').click();">
        <input type="text" class="datepicker calendar"  readonly placeholder="yyyy-mm-dd" autocomplete="off"  onchange="$('#goSearch').click();">
        <button type="button" class="dark">조회</button>
        <button type="button" class="light download"><span>다운로드</span></button>
      </div>
      <h3>메뉴별 접속 로그 통계</h3>
      <div class="chartBox">
        <div class="chartWrap access">
          <canvas id="menuAccessChart"></canvas>
        </div>
      </div>
    </div>
    
  </div>
</div>
</section>
<script>
$( function() {
   const menuAccessArea = document.getElementById('menuAccessChart').getContext('2d');
    let  userMChart = new Chart(menuAccessArea, {
    type: 'bar',
    data: {
      labels: ['긴급구난','데이터 통계','데이터 현황','공지사항','포털 소개','안전2.0','데이터 시각화'],
      datasets: [{
        data: [284,271,234,181,148,56,45],
        fill: false,
        tension: 0.1,
        pointBorderColor:'#754EFF',
        pointBorderWidth:1,
        borderColor:'#754EFF',
        backgroundColor:'#754EFF'
      }]
    },
    options: {
      maintainAspectRatio:false, //차트 크기 고정 해제 
      indexAxis: 'y',
      categoryPercentage: 1.0,
      barPercentage: 0.4,
      plugins:{
          legend:{display:false}
      },
      scales: {
        y:{
          grace: '100%',
          grid: {
            display: false
          },
          ticks:{
            color:'#101828',
            font: {
              size: 11,
              family: "pretendard",
              weight: 500,
            },
            padding:10
          }
        },
        x: {
          beginAtZero: true,
          type: 'linear',
          grace: '0%',
          grid:{
            drawTicks: false,
            drawBorder: false,
            borderDash: [3, 2],
          },
          ticks:{
            color:'#98a2b3',
            font: {
              size: 11,
              family: "pretendard",
              weight: 300,
            },
            stepSize:100,
            padding:0
          }
        }
      },
    }
  }); 
});
</script>