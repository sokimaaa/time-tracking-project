<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ tag import="timekeeping.my.view.field.FieldType" %>

<%@ attribute name="field" required="true" type="timekeeping.my.view.field.Field" %>
<fmt:setBundle basename="localization.timekeeping"/>

<div>
    <c:choose>
        <c:when test="${field.type eq FieldType.SELECT}">
            <div class="form__group">
                <select name="${field.name}" class="select">
                    <c:forEach var="option" items="${field.options}">
                        <option value="${option.value}">${option.text}</option>
                    </c:forEach>
                </select>
            </div>
        </c:when>
        <c:when test="${field.type eq FieldType.INPUT}">
            <div class="form__group">
                <input type="${field.inputType}"
                       name="${field.name}"
                       placeholder=" "
                <c:if test="${field.hidden}">
                       hidden="hidden"
                </c:if>
                <c:if test="${field.disabled}">
                       disabled
                </c:if>
                class="form__input">
                <label for="${field.name}" class="form__label"><fmt:message key="${field.tittle}"/></label>
            </div>
        </c:when>
    </c:choose>
</div>