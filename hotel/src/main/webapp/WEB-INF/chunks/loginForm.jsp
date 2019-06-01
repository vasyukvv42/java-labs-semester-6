<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-05-31
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<form class="form-signin" method="post">
    <h1 class="h3 mb-3 font-weight-normal">Log in</h1>

    <label for="inputEmail" class="sr-only">Username or email</label>
    <input type="text" id="inputEmail" class="form-control" name="usernameOrEmail" placeholder="Username or email" required autofocus>

    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
    <p>Don't have an account? <a href="${pageContext.request.contextPath}/app/signup">Sign up!</a></p>
</form>

