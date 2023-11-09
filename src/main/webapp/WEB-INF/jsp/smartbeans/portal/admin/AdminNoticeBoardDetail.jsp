<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
  var contextPath = "${pageContext.request.contextPath}";

  function goList() {
    location.href = "Announcement.do";
  }


  function goEdit() {
    document.bbsDetailForm.action = "/admn/bbs/notice/selectAdminEditNoticeBoard.do";
    document.bbsDetailForm.submit();
  }

  function goDelete() {
    if (confirm("삭제하시겠습니까?") === false) {
      return;
    }

    document.bbsDetailForm.action = "/admn/bbs/notice/deleteAdminNoticeBoard.do";
    document.bbsDetailForm.submit();
  }
</script>

<section id="container">
  <div class="breadCrumb">
    <!-- 빵 부스러기 경로 표시 -->
  </div>

  <!-- Location -->
  <div class="location">
    <ul>
      <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                           alt="메인으로"  style="position: relative; top: -5px;">
      </a></li>
      <li><a href="#">관리자 메뉴</a></li>
      <li><a href="#">게시판 관리</a></li>
      <li><a href="#">${boardType} 상세보기</a></li>
    </ul>
  </div>
  <!--// Location -->

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
                <a href="">자료실 관리</a>
              </li>
              <li class="lnbCnt">
                <a href="">FAQ 관리</a>
              </li>
              <li class="lnbCnt">
                <a href="">QnA 관리</a>
              </li>
              <li class="lnbCnt">
                <a href="">게시판 관리</a>
              </li>
              <li class="lnbCnt">
                <a href="">콩 재배 메뉴얼 관리</a>
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
      <h2>${boardType} 상세보기</h2>

      <div class="inner">
        <form name="bbsDetailForm" action="" method="post">
          <input type="hidden" name="bbsSn" value="${boardVO.noticeBoardNo}">
          <input type="hidden" name="editmode" value="U">
          <table class="view" cellspacing="0" style="border-collapse:collapse;">
            <colgroup>
              <col style="width: 15%;">
              <col style="width: 85%;">
            </colgroup>
            <tr>
              <th>NO</th>
              <td><c:out value='${boardVO.noticeBoardNo}'/></td>
              <!-- <td>3</td> -->
            </tr>
            <tr>
              <th>제목</th>
              <td><c:out value='${boardVO.noticeTitle}'/></td>
              <!-- <td>데이터허브 포털 소개</td> -->
            </tr>
            <tr>
              <th>작성자</th>
              <td><c:out value='${boardVO.noticeWrtr}'/></td>
              <!-- <td>홍길동</td> -->
            </tr>
            <tr>
              <th>작성일</th>
              <td>
                <fmt:formatDate value="${boardVO.noticeFirstRegistDtm}" pattern="yyyy.MM.dd HH:mm" />
              </td>
              <!-- <td>2023.03.08</td> -->
            </tr>
            <tr class="long">
              <th>내용</th>
              <td><pre><c:out value='${boardVO.noticeContents}'/></pre></td>
              <!-- <td>데이터허브 포털은 다양한 키워드로 쉽게 데이터를 검색하고 활용할 수 있는 서비스를 제공합니다.</td> -->
            </tr>
            <tr>
              <th>파일첨부</th>
              <td class="url">
<%--                          <c:import url="/common/fileView.do" charEncoding="utf-8">--%>
<%--                            <c:param name="param_atchFileId" value="${boardVO.atchFileId}" />--%>
<%--                            <c:param name="param_mode" value="default" />--%>
<%--                          </c:import>--%>
              </td>
            </tr>
          </table>
          <div class="btnArea">
            <ul>
              <li>
                <button type="button" class="dark" onClick="goList()">목록
                </button>
              </li>
              <li>
                <button type="button" class="dark" onClick="javascript:goEdit()">
                  수정
                </button>
              </li>
              <li>
                <button type="button" class="dark" onClick="javascript:goDelete()">삭제</button>
              </li>
            </ul>
          </div>
        </form>
      </div>

    </div>
  </div>
</section>
