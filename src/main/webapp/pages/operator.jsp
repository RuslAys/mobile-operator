<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<spring:url value="/admin/operator/l" var="pageurl" />
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item active">Operator editor</li>
</ol>
<div id="content-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div>
                            <div>
                                <form name="add-new-operator" action="${rootUrl}/admin/operator/add" method="post">
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
                                            id="confirmPasswordField" placeholder="Password">
                                    </div>
                                    <button type="submit" class="btn btn-primary mb-1">Add</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-9 mx-auto">
                        <div class="card mb-10 ">
                            <div class="card-header">
                                <i class="fas fa-table "></i>
                                Operators</div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="operatorsTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Active</th>
                                            <th>Role</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="operator" items="${operators}">
                                            <tr>
                                                <td>
                                                    <a href="${rootUrl}/admin/operator/${operator.username}"><c:out value="${operator.username}"/></a>
                                                </td>
                                                <td>${operator.enabled}</td>
                                                <td>
                                                    <c:forEach items="${operator.authorities}" var="auth">
                                                        <c:out value="${auth.authority}" />
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div>
    </div>
</div>
</div>
</div>
</body>
</html>
<script>
    $(document).ready(function(){
        $('#operatorsTable').DataTable();
    });
</script>