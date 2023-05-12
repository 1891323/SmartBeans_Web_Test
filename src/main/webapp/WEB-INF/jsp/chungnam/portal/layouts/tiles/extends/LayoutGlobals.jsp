<%@ page import="chungnam.config.uitil.ThreadLocalUtil" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<%
    String sessUserId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.sessUserId, "");
    String sessUserNm = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.sessUserNm, "");
    pageContext.setAttribute("sessUserId", sessUserId);
    pageContext.setAttribute("sessUserNm", sessUserNm);
%>

<c:set var="sessUserId" value="${pageScope.sessUserId}" scope="request"/>
<c:set var="sessUserNm" value="${pageScope.sessUserNm}" scope="request"/>

<script>
    const sessUserId = '${sessUserId}';
    const sessUserNm = '${sessUserNm}';
</script>