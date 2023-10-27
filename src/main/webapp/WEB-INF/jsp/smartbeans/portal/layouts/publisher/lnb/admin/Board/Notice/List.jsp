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
    <h2>공지사항 목록</h2>
    <div class="inner">
      <table>
      <colgroup>
        <col style="">
        <col style="">
        <col style="">
        <col style="">
        <col style="">
      </colgroup>
        <thead>
          <tr>
            <th>NO</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>상단고정</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>10</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn'><label for="chkBtn">&nbsp;</label></td>
          </tr>
          <tr>
            <td>9</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn1'><label for="chkBtn1">&nbsp;</label></td>
          </tr>
          <tr>
            <td>8</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn2'><label for="chkBtn2">&nbsp;</label></td>
          </tr>
          <tr>
            <td>7</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn3'><label for="chkBtn3">&nbsp;</label></td>
          </tr>
          <tr>
            <td>6</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn4'><label for="chkBtn4">&nbsp;</label></td>
          </tr>
          <tr>
            <td>5</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn5'><label for="chkBtn5">&nbsp;</label></td>
          </tr>
          <tr>
            <td>4</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn6'><label for="chkBtn6">&nbsp;</label></td>
          </tr>
          <tr>
            <td>3</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn7'><label for="chkBtn7">&nbsp;</label></td>
          </tr>
          <tr>
            <td>2</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn8'><label for="chkBtn8">&nbsp;</label></td>
          </tr>
          <tr>
            <td>1</td>
            <td>데이터허브 포털 소개</td>
            <td>관리자</td>
            <td>2023.03.08</td>
            <td><input type="checkbox" name="checkBtn" id='chkBtn9'><label for="chkBtn9">&nbsp;</label></td>
          </tr>
        </tbody>
      </table>
      <div>
        <ul class="pagination" id = "pagination">
          <li><button type='button' class='btnPrevend'></button></li>
          <li><button type='button' class='btnPrev'></button></li>
          <li class="on"><a href="">1</a></li>
          <li><a href= "">2</a></li>
          <li><a href= "">3</a></li>
          <li><a href= "">4</a></li>
          <li><a href= "">5</a></li>
          <li><button type='button' class='btnNext'></button></li>
          <li><button  type='button' class='btnNextEnd'></button></li>
        </ul>
      </div>
      <div class="btnArea">
        <ul>
          <li>
            <button type="button" class="dark">저장</button>
          </li>
          <li>
            <button type="button" class="dark">신규등록</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
</section>