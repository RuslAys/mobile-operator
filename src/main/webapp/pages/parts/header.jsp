<%@include file ="common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MSS</title>
<!-- navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">MSS</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <security:authorize access="hasRole('ROLE_ADMIN')">
                <a class="nav-link" href="/admin">Admin panel</a>
                <a class="nav-link" href="/tariff">Add Tariff plan</a>
                <a class="nav-link" href="/option">Add Option</a>
        </security:authorize>
      </li>
      <li class="nav-item active">
        <security:authorize access="hasRole('ROLE_OPERATOR')">
              <a class="nav-link" href="/sale">Sale contract</a>
        </security:authorize>
      </li>
    </ul>
    <security:authorize access="isAuthenticated()">
        <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
        </form>
    </security:authorize>
  </div>
</nav>
</head>