<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="localization.timekeeping"/>

<html>
<head>
    <title>
        <fmt:message key="page.title.personal.cabinet"/>
    </title>
    <style>
        <%@ include file="/WEB-INF/css/main.css" %>
    </style>
</head>
<body>

<jsp:include page="elements/header.jsp"/>

<div class="grid__cabinet">
    <div>
        <c:if test="${not empty form}">
            <tag:form form="${form}"/>
        </c:if>
    </div>
    <div>
        <h3 class="black__title">Current account info</h3>
        <hr>
        <br>

        Login: ${user.username} <br>
        Email: ${empty user.email ? 'no specify' : user.email} <br>
        Phone: ${empty user.phone ? 'no specify' : user.phone} <br>
        City: ${user.city} <br>
        Sex: ${user.sex} <br>
        <br><br>
        <c:if test="${not empty user.email}">
            <a href="/subscribe" class="button__link"><fmt:message key="link.distribution"/></a>
        </c:if>
    </div>
</div>

<jsp:include page="elements/footer.jsp"/>

</body>
</html>
