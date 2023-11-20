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

</script>

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

<section id="container">

        <div class="contents">
            <h2>${boardType} 상세보기</h2>

            <div class="inner">
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
                        </tr>
                        <tr class="long">
                            <th>내용</th>
                            <td><pre><c:out value='${boardVO.noticeContents}'/></pre></td>
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
                        </ul>
                    </div>
                </form>
            </div>

    </div>
</section>
</body>