<jsp:include page="parts/header.jsp" />
<body>
    <form id="search-form" action="/search" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="username"
                id="usernameField" placeholder="Username">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    ${user}
</body>
</html>