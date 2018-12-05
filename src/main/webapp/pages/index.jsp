<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
<div class="container py-4">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <form id="search-form" action="${rootUrl}/search" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="username"
                                id="usernameField" placeholder="Username">
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                    <a href="${rootUrl}/profile/${user.username}">${user.username}</a>
                </div>
            </div>
        </div>
    </div>
</body>

</html>