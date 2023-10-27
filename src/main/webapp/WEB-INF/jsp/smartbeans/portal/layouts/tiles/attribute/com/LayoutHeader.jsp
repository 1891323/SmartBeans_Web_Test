<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<header id="header">
    <div class="menubar">
        <div class="menuWrap">
            <h1 class="logo">
                <a href="" class="logo"><img src="/images/egovframework/main/images/logo@2x.png" alt="pc메인로고"></a>
            </h1>
            <nav id="gnb">
                <ul>
                    <li>
                        <a href="">데이터 현황</a>
                    </li>
                    <li>
                        <a href="">데이터 통계</a>
                        <ul class="subMenu">
                            <li><a href="">데이터 목록</a></li>
                            <li><a href="">데이터 시각화</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="">긴급구난</a>
                    </li>
                    <li>
                        <a href="">안전2.0</a>
                    </li>
                    <li>
                        <a href="">참여/소식</a>
                        <ul class="subMenu">
                            <li><a href="">공지사항</a></li>
                            <li><a href="">문의하기</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="">데이터허브 포털 소개</a>
                    </li>
                    <li>
                        <a href="">관리자 메뉴</a>
                        <ul class="subMenu admin">
                            <li>
                                <a href="">게시판 관리</a>
                                <ul class="menuList">
                                    <li><a href="/AnnouncementList.do">공지사항 관리</a></li>
                                    <li><a href="">문의하기 관리</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="">사용자 관리</a>
                                <ul class="menuList">
                                    <li><a href="">권한 관리</a></li>
                                    <li><a href="">메뉴 관리</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="">만족도 통계 관리</a>
                                <ul class="menuList">
                                    <li><a href="">사용자별</a></li>
                                    <li><a href="">메뉴별</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="">접속 이력 관리</a>
                                <ul class="menuList">
                                    <li><a href="">사용자별</a></li>
                                    <li><a href="">메뉴별</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="">데이터 관리</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <div class="user">
                <a>
                    <img src="/images/egovframework/main/images/ic_login.svg" alt="로그인">
                    <span>xellosslw1</span>
                </a>
            </div>
        </div>
    </div>
</header>