<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Sale contract</h1>
    <h2>Add new customer</h2>
    <div>
        <div>
            <form name="add-new-customer" action="sale/add" method="post">
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
                <div class="form-group">
                    <label for="optionsField">Add tariff</label>
                    <select class="form-control" id="tariffField"
                        name="tariffs">
                        <c:forEach items="${tariffs}" var="tariff">
                            <option value="${tariff.id}"> <c:out value="${tariff}"/> </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>

        <!--
        <div>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Active</th>
                    <th>Role</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${operators}" var="operator">
                        <tr>
                            <td><a href="operator/${operator.username}">
                                    <c:out value="${operator.username}" /> </a> </td>
                            <td><c:out value="${operator.enabled}" /> </td>
                                <c:forEach items="${operator.authorities}" var="auth">
                                    <td> <c:out value="${auth.authority}" /></td>
                                </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        -->
    </div>
</body>
</html>