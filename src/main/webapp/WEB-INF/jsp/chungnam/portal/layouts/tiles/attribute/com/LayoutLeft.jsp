<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!-- 왼쪽 메뉴 html -->
<div class="sidebar">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="#">게시판관리</a>
            <ul class="nav flex-column ml-3">
                <li class="nav-item"><a class="nav-link" href="#">공지사항 관리</a></li>
                <li class="nav-item"><a class="nav-link" href="#">문의하기 관리</a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">사용자 관리</a>
            <ul class="nav flex-column ml-3">
                <li class="nav-item"><a class="nav-link" href="#">권한 관리</a></li>
                <li class="nav-item"><a class="nav-link" href="#">메뉴 관리</a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">만족도 통계 관리</a>
            <ul class="nav flex-column ml-3">
                <li class="nav-item"><a class="nav-link" href="#">사용자별</a></li>
                <li class="nav-item"><a class="nav-link" href="#">메뉴별</a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">접속 이력 관리</a>
            <ul class="nav flex-column ml-3">
                <li class="nav-item"><a class="nav-link" href="#">사용자별</a></li>
                <li class="nav-item"><a class="nav-link" href="#">메뉴별</a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">데이터 관리</a>
        </li>
    </ul>
</div>
