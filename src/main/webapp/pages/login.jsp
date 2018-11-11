<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
	<div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <!-- form card login -->
                        <div class="card rounded-1">
                            <div class="card-header">
                                <h3 class="mb-0">Login</h3>
                            </div>
                            <div class="card-body">
                                <form class="form" role="form" id="formLogin" action="/login" method="post">
                                    <div class="form-group">
                                        <label for="uname1">Username</label>
                                        <input type="text" class="form-control form-control-lg rounded-1" name="username" id="uname1">
                                        <div class="invalid-feedback">Oops, you missed this one.</div>
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="password" class="form-control form-control-lg rounded-1" name="password" id="pwd1">
                                        <div class="invalid-feedback">Enter your password too!</div>
                                    </div>
                                    <button type="submit" class="btn btn-success btn-lg float-right" id="btnLogin">Login</button>
                                </form>
                            </div>
                            <!--/card-block-->
                        </div>
                        <!-- /form card login -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/container-->

  ${SPRING_SECURITY_LAST_EXCEPTION.message}
</body>
</html>