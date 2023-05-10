<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="container">
<div class="breadCrumb">
  <ul>
    <li><a href="#"><img src="images/egovframework/main/images/ic_sub_navi_home.svg" alt="메인으로"></a></li>
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
          <ul style="display:block;">
            <li class="lnbCnt">
              <a href="">권한 관리</a>
            </li>
            <li class="lnbCnt active">
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
    <h2>메뉴 추가</h2>
    <div class="inner">
      <div class="inputWrap">
        <div class="gridWrap">
          <div>
            <span>메뉴 명</span>
            <input type="text">
          </div>
          <div>
            <span>메뉴 URL</span>
            <input type="text">
          </div>
          <div>
            <span>우선순위</span>
            <input type="text">
          </div>
          <div>
            <span>상위메뉴ID</span>
            <input type="text">
          </div>
          <div>
            <span>공개 설정</span>
            <div class="btnWrap">
              <div class="radioBtnWrap" >
                <input type="radio" name="user" id="Radio1" value="Y">
                <label for="Radio1" id="Btn1" tabindex="9">&nbsp;</label>
                <label for="Radio1">공개</label>
              </div> 
              <div class="radioBtnWrap" >
                <input type="radio" name="user" id="Radio2" value="N">
                <label for="Radio2" id="Btn2" tabindex="10">&nbsp;</label>
                <label for="Radio2">비공개</label>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="btnArea">
        <ul>
          <li>
            <button type="button" class="dark">취소</button>
          </li>
          <li>
            <button type="button" class="dark">저장</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
</section>