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
        <li class="active">
          <a href="">게시판 관리</a>
          <ul style="display:block">
            <li class="lnbCnt active">
              <a href="">공지사항 관리</a>
            </li>
            <li class="lnbCnt">
              <a href="">문의하기 관리</a>
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
    <h2>공지사항 신규등록</h2>
    <div class="inner">
      <div class="inputWrap">
        <div class="gridWrap">
          <div>
            <span>NO</span>
            <input type="text">
          </div>
          <div>
            <span>제목</span>
            <input type="text">
          </div>
          <div>
            <span>작성자</span>
            <input type="text">
          </div>
          <div>
            <span>작성일</span>
            <input type="text">
          </div>
          <div>
            <span>내용</span>
            <textarea class="long"></textarea>
          </div>
          <div class="file-upload">
            <span>파일첨부</span>
            <input type="text" class="upload-name"  value="" placeholder="파일을 선택하세요" disabled id="file" name="fileNm">
            <input type="hidden" class="p-input" id="file2" name="fileNnm"/>
            <input type="hidden" class="p-input" id="filePath" name="filePath"/> 
            <input type="hidden" class="p-input" id="newfileName" name="newfileName"/> 
            <button type="button" class="deleteBtn"></button > 
            <label for="ex_filename"><button type="button" class="paperClip"></button></label> 
            <input type="file" id="ex_filename" class="upload-hidden" accept="image/gif,image/jpeg,image/png" name="uploadFiles" multiple="multiple"> 
          </div>
        </div>
      </div>
      <div class="btnArea">
        <ul>
          <li>
            <button type="button" class="dark">목록</button>
          </li>
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
$(document).ready(()=>{
  //첨부파일명 삽입
  var fileTarget = $('#ex_filename');
    fileTarget.on('change', function(){  // 값이 변경되면
        if(window.FileReader){  // modern browser
              var filename = $(this)[0].files[0].name;
        }  else {  // old IE
              var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
        }
        // 추출한 파일명 삽입
        $(this).siblings('#file').val(filename);
      $('#file2').attr("value",$('#file').val())
    showDeleteBtn();
    });
    if(id!=null){
      showList();
    }
    
    const query = window.location.search
    const param = new URLSearchParams(query)
    $('#tid').val(param.get('id'))
  });

  //첨부파일 취소버튼 이벤트
  $(document).on("click",".deleteBtn", function(){
    $(this).siblings('.upload-name').val("파일선택");
    $(this).siblings('#ex_filename').val("");
    showDeleteBtn();
  })

  //파일 삭제버튼 이벤트
  function showDeleteBtn(){
    if($(".upload-name").val() =="파일선택"){
      $(".deleteBtn").attr("style", "visibility:hidden");
    } else{
      $(".deleteBtn").attr("style", "visibility:visible");
    }
  }

  function insertWrap(){
    if($('#title').val()==""){
      alert("제목을 입력해주세요.")
      $('#title').focus();
      return false;
    } else if($('#file').val()=="파일선택"){
      alert("파일을 첨부해주세요.")
      $('#file').focus();
      return false;
    } else if($('#url').val()==""){
      alert("URL을 입력해주세요.")
      $('#url').focus();
      return false;
    }
    const file_Nm = ($('#file').val()=="파일선택" ? "":$('#file_Nm').val())
    
    const obj = document.InsertForm;
    obj.action="/sportal/mypage/keyGuidanceInsert.do";
    obj.submit();
    alert("저장 되었습니다.");
    
  }
</script>