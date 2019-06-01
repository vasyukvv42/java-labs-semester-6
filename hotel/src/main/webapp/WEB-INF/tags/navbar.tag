<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-05-31
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Navbar tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="user" type="edu.kpi.hotel.model.entity.User"%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Java Hotel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/hotels">Hotels</a>
            </li>
            <c:choose>
                <c:when test="${user.client}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/requests">My Requests</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/reservations">My Reservations</a>
                    </li>
                </c:when>

                <c:when test="${user.administrator}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/requests">Pending Requests</a>
                    </li>
                </c:when>
            </c:choose>

        </ul>

        <c:choose>
            <c:when test="${user == null}">
                <a class="btn btn-outline-success my-2 my-sm-0" href="${pageContext.request.contextPath}/app/login">Log in</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-outline-danger my-2 my-sm-0" href="${pageContext.request.contextPath}/app/logout">Log out</a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

