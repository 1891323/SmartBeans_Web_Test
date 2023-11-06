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
        location.href = "Announcement.do";
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
            <li><a href="#"><img src="${pageContext.request.contextPath}/images/egovframework/main/images/ic_sub_navi_home.svg" alt="메인으로"></a></li>
            <li><a href="#">관리자 메뉴</a></li>
            <li><a href="#">게시판 관리</a></li>
            <li><a href="#">공지사항 ${subTitle}</a></li>
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
            <h2>공지사항 ${subTitle}</h2>

            <input type="hidden" id="editmode" name="editmode" value="${editmode}"/>
            <form name="insertNoticeBoardOne" method="post" enctype="multipart/form-data" >

                <div class="inner">
                    <div class="inputWrap">
                        <div class="gridWrap">
                            <div>
                                <span>제목</span>
                                <input type="text" id="bbsTtl" name="bbsTtl" value="${boardVO.noticeTitle}" maxlength="200"/>
                                <input type="hidden" name="registerId" value="${boardVO.noticeWrtr}"/>
                                <input type="hidden" name="bbsSn" value="${boardVO.noticeBoardNo}"/>
                                <input type="hidden" name="nttSeCd" value="${boardVO.noticeBoardSubType}"/>
                            </div>
                            <div>
                                <span>작성자</span>
<%--                                ${sessUserNm}--%>
                            </div>
                            <div>
                                <span>작성일</span>
                                <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </div>
                            <div>
                                <span>내용</span>
                                <textarea class="long pd15" id="bbsCn" name="bbsCn" rows="10" cols="40" maxlength="4000">${boardVO.noticeContents}</textarea>
                            </div>
                            <div class="file-upload">
                                <span>파일첨부</span>
<%--                                <c:import url="/common/fileEdit.do" charEncoding="utf-8">--%>
<%--                                    <c:param name="param_atchFileId" value="${boardVO.fileNo}" />--%>
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
                                <c:if test="${empty boardVO.fileNo}">
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
                        <button type="button" class="dark" onClick="javascript:goEdit()">
                            저장
                        </button>
                    </li>

                </ul>
            </div>
        </div>
            </form>
    </div>
    </div>
</section>