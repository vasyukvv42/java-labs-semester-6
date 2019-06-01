<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>

<form class="form-signup" method="post">
    <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>

    <label for="inputUsername" class="sr-only">Username</label>
    <input type="text" id="inputUsername" class="form-control" name="username" placeholder="Username" required autofocus>

    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address" required>

    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
</form>
