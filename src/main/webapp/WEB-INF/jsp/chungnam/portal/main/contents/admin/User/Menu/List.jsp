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
            <li class="lnbCnt">
              <a href="">권한 관리</a>
            </li>
            <li class="lnbCnt active">
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
    <h2>메뉴 권한 관리</h2>
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
            <th>메뉴</th>
            <th>권한 여부</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>10</td>
            <td>데이터 현황</td>
            <td>
              <div class="btnWrap">
                <div class="radioBtnWrap" >
                  <input type="checkbox" name="checkBtn" id='chkBtn1'><label for="chkBtn1">&nbsp;</label>
                  <label for="chkBtn1">사용자</label>
                </div> 
                <div class="radioBtnWrap" >
                  <input type="checkbox" name="checkBtn" id='chkBtn2'><label for="chkBtn2">&nbsp;</label>
                  <label for="chkBtn2">운영자</label>
                </div>
                <div class="radioBtnWrap" >
                  <input type="checkbox" name="checkBtn" id='chkBtn3'><label for="chkBtn3">&nbsp;</label>
                  <label for="chkBtn3">관리자</label>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      
      <div class="btnArea">
        <ul>
          <li>
            <button type="button" class="dark">저장</button>
          </li>
          <li>
            <button type="button" class="dark">메뉴 추가</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
</section>
<script>
  


</script>