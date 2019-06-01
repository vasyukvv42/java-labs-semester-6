<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 04:31
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Room request card tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="roomRequest" type="edu.kpi.hotel.model.entity.RoomRequest"%>
<%@attribute name="suitableRooms" type="java.util.Map<edu.kpi.hotel.model.entity.RoomRequest, java.util.Map<java.lang.Integer, edu.kpi.hotel.model.entity.Room>>"%>

<div class="card border-<c:choose><c:when test="${roomRequest.pending}">warning</c:when><c:when test="${roomRequest.accepted}">success</c:when></c:choose>">
    <c:if test="${sessionScope.user.administrator}">
        <div class="card-header">Request by ${roomRequest.user.username}</div>
    </c:if>
    <div class="card-body">
        <h5 class="card-title">
            Hotel "${roomRequest.hotel.name}"
            (${roomRequest.hotel.city}, ${roomRequest.hotel.country})
        </h5>
        <h6 class="card-subtitle mb-2 text-muted">
            Requested a room for ${roomRequest.people} people<br>
            Willing to pay ${roomRequest.maxCost}$/day<br>
            Reservation date:
            <fmt:formatDate type="date" value="${roomRequest.reserveFrom}"/>
            –
            <fmt:formatDate type="date" value="${roomRequest.reserveTo}"/>
        </h6>
        <c:if test="${sessionScope.user.administrator}">
        <ul class="list-group list-group-flush">
            <c:forEach items="${suitableRooms.get(roomRequest).entrySet()}" var="entry">
                <form method="post" action="${pageContext.request.contextPath}/app/accept-request">
                    <input type="hidden" name="roomNumber" value="${entry.getKey()}">
                    <input type="hidden" name="roomRequestId" value="${roomRequest.id}">

                    <li class="list-group-item">
                        Room #${entry.getKey()}
                        (${entry.getValue().getCost()}$/day, ${entry.getValue().getMaxPeople()} people)
                        <button type="submit" class="btn btn-primary mb-2">Reserve</button>
                    </li>
                </form>
            </c:forEach>
        </ul>
        </c:if>
    </div>
</div>