<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="localization.timekeeping"/>

<html>
<head>
    <title>
        <fmt:message key="page.title.top.10.users"/>
    </title>
    <style>
        <%@ include file="/WEB-INF/css/main.css" %>
    </style>
</head>
<body>

<jsp:include page="elements/header.jsp"/>

<div class="grid__top">
    <div class="grid__top__row">
        <div class="top__section__header">Place</div>
        <div class="top__section__header">Username</div>
        <div class="top__section__header">Activity</div>
        <div class="top__section__header">Total time</div>
    </div>

    <c:forEach var="iter" begin="0" end="${topList.size()-1}">
        <c:if test="${iter%2 eq 0}">
            <div class="grid__top__row">
                <div class="top__section__light">${iter+1}</div>
                <div class="top__section__light">${topList.get(iter).user.username}</div>
                <div class="top__section__light">${topList.get(iter).activity.name}</div>
                <div class="top__section__light">${topList.get(iter).total}</div>
            </div>
        </c:if>
        <c:if test="${iter%2 eq 1}">
            <div class="grid__top__row">
                <div class="top__section__dark">${iter+1}</div>
                <div class="top__section__dark">${topList.get(iter).user.username}</div>
                <div class="top__section__dark">${topList.get(iter).activity.name}</div>
                <div class="top__section__dark">${topList.get(iter).total}</div>
            </div>
        </c:if>
    </c:forEach>
</div>

<c:if test="${not empty user.email}">
    <a href="/subscribe?event=top" class="subscribe__link"><fmt:message key="link.distribution"/></a>
</c:if>

<jsp:include page="elements/footer.jsp"/>

</body>
</html>
