<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 네이버시스템
  Date: 2023-05-24
  Time: 오후 3:08
  To change this template use File | Settings | File Templates.
--%>
<script>

  document.addEventListener('DOMContentLoaded', function () {
    // 이벤트 주입
    var btnDgstfnForClicks = document.querySelectorAll('.btnDgstfnForClick');
    for (var i = 0; i < btnDgstfnForClicks.length; i++) {
      btnDgstfnForClicks[i].addEventListener('click', function (e) {
        e.preventDefault();
        var dgstfnCd = e.currentTarget.dataset.dgstfnscr;
        setDgstfnFields(dgstfnCd);
      });
    }

    var dgstfnCnInput = document.getElementById("dgstfnCn");
    var dgstfnCnButton = document.querySelector(".dark");

    if (dgstfnCnInput) {
      dgstfnCnInput.addEventListener("keydown", function (event) {
        if (event.keyCode === 13) {
          event.preventDefault();
          if (dgstfnCnButton) {
            dgstfnCnButton.click();
          } else if (dgstfnCnButton) {
            dgstfnCnButton.click();
          }
        }
      });
    }

  });

  function insertSatisfaction() {
    var dgstfnScr = document.getElementById('dgstfnScr').value;
    var dgstfnCn = document.getElementById('dgstfnCn').value;
    var prgrmId = document.getElementById('prgrmId').value;

    // 검증
    if (dgstfnCn.trim() === "") return alert('의견을 입력해주세요.');
    if (prgrmId === '') return alert('메뉴정보가 없습니다.');


    // 사용할 데이터를 준비합니다.
    var data = {
      dgstfnCn: dgstfnCn,
      prgrmId: prgrmId,
      dgstfnScr: dgstfnScr
    };

    // Ajax 요청을 보냅니다.
    $.ajax({
      url: "/common/insertSatisfaction.do",
      type: "POST",
      contentType: "application/json",
      dataType: 'json',
      data: JSON.stringify(data),
      success: function (data) {
        console.log("Success:", data);
        alert("의견등록에 성공하였습니다."); // 등록 완료 메시지 출력
        document.getElementById('dgstfnCn').value = '';
        setDgstfnFields('5');
      },
      error: function (error) {
        console.error("Error:", error);
        alert("의견등록에 실패했습니다."); // 등록 실패 메시지 출력
      }
    });
  }


  function setDgstfnFields(dgstfnScr) {
    var btnDgstfnForClicks = document.querySelectorAll('.btnDgstfnForClick');
    btnDgstfnForClicks.forEach(function (btnDgstfnForClick) {
      btnDgstfnForClick.parentNode.classList.remove('active');
      if(btnDgstfnForClick.dataset.dgstfnscr === dgstfnScr) btnDgstfnForClick.parentNode.classList.add('active');
    });
    document.getElementById('dgstfnScr').value = dgstfnScr;
  }

</script>

<div class="greyBdrBox gap">
<form name="SearchForm" action="<c:url value='/common/insertSatisfaction.do'/>" method="post">
  <input type="hidden" id="prgrmId" name="prgrmId" value="${activePrgrmId}">
  <input type="hidden" id="dgstfnScr" name="dgstfnScr" value="5">
  <p>이 페이지에서 제공하는 정보에 대하여 어느정도 만족하셨습니까?</p>
  <div class="desc">
    <div class="imgWrap">
      <ul>
        <li class="active">
          <a class="btnDgstfnForClick" href="#" data-dgstfnScr="5" title="매우 만족">
            <img src="/images/egovframework/main/images/ic_great.svg" alt="매우 만족">
          </a>
          <span>매우 만족</span>
        </li>
        <li>
          <a class="btnDgstfnForClick" href="#" data-dgstfnScr="4" title="만족" >
            <img src="/images/egovframework/main/images/ic_good.svg" alt="만족">
          </a>
          <span>만족</span>
        </li>
        <li>
          <a class="btnDgstfnForClick" href="#" data-dgstfnScr="3" title="보통">
            <img src="/images/egovframework/main/images/ic_okay.svg" alt="보통">
          </a>
          <span>보통</span>
        </li>
        <li>
          <a class="btnDgstfnForClick" href="#" data-dgstfnScr="2" title="불만족">
            <img src="/images/egovframework/main/images/ic_bad.svg" alt="불만족">
          </a>
          <span>불만족</span>
        </li>
        <li>
          <a class="btnDgstfnForClick" href="#" data-dgstfnScr="1" title="매우 불만족">
            <img src="/images/egovframework/main/images/ic_terrible.svg" alt="매우 불만족">
          </a>
          <span>매우 불만족</span>
        </li>
      </ul>
    </div>
    <div class="rgt">
      <input type="text" id="dgstfnCn" name="dgstfnCn" placeholder="의견을 등록해주세요." maxlength="1000" title="의견을 등록해주세요.">
      <button type="button" onclick="insertSatisfaction();" class="dark">의견등록</button>
    </div>
  </div>
</form>
</div>