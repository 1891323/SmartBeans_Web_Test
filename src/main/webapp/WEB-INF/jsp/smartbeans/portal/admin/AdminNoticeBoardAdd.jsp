<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:choose>
    <c:when test="${editmode == 'U'}">
        <c:set var="editmode" value="U"/>
        <c:set var="subTitle" value="수정"/>
    </c:when>
    <c:otherwise>
        <c:set var="editmode" value="I"/>
        <c:set var="subTitle" value="신규등록"/>
    </c:otherwise>
</c:choose>

<c:set var="now" value="<%= new java.util.Date() %>"/>

<script>
    function goList() {
        location.href = "selectAdminNoticeBoardList.do";
    }

    function editData() {
        var bbsTtl = document.getElementById('bbsTtl').value;
        var bbsCn = document.getElementById('bbsCn').value;

        if (bbsTtl.trim() === "") {
            alert("글 제목이 비어있습니다.");
            return false;
        } else if (bbsCn.trim() === "") {
            alert("글 내용이 비어있습니다.");
            return false;
        }

        if (!confirm("저장하시겠습니까?")) {
            return false;
        }

        if (document.querySelector('#editmode').value === 'I') {
            document.insertNoticeBoardOne.action = "/admn/bbs/notice/insertAdminNoticeBoard.do";
            document.insertNoticeBoardOne.submit();
        } else if (document.querySelector('#editmode').value === 'U') {
            document.insertNoticeBoardOne.action = "/admn/bbs/notice/updateAdminNoticeBoard.do";
            document.insertNoticeBoardOne.submit();
        }
    }

    document.addEventListener("DOMContentLoaded", function() {
        var input = document.getElementById("bbsTtl");
        input.focus();
    });
</script>

<section id="container">
    <div class="breadCrumb">
        <!-- 빵 부스러기 경로 표시 -->
    </div>

    <!-- Location -->
    <div class="location">
        <ul>
            <li><a href="#"><img src="images/egovframework/main/images/ic_sub_navi_home.svg" alt="메인으로"></a></li>
            <li><a href="#">관리자 메뉴</a></li>
            <li><a href="#">게시판 관리</a></li>
            <li><a href="#">공지사항 상세보기</a></li>
        </ul>
    </div>
    <!--// Location -->

    <div class="wrapper">
        <div class="lnb">
            <!-- 왼쪽 메뉴 구조 (기존에 있던 내용) -->
        </div>

        <div class="contents">
            <h2>공지사항 상세보기</h2>

            <div class="inner">
                <table>
                    <colgroup>
                        <col style="width: 15%;">
                        <col style="width: 85%;">
                    </colgroup>
                    <tr>
                        <th>NO</th>
                        <td><c:out value='${notice.noticeBoardNo}'/></td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td><c:out value='${notice.noticeTitle}'/></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><c:out value='${notice.noticeWrtr}'/></td>
                    </tr>
                    <tr>
                        <th>작성일</th>
                        <td><fmt:formatDate value="${notice.noticeFirstRegistDtm}" pattern="yyyy.MM.dd" /></td>
                    </tr>
                    <tr class="long">
                        <th>내용</th>
                        <td><pre><c:out value='${notice.noticeContents}'/></pre></td>
                    </tr>
                </table>
            </div>

            <div class="btnArea">
                <!-- 버튼 영역 (목록, 수정, 삭제 등의 버튼) -->
            </div>
        </div>
    </div>
</section>
