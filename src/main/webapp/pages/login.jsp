<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
	<h4>Login, please</h4>
    <form action="/loginAction" method="post">
        <div class="form-group">
            <label for="usernameField">Username</label>
            <input type="text" class="form-control" name="username"
                id="usernameField" placeholder="Username">
        </div>
        <div class="form-group">
            <label for="passwordField">Password</label>
            <input type="password" class="form-control" name="password"
                id="passwordField" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>


  ${SPRING_SECURITY_LAST_EXCEPTION.message}
</body>
</html>