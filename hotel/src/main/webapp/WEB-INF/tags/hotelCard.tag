<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 02:30
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Hotel card tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="hotel" type="edu.kpi.hotel.model.entity.Hotel"%>

<div class="card">
    <h5 class="card-header">
        <c:forEach begin="1" end="${hotel.rating}" step="1">★</c:forEach><c:forEach begin="${hotel.rating}" end="4" step="1">☆</c:forEach>
        ${hotel.name}
    </h5>
    <div class="card-body">
        <h5 class="card-title">${hotel.city}, ${hotel.country}</h5>
        <h6 class="card-subtitle mb-2 text-muted">${hotel.address}</h6>
        <p class="card-text">${hotel.rooms.size()} rooms</p>
        <c:choose>
            <c:when test="${sessionScope.user.administrator}">
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/app/create-room">
                    <input type="hidden" name="hotelId" value="${hotel.id.toString()}">

                    <input type="number" class="form-control mb-2 mr-sm-2" name="number" placeholder="402" required>

                    <div class="input-group mb-2 mr-sm-2">
                        <input type="number" class="form-control" name="cost" placeholder="00.00" required>
                        <div class="input-group-append">
                            <div class="input-group-text">$/day</div>
                        </div>
                    </div>

                    <input type="number" class="form-control mb-2 mr-sm-2" name="maxPeople" placeholder="Max people" required>

                    <button type="submit" class="btn btn-primary mb-2">Add room</button>
                </form>
            </c:when>

            <c:when test="${sessionScope.user.client}">
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/app/create-room-request">
                    <input type="hidden" name="hotelId" value="${hotel.id.toString()}">

                    <input type="number" class="form-control mb-2 mr-sm-2" name="people" placeholder="People" required>

                    <div class="input-group mb-2 mr-sm-2">
                        <input type="number" class="form-control" name="maxCost" placeholder="00.00" required>
                        <div class="input-group-append">
                            <div class="input-group-text">$/day</div>
                        </div>
                    </div>

                    <input type="date" class="form-control mb-2 mr-sm-2" name="from" required>
                    <input type="date" class="form-control mb-2 mr-sm-2" name="to" required>

                    <button type="submit" class="btn btn-primary mb-2">Request a room</button>
                </form>
            </c:when>
        </c:choose>
    </div>
</div>