<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="timekeeping.my.model.entity.Role" %>

<fmt:setBundle basename="localization.timekeeping"/>

<header class="header">
    <div class="header__container">
        <div class="header__logo">
            ${role}
        </div>

        <nav class="header__nav">
            <a id="EN" href="?lang=EN" class="header__link header__lang" onclick="modify(this)">EN</a>
            |
            <a id="RU" href="?lang=RU" class="header__link header__lang" onclick="modify(this)">RU</a>

            <a href="../home" class="header__link"><fmt:message key="link.home"/></a>
            <a href="../login" class="header__link"><fmt:message key="link.login"/></a>
            <c:if test="${role eq Role.ANONYMOUS}">
                <a href="../register" class="header__link"><fmt:message key="link.register"/></a>
            </c:if>
            <c:if test="${role eq Role.USER}">
                <a href="../create" class="header__link"><fmt:message key="link.add.activity"/></a>
                <a href="../cabinet" class="header__link"><fmt:message key="link.personal.cabinet"/></a>
                <a href="../top" class="header__link"><fmt:message key="link.top.10.users"/></a>
                <a href="../logout" class="header__link"><fmt:message key="link.logout"/></a>
            </c:if>
            <c:if test="${role eq Role.ADMIN}">
                <a href="../admin" class="header__link"><fmt:message key="link.admin"/></a>
                <a href="../logout" class="header__link"><fmt:message key="link.logout"/></a>
            </c:if>
        </nav>
    </div>
</header>

<script type="text/javascript">
    function modify(a) {
        const current = window.location.href.toString();
        if(current.indexOf("?") > -1 && current.indexOf("lang") === -1) {
            a.setAttribute("href", current + "&lang=" + a.id);
        } else if(current.indexOf("lang") > -1) {
            let index = current.indexOf("lang");
            a.setAttribute("href", current.substring(0, index+5) + a.id);
        } else {
            a.setAttribute("href", current + "?lang=" + a.id);
        }
    }
</script>