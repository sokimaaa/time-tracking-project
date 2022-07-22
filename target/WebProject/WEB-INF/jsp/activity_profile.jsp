<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ page import="timekeeping.my.model.entity.Role" %>
<%@ page import="timekeeping.my.model.entity.Access" %>

<fmt:setBundle basename="localization.timekeeping"/>

<html>
<head>
    <title>
        <fmt:message key="page.title.activity.profile"/>
    </title>
    <style>
        <%@ include file="/WEB-INF/css/main.css" %>
    </style>
</head>
<body>

<jsp:include page="elements/header.jsp"/>

<c:if test="${role eq Role.ADMIN}">
    <div class="grid__profile">
        <div class="profile__part">
            <fmt:message key="activity.title"/> <br><br>
            <fmt:message key="activity.name"/> ${activity.name} <br>
            <fmt:message key="activity.category"/> ${activity.category.name} <br>
            <fmt:message key="activity.city"/> ${activity.city} <br>
            <c:if test="${not empty activity.description}">
                <fmt:message key="activity.description"/> ${activity.description} <br>
            </c:if>
            <br>
            <fmt:message key="activity.owner"/> ${activity.owner.username}
        </div>
    </div>
</c:if>

<c:if test="${role eq Role.USER}">
    <div class="grid__profile">
        <div class="profile__part">
            <fmt:message key="activity.title"/> <br><br>
            <fmt:message key="activity.name"/> ${activity.name} <br>
            <fmt:message key="activity.category"/> ${activity.category.name} <br>
            <fmt:message key="activity.city"/> ${activity.city} <br>
            <c:if test="${not empty activity.description}">
                <fmt:message key="activity.description"/> ${activity.description} <br>
            </c:if>
            <br>
            <fmt:message key="activity.owner"/> ${activity.owner.username}
        </div>
        <div class="profile__part">
            <c:choose>
                <c:when test="${role eq Role.USER}">
                    <c:if test="${empty action}">
                        <c:if test="${not empty form}">
                            <tag:form form="${form}" id="${activity.id}"/>
                        </c:if>
                    </c:if>
                    <c:if test="${not empty action}">
                        <c:if test="${action.access eq Access.ALLOW}">
                            <tag:info time="${action}"/>
                            <c:if test="${not empty form}">
                                <tag:form form="${form}" id="${activity.id}"/>
                            </c:if>
                        </c:if>
                        <c:if test="${action.access eq Access.DENY}">
                            <fmt:message key="message.wait.answer"/>
                        </c:if>
                    </c:if>
                </c:when>
            </c:choose>
        </div>
    </div>
</c:if>

<jsp:include page="elements/footer.jsp"/>

</body>
</html>