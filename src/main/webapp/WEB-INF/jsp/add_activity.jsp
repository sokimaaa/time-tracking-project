<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="localization.timekeeping"/>

<head>
    <title>
        <fmt:message key="page.title.add.activity"/>
    </title>
    <style>
        <%@ include file="/WEB-INF/css/main.css" %>
    </style>
</head>
<body>

<jsp:include page="elements/header.jsp"/>

<div class="form__container">
    <c:if test="${not empty form}">
        <tag:form form="${form}"/>
    </c:if>
</div>

<jsp:include page="elements/footer.jsp"/>

</body>
</html>