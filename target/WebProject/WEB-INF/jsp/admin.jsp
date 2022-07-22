<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="localization.timekeeping"/>

<html>
<head>
    <title>
        <fmt:message key="page.title.admin"/>
    </title>
    <style>
        <%@ include file="/WEB-INF/css/main.css" %>
    </style>
</head>
<body>

<jsp:include page="elements/header.jsp"/>

<div class="tab__content">
    <div class="tabs">
        <nav class="tabs__items">
            <a href="../admin/#users" class="tabs__item"><span><fmt:message key="manage.nav.users"/></span></a>
            <a href="../admin/#categories" class="tabs__item"><span><fmt:message key="manage.nav.categories"/></span></a>
            <a href="../admin/#activities" class="tabs__item"><span><fmt:message key="manage.nav.activities"/></span></a>
            <a href="../admin/#requests" class="tabs__item"><span><fmt:message key="manage.nav.requests"/></span></a>
        </nav>
        <div class="tabs_body">
            <div id="users" class="tabs__block">
                <c:if test="${not empty users}">
                    <div class="grid__users">
                        <tag:list list="${users}" isUser="true"/>
                    </div>
                </c:if>
            </div>
            <div id="categories" class="tabs__block">
                <div class="grid__categories">
                    <div>
                        <tag:list list="${categories}" isCategory="true"/>
                    </div>
                    <div class="white">
                        <c:if test="${not empty form}">
                            <tag:form form="${form}"/>
                        </c:if>
                    </div>
                </div>
            </div>
            <div id="activities" class="tabs__block">
                <a class="filter__link">|</a> <a href="../admin/#activities" class="filter__link">ALL</a> <a class="filter__link">|</a>
                <a href="../admin/?filter=public#activities" class="filter__link">PUBLIC</a> <a class="filter__link">|</a>
                <a href="../admin/?filter=private#activities" class="filter__link">PRIVATE</a> <a class="filter__link">|</a>
                <br>
                <hr>
                <br>
                <div class="grid__users">
                    <tag:list list="${activities}" isActivity2="true"/>
                </div>
            </div>
            <div id="requests" class="tabs__block">
                <div class="grid__users">
                    <tag:list list="${requests}" isRequest="true"/>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>