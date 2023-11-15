<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
  var contextPath = "${pageContext.request.contextPath}";

  function goList() {
    var noticeBoardSubType = parseInt(document.bbsDetailForm.noticeBoardSubType.value, 10);
    var redirectUrl = "/admin/noti/";
    switch (noticeBoardSubType) {
      case 1:
        redirectUrl += "Announcement.do";
        break;
      case 5:
        redirectUrl += "Board.do";
        break;
      case 4:
        redirectUrl += "QnA.do";
        break;
      default:
        redirectUrl += "Announcement.do"; // 기본값 혹은 예외 처리
    }
    location.href = redirectUrl;
  }


  function goEdit() {
    document.bbsDetailForm.action = "/admin/noti/selectAdminEditNoticeBoard.do";
    document.bbsDetailForm.submit();
  }

  function goDelete() {
    if (confirm("삭제하시겠습니까?") === false) {
      return;
    }

    document.bbsDetailForm.action = "/admin/noti/deleteAdminNoticeBoard.do";
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
      <%@ include file="/WEB-INF/jsp/smartbeans/portal/layouts/tiles/attribute/com/LayoutAdminLeft.jsp" %>
    </div>

    <div class="contents">
      <h2>${boardType} 상세보기</h2>

      <div class="inner">
        <form name="bbsDetailForm" action="" method="post">
          <input type="hidden" name="noticeBoardNo" value="${boardVO.noticeBoardNo}">
          <input type="hidden" name="editmode" value="U">
          <input type="hidden" name="noticeBoardSubType" value="${boardVO.noticeBoardSubType}">
          <input type="hidden" name="atchFileId" value="${boardVO.atchFileId}">

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

          <div class="comment">

            <div>

              <form method="post" action="/reply/write">

                <p>
                  <label>댓글 작성자</label> <input type="text" name="writer">
                </p>
                <p>
                  <textarea rows="5" cols="50" name="content"></textarea>
                </p>
                <p>
                  <input type="hidden" name="noticeBoardNo" value="${boardVO.noticeBoardNo}">
                  <button type="submit">댓글 작성</button>
                </p>
              </form>

            </div>

          </div>
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
