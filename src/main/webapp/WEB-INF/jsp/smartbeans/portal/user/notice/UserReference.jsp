<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
    // init
    document.addEventListener('DOMContentLoaded', function () {
        var savedSearchCondition = sessionStorage.getItem('searchCondition');
        var savedSearchKeyword = sessionStorage.getItem('searchKeyword');

        if (savedSearchCondition && savedSearchKeyword) {
            document.frm.searchCondition.value = savedSearchCondition;
            document.frm.searchKeyword.value = savedSearchKeyword;
        }
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

    function showCategory(category) {
        // 모든 카테고리 내용 숨김
        var categoryContents = document.querySelectorAll('.category-content');
        categoryContents.forEach(function (content) {
            content.classList.remove('active-content');
        });

        document.getElementById(category).classList.add('active-content');

        var catbuttons = document.querySelectorAll('catbutton');
        catbuttons.forEach(function (catbutton) {
            catbutton.classList.remove('active');
        });

        event.target.classList.add('active');
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
    <link rel="stylesheet" href="<c:url value='/'/>css/userreference.css">


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
            </form>
        </div>
        <!--// 검색조건 -->

        <p style="font-size: 20px; margin-top: 15px; text-align: right;">[ 총 게시물 : ${totCnt} 개 ]</p>

        <catbutton onclick="showCategory('all')">전체</catbutton>
        <catbutton onclick="showCategory('promotion')">홍보 컨텐츠</catbutton>
        <catbutton onclick="showCategory('webzine')">웹진</catbutton>
        <catbutton onclick="showCategory('press')">보도자료</catbutton>

        <div class="inner">

            <table>
                <!-- 이하 카테고리 내용은 예시를 위해 작성한 더미 코드임 -->
                <div id="all" class="category-content active-content">
                    <div class="box-container">
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>

                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                    </div>
                </div>

                <div id="promotion" class="category-content">
                    <div class="box-container">
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/promotion_thumb.PNG"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>홍보 컨텐츠</p>
                        </div>
                    </div>
                </div>

                <div id="webzine" class="category-content">
                    <div class="box-container">
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/webzine_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>웹진</p>
                        </div>
                    </div>
                </div>

                <div id="press" class="category-content">
                    <div class="box-container">
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                        <div class="box">
                            <img src="<c:url value="/images/images/press_thumb.jpg"/>"  style= width:100% height="200px" alt="썸네일">
                            <p>보도 자료</p>
                        </div>
                    </div>
                </div>

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
