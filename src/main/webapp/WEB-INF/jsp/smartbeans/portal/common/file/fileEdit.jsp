<%--
 * file : fileView.jsp
 * author kiboomhan
 * since 2023-05-24
 * version 1.0
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>"></script>

<c:set var="mode" value="default" scope="page"/>
<c:set var="id" value="thisisnotgood" scope="page"/>
<c:if test="${not empty param_mode}">
  <c:set var="mode" value="${param_mode}" scope="page"/>
  <c:set var="id" value="${egovc:encrypt(param_atchFileId)}" scope="page"/>
</c:if>

<script>
  const tryUpload = () => {
    event.preventDefault();
    if ($(".upload-name").val() === "파일선택") {
      $('#ex_filename').click();
    }
  }


window['${id}-callback'] = (fileList) => {
    if (fileList && fileList.length > 0 ) {
      $('#file').val(fileList[fileList.length - 1].orignlFileNm);
      $('#file2').attr("value", $('#file').val());
      showDeleteBtn();
    }
  }

  $(document).ready(() => {
    // 첨부파일명 삽입
    var fileTarget = $('#ex_filename');

    fileTarget.on('change', function () {
      var fileList = fileTarget[0].files;
      var fileNames = $("#egovComFileList");

      fileNames.empty(); // 기존 목록을 비웁니다.

      for (var i = 0; i < fileList.length; i++) {
        var fileNameDiv = $('<div>').addClass('file_name').text(fileList[i].name);
        var deleteBtn = $('<button>').addClass('deleteBtn').text('삭제').attr('data-file-index', i);

        deleteBtn.on('click', function() {
          var fileIndex = $(this).data('file-index');
          // 파일 목록에서 해당 인덱스의 파일 제거
          var newFileList = Array.from(fileTarget[0].files).filter((_, index) => index !== fileIndex);
          // 인풋 필드에 새 파일 목록 설정 (아래 설명 참조)
          setFilesToFileInput(fileTarget[0], newFileList);
          // 목록에서 해당 요소 제거
          $(this).parent().remove();
        });

        fileNames.append(fileNameDiv.append(deleteBtn));
      }
    });
  });

  function setFilesToFileInput(fileInput, files) {
    // DataTransfer 객체를 사용하여 파일 목록을 설정
    var dataTransfer = new DataTransfer();
    files.forEach(file => dataTransfer.items.add(file));
    fileInput.files = dataTransfer.files;
  }


  //첨부파일 취소버튼 이벤트
  $(document).on("click", ".deleteBtn", function () {
    if (confirm("첨부파일을 삭제하시겠습니까?") === false) {
      return;
    }
    $(this).siblings('.upload-name').val("파일선택");
    $(this).siblings('#ex_filename').val("");
    showDeleteBtn();
    fn_egov_deleteFile_all('${id}');
  });

  //파일 삭제버튼 이벤트
  function showDeleteBtn() {
    if ($(".upload-name").val() === "파일선택") {
      $(".deleteBtn").attr("style", "visibility:hidden");
      $('#for_ex_filename').css("opacity", "1");
    } else {
      $(".deleteBtn").attr("style", "visibility:visible");
      $('#for_ex_filename').css("opacity", "0.3");
    }
  }

</script>


<c:choose>
  <c:when test="${mode eq 'default'}">
    <input type="text" class="upload-name" value="파일선택" placeholder="파일을 선택하세요" disabled
           id="file" name="fileNm">
    <input type="hidden" class="p-input" id="file2" name="fileNnm"/>
    <button type="button" class="deleteBtn"></button>
    <label id="for_ex_filename" onclick="tryUpload()"> <!--for="ex_filename"-->
      <button type="button" class="paperClip"></button>
    </label>
<%--    <input type="file" id="ex_filename" class="upload-hidden" name="uploadFiles">--%>
<%--    <input type="file" id="ex_filename" class="upload-hidden" name="file_1">--%>
    <input type="file" id="ex_filename" class="upload-hidden" name="uploadFiles" multiple>

  </c:when>
  <c:otherwise>
    관리자에게 문의바랍니다.
  </c:otherwise>
</c:choose>


<div style="    background-color: #0a6aa1;
    display: none;
    position: absolute;
    margin-top: 114px;
    width: 100%;">
  <c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
    <c:param name="param_atchFileId" value="${id}"/>
    <c:param name="param_callback" value="${id}-callback"/>
  </c:import>

  <div id="egovComFileList"></div>

</div>

<script type="text/javascript">
  var maxFileNum = 3;
  var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
  // multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
  multi_selector.addElement( document.getElementById( 'ex_filename' ) );
</script>