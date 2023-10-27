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
          <a href="">접속 이력 관리</a>
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
          <a href="">데이터 관리</a>
        </li>
      </ul>
    </div>
  </div>
  <div class="contents">
    <h2>메뉴별 만족도 통계 관리</h2>
    <div class="inner">
      <div class="greyBox">
        <input type="text" class="datepicker calendar"  readonly placeholder="yyyy-mm-dd" autocomplete="off"  onchange="$('#goSearch').click();">
        <input type="text" class="datepicker calendar"  readonly placeholder="yyyy-mm-dd" autocomplete="off"  onchange="$('#goSearch').click();">
        <input type="text"  placeholder="메뉴 검색">
        <button type="button" class="dark">조회</button>
        <button type="button" class="light download"><span>다운로드</span></button>
      </div>
      <h3>메뉴명: 긴급구난 / 기간: 2023-06-30 ~ 2023-12-31</h3>
      <div class="chartBox twin">
        <div class="chartWrap">
          <canvas id="userSurvayChart"></canvas>
        </div>
        <table class="survay">
          <colgroup>
            <col style="width:30%;">
            <col style="">
            <col style="">
          </colgroup>
          <thead>
            <tr>
              <th>만족도</th>
              <th>응답자 수(명)</th>
              <th>비율(%)</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>매우 만족</th>
              <td>70</td>
              <td>43.3</td>
            </tr>
            <tr>
              <th>만족</th>
              <td>80</td>
              <td>49.1</td>
            </tr>
            <tr>
              <th>보통</th>
              <td>11</td>
              <td>6.7</td>
            </tr>
            <tr>
              <th>불만족</th>
              <td>3</td>
              <td>0.6</td>
            </tr>
            <tr>
              <th>매우 불만족</th>
              <td>1</td>
              <td>0.3</td>
            </tr>
            <tr>
              <th>계</th>
              <td>163</td>
              <td>100</td>
            </tr>
          </tbody>
        </table>
      </div>
      <h3>메뉴명: 데이터 통계 / 기간: 2023-06-30 ~ 2023-12-31</h3>
      <div class="chartBox twin">
        <div class="chartWrap">
          <canvas id="userSurvayChart2"></canvas>
        </div>
        <table class="survay">
          <colgroup>
            <col style="width:30%;">
            <col style="">
            <col style="">
          </colgroup>
          <thead>
            <tr>
              <th>만족도</th>
              <th>응답자 수(명)</th>
              <th>비율(%)</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>매우 만족</th>
              <td>70</td>
              <td>43.3</td>
            </tr>
            <tr>
              <th>만족</th>
              <td>80</td>
              <td>49.1</td>
            </tr>
            <tr>
              <th>보통</th>
              <td>11</td>
              <td>6.7</td>
            </tr>
            <tr>
              <th>불만족</th>
              <td>3</td>
              <td>0.6</td>
            </tr>
            <tr>
              <th>매우 불만족</th>
              <td>1</td>
              <td>0.3</td>
            </tr>
            <tr>
              <th>계</th>
              <td>163</td>
              <td>100</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</section>
<script>
$( function() {
   const dataChartArea = document.getElementById('userSurvayChart2').getContext('2d');
	let  dataChart = new Chart(dataChartArea, {
		type: 'bar',
		data: {
			labels: ['매우 만족','만족','보통','불만족','매우불만족'],
			datasets: [{
        data: [70, 80, 10, 3, 1],
				fill: false,
				tension: 0.1,
				pointBorderColor:'#FE6A57',
				pointBorderWidth:1,
				borderColor:'#FE6A57',
				backgroundColor:'#FE6A57'
			}]
		},
		options: {
			maintainAspectRatio:false, //차트 크기 고정 해제 
      categoryPercentage: 1.0,
      barPercentage: 0.3,
			plugins:{
					legend:{display:false}
			},
			scales: {
        x:{
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
        y: {
          beginAtZero: true,
          type: 'linear',
          grace: '100%',
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
            stepSize:20,
            padding:10
          }
        }
      },
    }
	});
 
});
</script>