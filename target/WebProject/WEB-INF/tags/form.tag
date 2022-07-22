<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ attribute name="form" required="true" type="timekeeping.my.view.form.Form" %>
<%@ attribute name="id" %>
<fmt:setBundle basename="localization.timekeeping"/>



<form   <c:if test="${empty id}">
            action="${form.action}"
        </c:if>
        <c:if test="${not empty id}">
            action="${form.action}${id}"
        </c:if>
        method="${form.method}"
        name="${form.id}" class="form">
    <h1 class="form__title"><fmt:message key="${form.title}"/></h1>
    <c:if test="${form.hasErrors}">
        <h3 class="error">${form.errors}</h3>
    </c:if>
    <c:forEach var="field" items="${form.fields}">
        <tag:field field="${field}"/>
    </c:forEach>
    <c:if test="${not empty form.buttonText}">
        <button type="submit" class="form__button">
            <fmt:message key="${form.buttonText}"/>
        </button>
    </c:if>
</form>