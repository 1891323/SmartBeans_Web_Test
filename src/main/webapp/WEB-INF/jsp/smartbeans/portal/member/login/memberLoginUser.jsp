<%--
  Created by IntelliJ IDEA.
  User: Jamin.Kim
  Date: 2023-10-27
  Time: 오후 3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/'/>css/egovframework/com/base.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/egovframework/com/layout.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/egovframework/com/component.css">
    <link rel="stylesheet" href="<c:url value='/'/>css/egovframework/com/page.css">

    <script src="<c:url value='/'/>js/jquery-1.11.2.min.js" type=""></script>
    <script src="<c:url value='/'/>js/ui.js" type=""></script>
    <%-- <link href="<c:url value='/'/>css_old/default.css" rel="stylesheet" type="text/css" > --%>

    <title>로그인</title>
    <script type="text/javascript">


    // 로그인
    function actionLogin() {

        if (document.loginForm.userId.value =="") {                                         // 아이디를 입력하지 않았을 때
            alert("아이디를 입력하세요");
        } else if (document.loginForm.password.value =="") {                               // 비밀번호를 입력하지 않았을 때
            alert("비밀번호를 입력하세요");
        } else {                                                                           // 아이디와 비밀번호가 모두 입력되었을 때
            document.loginForm.action="<c:url value='/member/login/actionLogin'/>";
            // document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
            //document.loginForm.action="<c:url value='/j_spring_security_check'/>";
            document.loginForm.submit();
        }
    }



    // 아이디/비밀번호 찾기 화면으로 가기
    function goFindId() {
        document.defaultForm.action="<c:url value='/uat/uia/egovIdPasswordSearch.do'/>";
        document.defaultForm.submit();
    }


    // 회원가입 화면으로 가기
    function goRegiUsr() {
        // 사용자 유형 가져오기
        var userSe = document.loginForm.userSe.value;
        // 일반회원
        if (userSe == "GNR") {
            // 회원가입 페이지로 이동
            <%--document.loginForm.action="<c:url value='/member/login/memberActionSecurityLogin'/>";--%>
            // document.loginForm.submit();
        }else{
            alert("일반회원 가입만 허용됩니다.");
        }
    }


    // 쿠키 설정
    function setCookie (name, value, expires) {         // 이름, 값, 경로, 만료 시간
        document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
    }

    // 쿠키값 가져오기
    function getCookie(Name) {
        var search = Name + "="
        if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
            offset = document.cookie.indexOf(search)
            if (offset != -1) { // 쿠키가 존재하면
                offset += search.length
                // set index of beginning of value
                end = document.cookie.indexOf(";", offset)
                // 쿠키 값의 마지막 위치 인덱스 번호 설정
                if (end == -1)
                    end = document.cookie.length
                return unescape(document.cookie.substring(offset, end))
            }
        }
        return "";
    }


    // 사용자 아이디를 쿠키에 저장
    function saveid(form) {
        var expdate = new Date();
        // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
        if (form.checkId.checked)
            expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
        else    // 체크박스가 선택되지 않았다면
            expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
        setCookie("saveid", form.userId.value, expdate);
    }


    // 저장된 아이디 쿠키 가져오기
    function getid(form) {
        form.checkId.checked = ((form.userId.value = getCookie("saveid")) != "");
    }


    // 초기화 함수
    function fnInit() {
        var message = document.loginForm.message.value;
        if (message != "") {
            alert(message);
        }
        getid(document.loginForm);
        fnLoginTypeSelect("typeGnr");
    }


    function fnLoginTypeSelect(objName){

        document.getElementById("typeGnr").className = "";
        document.getElementById("typeUsr").className = "";

        document.getElementById(objName).className = "on";

        if(objName === "typeGnr"){ //일반회원
            document.loginForm.userSe.value = "GNR";
        }else if(objName === "typeUsr"){	//업무사용자
            document.loginForm.userSe.value = "USR";
        }

    }

    //-->
</script>
</head>
<body onLoad="fnInit();">

<!-- skip navigation -->
<a href="#contents" class="skip_navi">본문 바로가기</a>

<div class="wrap">
    <!-- header start -->

<%--    <c:import url="/sym/mms/EgovHeader.do" />--%>

    <!-- //header end -->

    <div class="container">
        <div class="p_login">
            <h1>로그인</h1>


            <div class="loginbox">

                <form name="loginForm" action ="<c:url value='/member/login/memberActionSecurityLogin'/>" method="post">
                    <div style="visibility:hidden;display:none;">
                        <input name="iptSubmit1" type="submit" value="전송" title="전송" />
                    </div>
                    <input type="hidden" name="message" value="<c:out value='${message}'/>" />

                    <fieldset>
                        <legend>로그인</legend>

                        <dl>

                            <dt><label>아이디</label></dt>
                            <dd><input type="text" name="userId" id="userId" title="아이디" maxlength="10"/></dd>
                        </dl>

                        <dl>

                            <dt><label>비밀번호</label></dt>
                            <dd>
                                <input type="password" name="password" id="password" title="비밀번호" onKeyDown="javascript:if (event.keyCode == 13) { actionLogin(); }"/>
                                <label for="chk" class="f_chk">
                                    <input type="checkbox" name="checkId" id="chk" title="ID 저장" onClick="javascript:saveid(document.loginForm);" />
                                    <span>ID 저장</span>
                                </label>
                            </dd>
                        </dl>

                        <div class="btn_a">

                            <a class="btn" onClick="actionLogin()">로그인</a>
                            <a class="btn" onClick="goRegiUsr();">회원가입</a>
                        </div>
                    </fieldset>

                    <input name="userSe" type="hidden" value="GNR"/>
                    <input name="j_username" type="hidden"/>

                </form>
            </div>
        </div>
    </div>

    <!-- footer 시작 -->

<%--    <c:import url="/sym/mms/EgovFooter.do" />--%>
    <!-- //footer 끝 -->
</div>

</body>

</html>
