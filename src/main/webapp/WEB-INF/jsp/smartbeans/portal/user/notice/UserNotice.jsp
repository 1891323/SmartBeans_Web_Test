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

    function fn_egov_select_noticeList(pageNo) {
        var newUrl = contextPath + "/user/noti/Announcement.do?pageIndex=" + pageNo;
        window.location.href = newUrl;
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
</script>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/page.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

    <title>공지 사항</title>
</head>

<body>

<div class="location">
    <ul>
        <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg"
                             alt="메인으로"  style="position: relative; top: -1px;">
        </a></li>
        <li><a href="#">알림마당</a></li>
        <li><a href="#">공지사항</a></li>
    </ul>
</div>

    <div class="contents">
                <h2 name ="">공지사항 </h2>
            <!-- 검색조건 -->
        <div class="condition">
            <form name="frm" action="/portal/user/notice/noticeList.do" method="post">
                <input type="hidden" name="noticeBoardSubType" value="${noticeBoardSubType}" />
                <label class="item f_select">
                    <select name="searchCnd" id="searchCnd" title="검색조건 선택">
                        <option value="0">제목</option>
                        <option value="1">내용</option>
                        <option value="2">작성자</option>
                    </select>
                </label>
                <span class="item f_search">
                    <input class="f_input w_500" type="text" name="searchWrd" title="검색어 입력">
                    <button class="btn" type="submit" onclick="fn_egov_select_noticeList('1'); return false;">조회</button>
                 </span>
            </form>
        </div>

            <!--// 검색조건 -->
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

            </table>

            <tbody>
            <!--공지사항 리스트 목록 -->
            <c:forEach items="${boardList}" var="notice">
                <tr>
                    <td>${notice.rowNum}</td>
                    <td> <a href="#" ${notice.noticeBoardNo}> <c:out value="${notice.noticeTitle}" /></a></td>
                    <td>${notice.noticeWrtr}</td>
                    <td><fmt:formatDate value="${notice.noticeFirstRegistDtm}" pattern="yyyy.MM.dd" /></td>
                </tr>
            </c:forEach>
            <!--공지사항 리스트 목록 -->
            </tbody>

        </div>
    </div>
</body>

</html>
