<%@include file ="common.jsp"%>
<c:set var="cart" value="${cart}" scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MSS</title>
<!-- navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${rootUrl}">MSS</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <security:authorize access="hasRole('ROLE_ADMIN')">
                <a class="nav-link" href="${rootUrl}/admin">Admin panel</a>
            ${rootUrl}
        </security:authorize>
      </li>
      <li class="nav-item active">
        <security:authorize access="hasRole('ROLE_OPERATOR')">
              <a class="nav-link" href="${rootUrl}/sale">Sale contract</a>
        </security:authorize>
      </li>
      <security:authorize access="isAuthenticated()">
          <li class="nav-item active">
                <a class="nav-link" href="${rootUrl}/cart">Cart quantity: ${cart.quantity}</a>
          </li>
      </security:authorize>
    </ul>
    <security:authorize access="isAuthenticated()">
        Logged as
        <a class="nav-link"
            href="${rootUrl}/profile/<security:authentication property="principal.username"/>">
            <security:authentication property="principal.username"/>
        </a>
        <form class="form-inline my-2 my-lg-0" action="${rootUrl}/logout" method="post">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
        </form>
    </security:authorize>
  </div>
</nav>
</head>