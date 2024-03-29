<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>

  // init
  document.addEventListener('DOMContentLoaded', function () {
    setDisabled();
  });

  var contextPath = "${pageContext.request.contextPath}";
  var currentPagePrefix = getCurrentPagePrefix(); // 현재 페이지의 prefix 추출

  function getCurrentPagePrefix() {
    var pathArray = window.location.pathname.split('/');
    var lastSegment = pathArray[pathArray.length - 1];
    return lastSegment.split('.')[0]; // 'Announcement.do'에서 'Announcement' 부분만 추출
  }

  function fn_egov_select_noticeList(pageNo) {
    var newUrl = contextPath + "/admin/noti/" + currentPagePrefix + ".do?pageIndex=" + pageNo;
    window.location.href = newUrl;
  }

  function go_search(pageNo) {
    var searchCnd = document.frm.searchCnd.value; // 검색 유형 추출
    var searchWrd = document.frm.searchWrd.value; // 검색어 추출


    var newUrl = contextPath + "/admin/noti/" + currentPagePrefix + ".do?pageIndex=" + pageNo
            + "&searchCnd=" + encodeURIComponent(searchCnd)
            + "&searchWrd=" + encodeURIComponent(searchWrd);
    window.location.href = newUrl;
  }

  function goEdit(select, noticeBoardNo) {
    if (select === "insert") {
      document.frm.action = "/admin/noti/selectAdminEditNoticeBoard.do";
      document.frm.noticeBoardSubType.value = '${noticeBoardSubType}';
      document.frm.method = 'post';
      document.frm.submit();
    } else if (select === "save") {
      let datas = getDummyCheckElements();

      if (datas.length === 0) {
        alert('변경사항이 없습니다.');
        return false;
      }
      if (!confirm("저장하시겠습니까?")) {
        return false;
      }

      $.ajax({
        url: '/admn/bbs/notice/updateAdminNoticeBoardList.do',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(datas),
        success: function (response) {
          if (response.alertMessage) {
            alert(response.alertMessage);
          }
          if (response.status === 'success') {
            alert('저장되었습니다.');
            location.reload();
          }
        },
        error: function (xhr, status, error) {
          console.error('Failed to update the notice board list');
        }
      });
    } else if (select === "detail") {
      var newUrl = contextPath + "/admin/noti/selectAdminDetailNoticeBoard.do?noticeBoardNo=" + noticeBoardNo;
      window.location.href = newUrl;
    }
  }

  function setDisabled() {
    // dummy check 해서 변경사항 없을 시 disabled 처리
    datas = getDummyCheckElements();
    if (datas.length === 0) {
      document.querySelector('#btnAreaSaveButton').classList.add('alpha30');
    } else {
      document.querySelector('#btnAreaSaveButton').classList.remove('alpha30');
    }
  }

  function updateTopFixedStatus(noticeBoardNo, isChecked) {
    var topFixedStatus = isChecked ? 'Y' : 'N';

    $.ajax({
      type: "POST",
      url:  "/admin/noti/updateTopFixedStatus.do",
      data: {
        noticeBoardNo: noticeBoardNo,
        noticeTopFixed: topFixedStatus
      },
      success: function(response) {
        if (response === 1) {
          alert('변경에 성공했습니다.');
        } else {
          alert('변경에 실패했습니다. 잠시 후 다시 시도해 주세요.');
        }
      },
      error: function(xhr, status, error) {
        // 에러 처리
      }
    });
  }

</script>

<c:set var="noticeBoardSubType" value="${noticeBoardSubType}" />

<section id="container">
  <div class="breadCrumb">

  </div>
  <!-- Location -->
  <div class="location">
    <ul>
      <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                           alt="메인으로"  style="position: relative; top: -5px;">
      </a></li>
      <li><a href="#">관리자 메뉴</a></li>
      <li><a href="#">${pageTitle} 관리</a></li>
    </ul>
  </div>
  <!--// Location -->
  <div class="wrapper">
    <div class="lnb">
      <h3>관리자 메뉴</h3>

      <%@ include file="/WEB-INF/jsp/smartbeans/portal/layouts/tiles/attribute/com/LayoutAdminLeft.jsp" %>

    </div>
    <div class="contents">
      <h2 name ="">${pageTitle} 관리 </h2>
      <!-- 검색조건 -->
      <div class="condition">

        <form name="frm" action="/admin/noti/Announcement.do" method="post">
          <input type="hidden" name="noticeBoardSubType" value="${noticeBoardSubType}" />
          <label class="item f_select">
            <select name="searchCondition" id="searchCnd" title="검색조건 선택">
              <option value="0">제목</option>
              <option value="1">내용</option>
              <option value="2">작성자</option>
            </select>
          </label>
          <span class="item f_search">
        <input class="f_input w_500" type="text" name="searchKeyword" title="검색어 입력">
        <button class="btn" type="submit" onclick=" go_search('1'); return false;">조회</button>
    </span>
          <a href=# class="item btn btn_blue_46 w_100" onClick="javascript:goEdit('insert', 'I')">등록</a>
        </form>

      </div>
      <!--// 검색조건 -->

      <p style="font-size: 16px; margin-top: 5px; text-align: right;">[ 총 게시물 : ${totCnt} 개 ]</p>
      <div class="inner">
        <table>
          <colgroup>
            <col style="">
            <col style="">
            <col style="">
            <col style="">
            <%--        <col style="">--%>
          </colgroup>
          <thead>
          <tr>
            <th>NO</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <c:if test="${noticeBoardSubType == 1}"> <!-- 공지사항인 경우에만 상단고정 기능 출력 -->
              <th>상단고정</th>
            </c:if>
          </tr>
          </thead>

          <tbody>
          <!--공지사항 리스트 목록 -->
          <c:forEach items="${boardList}" var="notice">
            <tr>
              <td>${notice.rowNum}</td>
              <td><a href="#" onclick="goEdit('detail', '${notice.noticeBoardNo}')"><c:out value="${notice.noticeTitle}" /></a></td>
              <td>${notice.noticeWrtr}</td>
              <td><fmt:formatDate value="${notice.noticeFirstRegistDtm}" pattern="yyyy.MM.dd" /></td>
              <c:if test="${notice.noticeBoardSubType == 1}"> <!-- 공지사항의 하위 타입이 1인 경우에만 상단고정 체크박스를 표시 -->
                <td>
                  <input type="checkbox" name="checkBtn" id='chkBtn${notice.noticeBoardNo}' ${notice.noticeTopFixed == 'Y' ? 'checked' : ''}
                         onchange="updateTopFixedStatus(${notice.noticeBoardNo}, this.checked)">
                  <label for="chkBtn${notice.noticeBoardNo}">&nbsp;</label>
                </td>
              </c:if>
            </tr>
          </c:forEach>
          <!--공지사항 리스트 목록 -->

          </tbody>

        </table>
        <div>
          <ul class="pagination" id = "pagination">
            <ui:pagination paginationInfo="${paginationInfo}" type="renew" jsFunction="fn_egov_select_noticeList" />

            <%--          <li><button type='button' class='btnPrevend'></button></li>--%>
            <%--          <li><button type='button' class='btnPrev'></button></li>--%>
            <%--          <!--하단 페이징 구간-->--%>
            <%--          <li class="on"><a href="">1</a></li>--%>
            <%--          <li><a href= "">2</a></li>--%>
            <%--          <!--하단 페이징 구간-->--%>
            <%--          <li><button type='button' class='btnNext'></button></li>--%>
            <%--          <li><button  type='button' class='btnNextEnd'></button></li>--%>
          </ul>
        </div>
      </div>
    </div>
  </div>


</section>