<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><spring:message code="comSymMnuBmm.bkmkMenuManageList.title"/></title><!-- 바로가기 메뉴관리 -->
    <link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
    <link type="text/css" rel="stylesheet" href="/egovframework-all-in-one/css/egovframework/com/cmm/jqueryui.css">
    <script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
    <script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
    <script>
        function savePrgrmsAuth() {

        }

        window.addEventListener("DOMContentLoaded", (event) => {

        });

        function clickMenu(event) {
            debugger;
            // 임시용 수정모드로 변경
            event.currentTarget.closest('.prgrmTr');
            document.formAddPrgrm.action = "/updatePrgrm.do";
            document.formAddPrgrm.addPrgrmMode.value = "U";
            document.formAddPrgrm.prgrmLevel.value = event.currentTarget.closest('.prgrmTr').querySelector('input[name="prgrmLevel"]');
            document.formAddPrgrm.prgrmNm.value = event.currentTarget.closest('.prgrmTr').querySelector('input[name="prgrmNm"]');
            document.formAddPrgrm.prgrmId.value = event.currentTarget.closest('.prgrmTr').querySelector('input[name="prgrmId"]');
            document.formAddPrgrm.prgrmUrl.value = event.currentTarget.closest('.prgrmTr').querySelector('input[name="prgrmUrl"]');
            document.formAddPrgrm.prgrmOrdr.value = event.currentTarget.closest('.prgrmTr').querySelector('input[name="prgrmOrdr"]');
            document.formAddPrgrm.upPrgrmId.value = event.currentTarget.closest('.prgrmTr').querySelector('input[name="upPrgrmId"]');
            document.formAddPrgrm.rlsYn.value = event.currentTarget.closest('.prgrmTr').querySelector('input[name="rlsYn"]');
        }
    </script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript>
<div class="board">
    <h1>메뉴권한관리</h1>
    <form name="prgrmListForm" action="<c:url value='/sym/mnu/bmm/selectBkmkMenuManageList.do' />" method="post">
        <input type="hidden" name="checkMenuIds" value="">
        <input type="hidden" name="searchCnd" value="0">
        <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">

        <table class="board_list">
            <caption></caption>
            <colgroup>
                <col style="width:10%"/>
                <col/>
                <col style="width:20%"/>
                <col style="width:15%"/>
                <col style="width:15%"/>
                <col style="width:15%"/>
            </colgroup>
            <thead>
            <tr>
                <th scope="col">순번</th>
                <th scope="col">메뉴</th><!-- 메뉴명 -->
                <th scope="col">메뉴ID</th><!-- 메뉴명 -->
                <th scope="col" colspan="3">권한여부</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="result" items="${resultList}" varStatus="status">
                <tr class="prgrmTr">
                    <td>
                        <c:out value='${result.rownum}'/>
                        <input type="hidden" name="prgrmSn" value="${result.prgrmSn}">
                        <input type="hidden" name="prgrmLevel" value="${result.prgrmLevel}">
                        <input type="hidden" name="prgrmNm" value="${result.prgrmNm}">
                        <input type="hidden" name="prgrmId" value="${result.prgrmId}">
                        <input type="hidden" name="prgrmUrl" value="${result.prgrmUrl}">
                        <input type="hidden" name="prgrmOrdr" value="${result.prgrmOrdr}">
                        <input type="hidden" name="upPrgrmId" value="${result.upPrgrmId}">
                    </td>
                    <td>
                        <a href="#" onclick="clickMenu(event)">
                            <span><c:out value="${result.prgrmNm}" escapeXml="false"/></span>
                        </a>
                    </td>
                    <td>
                        <span><c:out value="${result.prgrmId}" escapeXml="false"/></span>
                    </td>
                    <td><input type="checkbox" name="authorUserYn"
                               <c:if test="${result.authorUserYn == 'Y'}">checked</c:if> ><span>사용자</span></td>
                    <td><input type="checkbox" name="authorOperYn"
                               <c:if test="${result.authorOperYn == 'Y'}">checked</c:if> ><span>운영자</span></td>
                    <td><input type="checkbox" name="authorAdmnYn"
                               <c:if test="${result.authorAdmnYn == 'Y'}">checked</c:if> ><span>관리자</span></td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(resultList) == 0}">
                <tr>
                    <td class="lt_text3" nowrap colspan="3"><spring:message code="common.nodata.msg"/></td>
                </tr>
            </c:if>
            </tbody>
        </table>

        <!-- paging navigation -->
        <div class="pagination">
            <ul>
                <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_bkmkInfs"/>
            </ul>
        </div>

        <div>
            <input type="button" value="저장" onclick="savePrgrmsAuth()">
            <input type="button" value="메뉴삭제">
        </div>
    </form>

    <form action="/addPrgrm.do" method="post" name="formAddPrgrm">
        <h1>추메뉴권한관리>추가/수정 페이지</h1>
        <input type="hidden" name="addPrgrmMode" value="I">
        <input type="hidden" name="registerId" value="test">
        <input type="hidden" name="updusrId" value="test">
        <input type="hidden" name="prgrmLevel" value="1">

        <div>
            <span>메뉴명 : </span>
            <input type="text" name="prgrmNm">
        </div>
        <div>
            <span>메뉴ID : </span>
            <input type="text" name="prgrmId">
        </div>
        <div>
            <span>메뉴URL : </span>
            <input type="text" name="prgrmUrl">
        </div>
        <div>
            <span>우선순위 : </span>
            <input type="text" name="prgrmOrdr">
        </div>
        <div>
            <span>상위메뉴ID : </span>
            <input type="text" name="upPrgrmId">
        </div>
        <div>
            <span>공개설정 : </span>
            <input type="text" name="rlsYn">
        </div>
        <div>
            <input type="submit" value="입력">
            <input type="submit" value="수정">
        </div>

    </form>
</div>
</body>
</html>
