<%@include file ="common.jsp"%>
<c:set var="cart" value="${cart}" scope="session" />
<html>
<head>

<title>MSS</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <link href='<spring:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href='<spring:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>' rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href='<spring:url value="/resources/vendor/datatables/dataTables.bootstrap4.css"/>' rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href='<spring:url value="/resources/css/sb-admin.css"/>' rel="stylesheet">

    <!-- Bootstrap core JavaScript-->
    <script src='<spring:url value="/resources/vendor/jquery/jquery.min.js"/>'></script>
    <script src='<spring:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>

    <!-- Core plugin JavaScript-->
    <script src='<spring:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>'></script>

    <!-- Page level plugin JavaScript-->
    <script src='<spring:url value="/resources/vendor/chart.js/Chart.min.js"/>'></script>
    <script src='<spring:url value="/resources/vendor/datatables/jquery.dataTables.js"/>'></script>
    <script src='<spring:url value="/resources/vendor/datatables/dataTables.bootstrap4.js"/>'></script>

    <!-- Custom scripts for all pages-->
    <script src='<spring:url value="/resources/js/sb-admin.min.js"/>'></script>

<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="${rootUrl}">MSS</a>

    <security:authorize access="isAuthenticated()">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li class="nav-item active">
                <a class="nav-link" href="${rootUrl}/admin">Admin panel</a>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_OPERATOR')">
            <li class="nav-item active">
                <a class="nav-link" href="${rootUrl}/sale">Sale contract</a>

            </li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li class="nav-item active">
                <a class="nav-link" href="${rootUrl}/cart">Cart quantity: ${cart.quantity}</a>
            </li>
        </security:authorize>
    </ul>
    <!-- Navbar -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user-circle fa-fw"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="${rootUrl}/profile/<security:authentication property="principal.username"/>">Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
                    </div>
                </li>
            </ul>
        </security:authorize>
    </form>

</nav>
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <form class="form-inline my-2 my-lg-0" action="${rootUrl}/logout" method="post">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </div>
</div>
</head>
</html>