<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="container">
<div class="breadCrumb">
  <ul>
    <li><a href="#"><img src="/images/images/ic_sub_navi_home.svg" alt="메인으로"></a></li>
    <li><a href="#">관리자 메뉴</a></li>
    <li><a href="#">게시판 관리</a></li>
    <li><a href="#">공지사항 관리</a></li>
  </ul>
</div>
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
              <a href="">문의하기 관리</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="">사용자 관리</a>
          <ul style="display:block;">
            <li class="lnbCnt active">
              <a href="">권한 관리</a>
            </li>
            <li class="lnbCnt">
              <a href="">메뉴 관리</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="">만족도 통계 관리</a>
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
    <h2>사용자 권한 관리</h2>
    <div class="inner">
      <table>
      <colgroup>
        <col style="width: 10%;">
        <col style="width:35%;">
        <col style="width:auto;">
      </colgroup>
        <thead>
          <tr>
            <th>NO</th>
            <th>아이디</th>
            <th>권한 여부</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>10</td>
            <td>Abc123e</td>
            <td>
              <div class="btnWrap">
                <div class="radioBtnWrap" >
                  <input type="radio" name="user" id="Radio1" value="Y">
                  <label for="Radio1" id="Btn1" tabindex="9">&nbsp;</label>
                  <label for="Radio1">사용자</label>
                </div> 
                <div class="radioBtnWrap" >
                  <input type="radio" name="user" id="Radio2" value="N">
                  <label for="Radio2" id="Btn2" tabindex="10">&nbsp;</label>
                  <label for="Radio2">운영자</label>
                </div>
                <div class="radioBtnWrap" >
                  <input type="radio" name="user" id="Radio3" value="N">
                  <label for="Radio3" id="Btn3" tabindex="10">&nbsp;</label>
                  <label for="Radio3">관리자</label>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <div>
        <ul class="pagination" id = "pagination">
          <li><button type='button' class='btnPrevend'></button></li>
          <li><button type='button' class='btnPrev'></button></li>
          <li class="on"><a href="">1</a></li>
          <li><a href= "">2</a></li>
          <li><a href= "">3</a></li>
          <li><a href= "">4</a></li>
          <li><a href= "">5</a></li>
          <li><button type='button' class='btnNext'></button></li>
          <li><button  type='button' class='btnNextEnd'></button></li>
        </ul>
      </div>
      <div class="btnArea">
        <ul>
          <li>
            <button type="button" class="dark">저장</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
</section>
<script>
  $(function(){
// radio btn click event 
  $("#Btn1").on("click", function(){
    if($("#Radio1").is(":checked")) {
    $("#Radio2").prop("checked", false);
    $("#Radio3").prop("checked", false);
    } else {
    $("#Radio1").prop("checked", true);
    }
  })
  $("#Btn2").on("click", function(){
    if($("#Radio2").is(":checked")) {
    $("#Radio1").prop("checked", false);
    $("#Radio3").prop("checked", false);
    } else {
    $("#Radio2").prop("checked", true);
    }
  })
  $("#Btn3").on("click", function(){
    if($("#Radio3").is(":checked")) {
    $("#Radio1").prop("checked", false);
    $("#Radio2").prop("checked", false);
    } else {
    $("#Radio3").prop("checked", true);
    }
  })
 

});


</script>