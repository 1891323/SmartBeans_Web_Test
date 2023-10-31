<%--
  Created by IntelliJ IDEA.
  User: 네이버시스템
  Date: 2023-10-27
  Time: 오후 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<header id="header">
    <div class="menubar">
        <div class="menuWrap">
            <h1 class="logo">
                <a href="/" class="logo"><img src="/images/egovframework/main/images/logo@2x.png" alt="pc메인로고"></a>
            </h1>

            <nav id="gnb">
                <ul>
                    <c:forEach var="menu" items="${topMenu}">
                        <c:if test="${menu.menu_no <= 5}">
                            <li>
                                <a>${menu.menu_name}</a> <%--주메뉴--%>
                                <ul class="subMenu admin">
                                    <c:forEach var="submenu" items="${topMenu}">
                                        <c:if test="${submenu.menu_parent_id == menu.menu_no}">
                                            <li>
                                                <a>${submenu.menu_name}</a> <%--서브메뉴--%>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </nav>

            <div class="user">
                <a>
                    <img src="/images/egovframework/main/images/ic_login.svg" alt="로그인">
                    <span>하이루</span>
                </a>
            </div>
        </div>
    </div>
</header>
