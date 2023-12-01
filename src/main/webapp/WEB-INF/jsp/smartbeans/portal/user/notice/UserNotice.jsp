<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    // init
    document.addEventListener('DOMContentLoaded', function () {
        var savedSearchCondition = sessionStorage.getItem('searchCondition');
        var savedSearchKeyword = sessionStorage.getItem('searchKeyword');

        if (savedSearchCondition && savedSearchKeyword) {
            document.frm.searchCondition.value = savedSearchCondition;
            document.frm.searchKeyword.value = savedSearchKeyword;
        }
        setDisabled();
    });

    var contextPath = "${pageContext.request.contextPath}";

    function fn_egov_select_noticeList(pageNo) {
        var searchCondition = document.frm.searchCondition.value; // 검색 유형 추출
        var searchKeyword = document.frm.searchKeyword.value; // 검색어 추출

        sessionStorage.setItem('searchCondition', searchCondition);
        sessionStorage.setItem('searchKeyword', searchKeyword);

        var noticeBoardSubType = <c:out value="${noticeBoardSubType}" />;
        var pageType = "";

        switch (noticeBoardSubType) {
            case 1:
                pageType = "Announcement";
                break;
            case 2:
                pageType = "Reference";
                break;
            case 3:
                pageType = "FAQ";
                break;
            case 4:
                pageType = "QnA";
                break;
            case 5:
                pageType = "Board";
                break;
            default:
                pageType = "Announcement";
        }

        var newUrl = contextPath + "/user/noti/" + pageType + ".do?pageIndex=" + pageNo
            + "&searchCondition=" + encodeURIComponent(searchCondition)
            + "&searchKeyword=" + encodeURIComponent(searchKeyword);
        window.location.href = newUrl;
    }

    function goDeatil(select, noticeBoardNo) {
        if (select === "detail") {
            var newUrl = contextPath + "/user/noti/selectUserDetailNoticeBoard.do?noticeBoardNo=" + noticeBoardNo;
            window.location.href = newUrl;
        }
    }

    function goEdit(select, noticeBoardNo) {
        if (select === "insert") {
            document.frm.action = "/user/noti/selectUserEditNoticeBoard.do";
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
                url: '/user/notice/updateUserNoticeBoardList.do',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(datas),
                success: function (response) {
                    if (response.alertMessage) {
                        alert(response.alertMessage);
                        var row = $('tr').has('td:contains("' + noticeBoardNo + '")');
                        row.prependTo('tbody');
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
            var newUrl = contextPath + "/user/noti/selectUserDetailNoticeBoard.do?noticeBoardNo=" + noticeBoardNo;
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

    function userupdateTopFixedStatus(noticeBoardNo, isChecked) {
        var topFixedStatus = isChecked ? 'Y' : 'N';

        $.ajax({
            type: "POST",
            url:  "/admin/noti/userupdateTopFixedStatus.do",
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
            },
        });
    }

    $(document).ready(function () {
        // 체크박스가 체크된 행을 테이블의 맨 위로 이동
        $('tbody tr:has(input:checked)').prependTo('tbody');
    });

</script>

<c:set var="noticeBoardSubType" value="${noticeBoardSubType}" />

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/page.css">

</head>

<body>

<div class="location">
    <ul>
        <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                             alt="메인으로"  style="position: relative; top: -1px;">
        </a></li>
        <li><a href="#">알림마당</a></li>
        <li><a href="#">${pageTitle}</a></li>
    </ul>
</div>

    <div class="contents">
                <h2 name ="">${pageTitle} </h2>
            <!-- 검색조건 -->
        <div class="condition">
            <form name="frm" action="/user/noti/Announcement.do" method="post">
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
                    <button class="btn" type="submit" onclick="fn_egov_select_noticeList('1'); return false;">조회</button>

                </span>

                <c:if test="${noticeBoardSubType == 4}"> <!-- Q&A일 경우에만 사용자가 글 등록 가능 -->
                <a href=# class="item btn btn_blue_46" onClick="javascript:goEdit('insert', 'I')">등록</a>
                </c:if>

            </form>
        </div>
        <!--// 검색조건 -->

        <p style="font-size: 20px; margin-top: 15px; text-align: right;">[ 총 게시물 : ${totCnt} 개 ]</p>
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
                        <!-- 사용자페이지에서는 안보이도록 style display를 none 으로 처리 -->
                        <c:if test="${notice.noticeBoardSubType == 1}"> <!-- 공지사항의 하위 타입이 1인 경우에만 상단고정 체크박스를 표시 -->
                            <td style="display: none;">
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
                <ul class="pagination" id="pagination">
                    <li><button type='button' class='btnPrevend'></button></li>
                    <li><button type='button' class='btnPrev'></button></li>
                    <ui:pagination paginationInfo="${paginationInfo}" type="renew" jsFunction="fn_egov_select_noticeList" />
                    <li><button type='button' class='btnNext'></button>
                    <li><button  type='button' class='btnNextEnd'></button>
                </ul>
            </div>
        </div>
    </div>
</body>

</html>
