<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 02:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base>
    <c:if test="${sessionScope.user.administrator}">
        <div class="jumbotron">
            <%@include file="WEB-INF/chunks/hotelCreationForm.jsp"%>
        </div>
    </c:if>

    <c:forEach items="${requestScope.hotels}" var="hotel">
        <t:hotelCard hotel="${hotel}"/>
        <br>
    </c:forEach>
</t:base>
