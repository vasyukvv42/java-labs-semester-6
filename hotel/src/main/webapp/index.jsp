<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-05-31
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base>
    <div class="jumbotron">
        <h1>Hello, ${sessionScope.user == null ? "world" : sessionScope.user.username}!</h1>
        <c:if test="${sessionScope.user.client}">
            <br>
            <h3>Current balance: ${sessionScope.user.balance}$</h3>
            <h3>Refill balance</h3>
            <form class="form-inline" method="post" action="${pageContext.request.contextPath}/app/refill-balance">

                <div class="input-group mb-2 mr-sm-2">
                    <input type="number" class="form-control" name="amount" placeholder="00.00" required>
                    <div class="input-group-append">
                        <div class="input-group-text">$</div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mb-2">Refill</button>
            </form>
        </c:if>
    </div>
</t:base>
