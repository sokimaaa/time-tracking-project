<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:setBundle basename="localization.timekeeping"/>

<html>
<head>
    <title>
      <fmt:message key="page.title.home"/>
    </title>
    <style>
        <%@ include file="/WEB-INF/css/main.css" %>
    </style>
</head>
<body>

<jsp:include page="elements/header.jsp"/>

<div class="nav__manipulate">
    <div class="filter">
        <div><a href="#" onclick="builderFilterPath(this)" class="link__manipulate">all</a></div>
        <c:forEach items="${categories}" var="category">
            <div><a id="filter=${category.id}" href="#" onclick="builderFilterPath(this)" class="link__manipulate">${category.name}</a></div>
        </c:forEach>
    </div>
    <div></div>
    <div class="sorter">
        <div><a href="#" onclick="builderSortPath(this)" class="link__manipulate"><fmt:message key="sort.turn.off"/></a></div>
        <div><a id="sort=name" href="#" onclick="builderSortPath(this)" class="link__manipulate"><fmt:message key="sort.by.name"/></a></div>
        <div><a id="sort=category" href="#" onclick="builderSortPath(this)" class="link__manipulate"><fmt:message key="sort.by.category"/></a></div>
        <div><a id="sort=count" href="#" onclick="builderSortPath(this)" class="link__manipulate"><fmt:message key="sort.by.count"/></a></div>
    </div>
</div>
<div class="grid__container">
    <tag:list list="${activities}" isActivity="true"/>
</div>
<div class="pagination__bar">
    <c:forEach var="number" begin="1" end="${length}">
        <a id="page=${number}" href="#" class="pagination__item" onclick="builderPaginationPath(this)">${number}</a>
    </c:forEach>
</div>

<jsp:include page="elements/footer.jsp"/>

<script type="text/javascript">
  const currentLink = window.location.href.toString();
  const startLink = "http://localhost:8080/home/";

  const regexPagination = new RegExp("page=");
  const regexSort = new RegExp("sort=");
  const regexFilter = new RegExp("filter=");

  function builderFilterPath(a) {
    const filterParam = a.id;
    let href = startLink;

    if(regexPagination.test(currentLink)) {
      const matches = currentLink.match("page=.+?[/;]");
      href += matches[0].substring(0, matches[0].length-1) + ";";
    }

    if(!(filterParam === "")) {
      href += filterParam + ";";
    }

    if(regexSort.test(currentLink)) {
      const matches = currentLink.match("sort=.+?[/;]")
      href += matches[0];
    } else {
      href = href.substring(0, href.length - 1) + "/";
    }

    a.setAttribute("href", href);
  }

  function builderSortPath(a) {
    const sortParam = a.id;
    let href = startLink;

    if(regexPagination.test(currentLink)) {
      const matches = currentLink.match("page=.+?[/;]");
      href += matches[0].substring(0, matches[0].length-1) + ";";
    }

    if(regexFilter.test(currentLink)) {
      const matches = currentLink.match("filter=.+?[/;]");
      href += matches[0].substring(0, matches[0].length-1) + ";";
    }

    if(sortParam === "") {
      href = href.substring(0, href.length - 1) + "/";
    } else {
      href += sortParam + "/";
    }

    a.setAttribute("href", href);
  }

  function builderPaginationPath(a) {
      const paginationNumber = a.id;
      let href = startLink;

      href += paginationNumber;

      if(regexFilter.test(currentLink)) {
          const matches = currentLink.match("filter=.+?[/;]");
          href += ";" + matches[0].substring(0, matches[0].length-1);
      }

      if(regexSort.test(currentLink)) {
          const matches = currentLink.match("sort=.+?[/;]")
          href += ";" + matches[0];
      } else {
          href += "/";
      }
      a.setAttribute("href", href);
  }
</script>
</body>
</html>
