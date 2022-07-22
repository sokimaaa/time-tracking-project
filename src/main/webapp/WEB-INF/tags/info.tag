<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="profile" type="timekeeping.my.model.entity.User" %>
<%@ attribute name="time" type="timekeeping.my.model.entity.Action" %>

<c:if test="${not empty time}">
    Total spent time: ${time.total} minutes <br>
    Last spent time: ${time.last} minutes
</c:if>

<c:if test="${not empty profile}">
    ${profile}
    Optional
</c:if>
