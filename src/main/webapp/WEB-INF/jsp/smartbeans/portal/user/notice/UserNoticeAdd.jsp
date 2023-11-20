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

<c:choose>

    <c:when test="${noticeBoardSubType == '1'}">
        <c:set var="Title" value="공지사항"/>
    </c:when>
    <c:when test="${noticeBoardSubType == '2'}">
        <c:set var="Title" value="자료실"/>
    </c:when>
    <c:when test="${noticeBoardSubType == '3'}">
        <c:set var="Title" value="FAQ"/>
    </c:when>
    <c:when test="${noticeBoardSubType == '4'}">
        <c:set var="Title" value="Q&A"/>
    </c:when>
    <c:when test="${noticeBoardSubType == '5'}">
        <c:set var="Title" value="게시판"/>
    </c:when>
    <c:otherwise>
        <c:set var="Title" value="공지사항"/>
    </c:otherwise>

</c:choose>

<c:set var="now" value="<%= new java.util.Date() %>"/>

<script>

    var noticeBoardSubType = Number('${boardVO.noticeBoardSubType}');
    var formModified = false;
    $(document).ready(function() {
        $('input, textarea').on('change', function() {
            formModified = true;
        });

        // 폼 제출시에는 경고가 나타나지 않도록 formModified를 false로 설정.
        $('form').on('submit', function() {
            formModified = false;
        });
    });
    function goList() {
        if (formModified && !confirm('작성하던 게시물이 저장되지 않습니다. 페이지를 벗어나시겠습니까?')) {
            return false;
        }
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

        // 사용자가 확인을 눌렀을 때만 formModified를 false로 설정하여 onbeforeunload 경고를 방지합니다.
        formModified = false;
        location.href = redirectUrl; // 결정된 URL로 이동합니다.
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
            document.insertNoticeBoardOne.action = "/user/noti/insertUserNoticeBoard.do";
            document.insertNoticeBoardOne.submit();
        }
    }

    document.addEventListener("DOMContentLoaded", function() {
        var input = document.getElementById("bbsTtl");
        input.focus();
    });
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

</head>

<body>
    <!-- Location -->
    <div class="location">
        <ul>
            <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg" alt="메인으로"></a></li>
            <li><a href="#">알림마당</a></li>
            <li><a href="#">${subTitle}</a></li>
        </ul>
    </div>
    <!--// Location -->

        <div class="contents">
            <h2>${Title} ${subTitle}</h2>

            <input type="hidden" id="editmode" name="editmode" value="${editmode}"/>
            <form name="insertNoticeBoardOne" method="post" enctype="multipart/form-data" >
                <input type="hidden" name="noticeBoardSubType" value="${noticeBoardSubType}"/>
                <input type="hidden" name="noticeBoardNo" value="${boardVO.noticeBoardNo}"/>


                <div class="inner">
                    <div class="inputWrap">
                        <div class="gridWrap">
                            <div>
                                <span>제목</span>
                                <input type="text" id="bbsTtl" name="noticeTitle" value="${boardVO.noticeTitle}" maxlength="200"/>

                            </div>
                            <div>
                                <span>작성자</span>
                                <%--                                ${sessUserNm}--%>
                            </div>
                            <div>
                                <span>작성일</span>
                                <c:choose>
                                    <c:when test="${editmode == 'U'}">
                                        <c:choose>
                                            <c:when test="${not empty boardVO.noticeLastUpdtDtm}">
                                                <fmt:formatDate value="${boardVO.noticeLastUpdtDtm}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedLastUpdtDate"/>
                                                <input type="text" name="noticeLastUpdtDtm" value="${formattedLastUpdtDate}" readonly="readonly" class="no-border"/>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:formatDate value="${boardVO.noticeFirstRegistDtm}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedFirstRegistDate"/>
                                                <input type="text" name="noticeFirstRegistDtm" value="${formattedFirstRegistDate}" readonly="readonly" class="no-border"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
                                        <input type="text" name="noticeFirstRegistDtm" value="${formattedDate}" readonly="readonly" class="no-border"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div>
                                <span>내용</span>
                                <textarea class="long pd15" id="bbsCn" name="noticeContents" rows="10" cols="40" maxlength="4000">${boardVO.noticeContents}</textarea>
                            </div>
                            <div class="file-upload">
                                <span>파일첨부</span>
                                <%--                                <c:import url="/common/fileEdit.do" charEncoding="utf-8">--%>
                                <%--                                    <c:param name="param_atchFileId" value="${boardVO.atchFileId}" />--%>
                                <%--                                    <c:param name="param_mode" value="default" />--%>
                                <%--                                </c:import>--%>
                                <div id="file_upload_posbl" class="board_attach2">
                                    <!-- 'multiple' 속성 추가 -->
                                    <input name="file_1" id="egovComFileUploader" type="file" multiple />
                                    <div id="egovComFileList"></div>
                                </div>
                                <div id="file_upload_imposbl" class="board_attach2">
                                    <!-- 필요한 경우 추가 콘텐츠를 여기에 배치 -->
                                </div>
                                <c:if test="${empty boardVO.atchFileId}">
                                    <input type="hidden" id="fileListCnt" name="fileListCnt" value="0" />
                                </c:if>
                            </div>
                            <script type="text/javascript">
                                // 기존의 MultiSelector 객체 초기화 코드를 여기에 삽입
                                var listElement = document.getElementById('egovComFileList');
                                var fileSelectElement = document.getElementById('egovComFileUploader');
                                var multi_selector = new MultiSelector(listElement, maxFileNum);
                                var maxFileNum = 3; // 최대 파일 개수 설정
                                var multi_selector = new MultiSelector(document.getElementById('egovComFileList'), maxFileNum);
                                multi_selector.addElement(document.getElementById('egovComFileUploader'));
                            </script>

                        </div>
                    </div>

                    <div class="btnArea">
                        <!-- 버튼 영역 (목록, 수정, 삭제 등의 버튼) -->
                        <ul>
                            <li>
                                <button type="button" class="dark" onClick="goList()">목록
                                </button>
                            </li>
                            <li>
                                <button type="button" class="dark" onClick="javascript:editData()">
                                    저장
                                </button>
                            </li>

                        </ul>
                    </div>
                </div>
            </form>
        </div>

</body>

</html>