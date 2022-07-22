<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="localization.timekeeping"/>

<html>
<head>
  <title>
    <fmt:message key="page.title.access.denied"/>
  </title>
  <style>
    <%@ include file="/WEB-INF/css/main.css" %>
  </style>
</head>
<body>

<jsp:include page="elements/header.jsp"/>

<div class="error__message">
  <fmt:message key="message.access.denied"/>
</div>

<jsp:include page="elements/footer.jsp"/>

</body>
</html>
