<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <li class="active">
          <a href="">게시판 관리</a>
          <ul style="display:block">
            <li class="lnbCnt active">
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
    <h2>공지사항 보기</h2>
    <div class="inner">
      <table class="view" cellspacing="0" style="border-collapse:collapse;">
        <colgroup>
            <col style="width: 15%;">
            <col style="width: 85%;">
        </colgroup>
        <tr>
            <th>NO</th>
            <td><c:out value='${bbs_sn}'/></td>
            <!-- <td>3</td> -->
        </tr>
        <tr>
            <th>제목</th>
            <td><c:out value='${sj}'/></td>
            <!-- <td>데이터허브 포털 소개</td> -->
        </tr>
        <tr>
            <th>작성자</th>
            <td><c:out value='${create_id}'/></td>
            <!-- <td>홍길동</td> -->
        </tr>
        <tr>
            <th>작성일</th>
            <td><c:out value='${reg_dttm}'/></td>
            <!-- <td>2023.03.08</td> -->
        </tr>
        <tr class="long">
            <th>내용</th>
            <td><c:out value='${cn}'/></td>
            <!-- <td>데이터허브 포털은 다양한 키워드로 쉽게 데이터를 검색하고 활용할 수 있는 서비스를 제공합니다.</td> -->
        </tr>
        <tr>
            <th>파일첨부</th>
            <td class="url"><a href="" class="file">데이터허브 포털 이용 방법.Jpg, 데이터 목록.pdf</a></td>
        </tr>
      </table>
      <div class="btnArea">
        <ul>
          <li>
            <button type="button" class="dark">목록</button>
          </li>
          <li>
            <button type="button" class="dark">수정</button>
          </li>
          <li>
            <button type="button" class="dark">삭제</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
</section>