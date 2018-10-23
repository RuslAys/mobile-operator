<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Admin Panel</h1>
    <h2>Add new operator</h2>
    <div>
        <div>
            <form name="add-new-operator" action="admin/add" method="post">
                <div class="form-group">
                    <label for="usernameField">Username</label>
                    <input type="text" class="form-control" name="username"
                        id="usernameField" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="passwordField">Password</label>
                    <input type="password" class="form-control" name="password"
                        id="passwordField" placeholder="Password">
                </div><div class="form-group">
                    <label for="passwordField">Confirm password</label>
                    <input type="password" class="form-control" name="confirmPassword"
                        id="passwordField" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Role</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${operators}" var="operator">
                        <tr>
                            <td><a href="/admin/${operator.username}">
                                    <c:out value="${operator.username}" /> </a> </td>
                                <c:forEach items="${operator.authorities}" var="auth">
                                    <td> <c:out value="${auth.authority}" /></td>
                                </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>