<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="noticeBoardSubType" value="${noticeBoardSubType}" />
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>

    var contextPath = "${pageContext.request.contextPath}";

    function goList() {
        var noticeBoardSubType = parseInt(document.bbsDetailForm.noticeBoardSubType.value, 10);
        var redirectUrl = "/user/noti/";
        switch (noticeBoardSubType) {
            case 1:
                redirectUrl += "Announcement.do";
                break;
            case 2:
                redirectUrl += "Reference.do";
                break;
            case 3:
                redirectUrl += "FAQ.do";
                break;
            case 4:
                redirectUrl += "QnA.do";
                break;
            case 5:
                redirectUrl += "Board.do";
                break;
            default:
                redirectUrl += "Announcement.do"; // 기본값 혹은 예외 처리
        }
        location.href = redirectUrl;
    }

    refreshCommentList();

    function insertComment() {
        var noticeBoardNo = "${boardVO.noticeBoardNo}"; // boardVO.noticeBoardNo를 사용
        var cmntCn = document.getElementById('cmntCn').value;

        // Axios를 사용하여 댓글 삽입하는 것으로 바꾸기
        axios.get({
            type: 'POST',
            url: '/user/noti/insertUserComment.do',
            data: {
                noticeBoardNo: noticeBoardNo,
                cmntCn: cmntCn,
                cmntFirstRegistDtm: '',
            }
        })
            .then(function(response) {
                // 댓글 삽입 성공 후 댓글 목록을 갱신
                alert('댓글 삽입 성공.');
                refreshCommentList();
            })
            .catch(function(error) {
                alert('댓글 삽입에 실패했습니다.');
            });
    }

    function refreshCommentList() {
        var noticeBoardNo = "${boardVO.noticeBoardNo}";

        // Axios를 사용하여 업데이트된 댓글 목록을 가져옴
        axios.get('/user/noti/selectCmntList.do', {
            params: {
                noticeBoardNo: noticeBoardNo
            }
        })
            .then(function(response) {
                console.log(response.cmntList)
                document.getElementById('commentList').cmntList = response.cmntList;
            })
            .catch(function(error) {
                alert('댓글 목록을 가져오지 못했습니다.');
                console.error(error);
            });
    }

</script>

<!DOCTYPE html>
<html>

<head>
    <title></title>
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
                                 alt="메인으로"  style="position: relative; top: -5px;">
            </a></li>
            <li><a href="#">알림마당</a></li>
            <li><a href="#">${boardType} 상세보기</a></li>
        </ul>
    </div>

        <div class="contents">
            <h2>${boardType} 상세보기</h2>

            <box class="inner">

                <form name="bbsDetailForm" action="" method="post">

                    <input type="hidden" name="noticeBoardNo" value="${boardVO.noticeBoardNo}">
                    <input type="hidden" name="noticeBoardSubType" value="${boardVO.noticeBoardSubType}">

                    <table class="view" cellspacing="0" style="border-collapse:collapse;">
                        <colgroup>
                            <col style="width: 15%;">
                            <col style="width: 85%;">
                        </colgroup>
                        <tr>
                            <th>NO</th>
                            <td><c:out value='${boardVO.noticeBoardNo}'/></td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><c:out value='${boardVO.noticeTitle}'/></td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td><c:out value='${boardVO.noticeWrtr}'/></td>
                        </tr>
                        <tr>
                            <th>작성일</th>
                            <td>
                                <fmt:formatDate value="${boardVO.noticeFirstRegistDtm}" pattern="yyyy.MM.dd HH:mm" />
                            </td>
                        </tr>
                        <tr class="long">
                            <th>내용</th>
                            <td><pre><c:out value='${boardVO.noticeContents}'/></pre></td>
                        </tr>
                        <tr>
                            <th>파일첨부</th>
                            <td class="url">
                                <%--<c:import url="/common/fileView.do" charEncoding="utf-8">
                                <c:param name="param_atchFileId" value="${boardVO.atchFileId}" />
                                <c:param name="param_mode" value="default" />
                                </c:import>--%>
                            </td>
                        </tr>

                        <tbody>
                        <!--알림마당 리스트 목록 -->
                        <c:forEach items="${boardList}" var="notice">
                            <tr>
                                <td>${notice.rowNum}</td>
                                <td><a href="#" onclick="goEdit('detail', '${notice.noticeBoardNo}')"><c:out value="${notice.noticeTitle}" /></a></td>
                            </tr>
                        </c:forEach>
                        <!--알림마당 리스트 목록 -->

                        <!-- 댓글 목록 -->
                        <c:if test="${boardVO.noticeBoardSubType == 1 || boardVO.noticeBoardSubType == 4}">
                                <table id="commentList">
                                    <colgroup>
                                        <col style="">
                                        <col style="">
                                        <col style="">
                                    </colgroup>

                                    <thead>
                                        <tr>
                                            <th>작성자</th>
                                            <th>내용</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${data}" var="cmnt">
                                            <tr>
                                                <td><c:out value='${cmnt.cmntCn}'/></td>
                                                <td>${selectCmntList.cmntCn}</td>
                                                <td>${cmntVO.cmntCn}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                        </c:if>

                        <!-- 댓글 입력 폼 -->
                        <c:if test="${boardVO.noticeBoardSubType == 4}">
                            <h3>댓글 작성</h3>
                            <form id="commentForm">
                                <input type="hidden" id="noticeBoardNo" value="${boardVO.noticeBoardNo}" />
                                <label>
                                    <textarea id="cmntCn" rows="4" cols="50" placeholder="DB 삽입 성공 / 출력 문제 해결하기"></textarea>
                                </label>
                                <button type="button" onclick="insertComment()">댓글 작성</button>
                            </form>
                        </c:if>

                        </tbody>
                    </table>

                    <div class="btnArea">
                        <ul>
                            <li>
                                <button type="button" class="dark" onClick="goList()">목록
                                </button>
                            </li>
                        </ul>
                    </div>

                </form>
            </box>

            </div>

</body>

</html>