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

<c:set var="mode" value="default" scope="page"/>
<c:set var="id" value="thisisnotgood" scope="page"/>
<c:if test="${not empty param_mode}">
  <c:set var="mode" value="${param_mode}" scope="page"/>
  <c:set var="id" value="${egovc:encrypt(param_atchFileId)}" scope="page"/>
</c:if>

<script>
  window['${id}-callback'] = (fileList) => {
    const mode = '${mode}';
    if (fileList && fileList.length > 0 ) {
      if ( mode === 'default' ) {
        document.getElementById('${id}-a').innerHTML = fileList[0].orignlFileNm;
      }
      document.getElementById('${id}-fileSn').value = fileList[fileList.length - 1].fileSn;
    }
  }

</script>

<c:choose>
  <c:when test="${mode eq 'default'}">
    <a id="${id}-a" href="#" onclick="
        const atchFileId = '${id}';
        const fileSn = document.getElementById('${id}-fileSn').value;
        window.open('/cmm/fms/FileDown.do?atchFileId=' + atchFileId + '&fileSn=' + fileSn);
        " class="file"></a>
  </c:when>
  <c:when test="${mode eq 'downForDataMetaSn'}">
    <button id="${id}-a" onclick="
        const atchFileId = '${id}';
        const fileSn = document.getElementById('${id}-fileSn').value;
        window.open(`/cmm/fms/FileDown.do?atchFileId=\${atchFileId}&fileSn=\${fileSn}&mode=${mode}&key=${param_key}`);
        return false;
        " class="download" type="button" title="exceldown"><span>엑셀 다운로드</span></button>
  </c:when>
  <c:otherwise>
    관리자에게 문의바랍니다.
  </c:otherwise>
</c:choose>


<div id="${id}-origin" style="background-color: #0a6aa1;display: none" >
  <input type="hidden" id="${id}-fileSn" value="">
<c:import url="/cmm/fms/selectFileInfsForUpdate.do" >
  <c:param name="param_atchFileId" value="${id}"/>
  <c:param name="param_callback" value="${id}-callback"/>
</c:import>
</div>

