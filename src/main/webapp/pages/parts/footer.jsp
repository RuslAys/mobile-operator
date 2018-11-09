<%@include file ="common.jsp"%>

<nav class="navbar fixed-bottom navbar-expand-sm navbar-light bg-light">
  <a class="navbar-brand" href="/cart">Cart</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarCollapse">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        ${cart}
      </li>
    </ul>
  </div>
</nav>