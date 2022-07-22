<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ attribute name="list" required="true" type="java.util.List" %>
<%@ attribute name="isUser" %>
<%@ attribute name="isActivity" %>
<%@ attribute name="isActivity2" %>
<%@ attribute name="isCategory" %>
<%@ attribute name="isRequest" %>

<c:if test="${not empty isUser}">
    <c:forEach var="user" items="${list}">
        <div class="light__theme">
            Login: ${user.username} <br>
            Email: ${empty user.email ? 'no specify' : user.email} <br>
            Phone: ${empty user.phone ? 'no specify' : user.phone} <br>
            City: ${empty user.city ? 'no specify' : user.city} <br>
            Sex: ${empty user.sex ? 'no specify' : user.sex} <br>
            <c:if test="${user.access.access}">
                <a href="/lock?ban=false&id=${user.id}" class="button__link">BAN</a>
            </c:if>
            <c:if test="${!user.access.access}">
                <a href="/lock?ban=true&id=${user.id}" class="button__link">UNBAN</a>
            </c:if>
        </div>
    </c:forEach>
</c:if>

<c:if test="${not empty isActivity}">
    <c:forEach var="iter" begin="0" end="${list.size()-1}">
        <c:if test="${iter%2 eq 0}">
            <div class="dark">
                <div>
                    Activity name: ${list.get(iter).name} <br>
                    Category: ${list.get(iter).category.name} <br>
                    City: ${list.get(iter).city} <br>
                    Count users: ${list.get(iter).count} <br>
                    Owner: ${list.get(iter).owner.username} <br><br>
                    <a href="/activity?id=${list.get(iter).id}" class="button__link">
                        Press here to enter
                    </a>
                </div>
                <c:if test="${not empty list.get(iter).description}">
                    <div>
                        Description: <br><br>
                        ${list.get(iter).description}
                    </div>
                </c:if>
            </div>
        </c:if>
        <c:if test="${iter%2 eq 1}">
            <div class="light">
                <div>
                    Activity name: ${list.get(iter).name} <br>
                    Category: ${list.get(iter).category.name} <br>
                    City: ${list.get(iter).city} <br>
                    Count users: ${list.get(iter).count} <br>
                    Owner: ${list.get(iter).owner.username} <br><br>
                    <a href="/activity?id=${list.get(iter).id}" class="button__link">
                        Press here to enter
                    </a>
                </div>
                <c:if test="${not empty list.get(iter).description}">
                    <div>
                        Description: <br><br>
                            ${list.get(iter).description}
                    </div>
                </c:if>
            </div>
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${not empty isActivity2}">
    <c:forEach items="${list}" var="activity">
        <div class="light__theme">
            Activity's id: ${activity.id} <br>
            Name: ${activity.name} <br>
            Category: ${activity.category.name} <br>
            Owner: ${activity.owner.username} <br>
            Status: ${activity.status} <br>
            Count customers: ${activity.count} <br>
            <a href="/remove?id=${activity.id}" class="button__link">Remove</a> <br>
            <a href="/change?id=${activity.id}&status=${activity.status.value}" class="button__link">Change status</a><br>
        </div>
    </c:forEach>
</c:if>

<c:if test="${not empty isCategory}">
    <div class="act__list">
        <ul>
            <h4 class="light__title">Existing categories:</h4>
            <hr>
            <br><br>
            <c:forEach items="${categories}" var="category">
                <li class="light__text">${category.name}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${not empty isRequest}">
    <c:forEach items="${requests}" var="request">
        <div class="dark__theme">
            Request <br>
            User: ${request.user.username} <br>
            Activity: ${request.activity.name} <br>
            Owner: ${request.activity.owner.username} <br>
            Access: ${request.access} <br>
            <a href="/accept?user=${request.user.id}&activity=${request.activity.id}" class="button__link">Accept</a>
        </div>
    </c:forEach>
</c:if>
