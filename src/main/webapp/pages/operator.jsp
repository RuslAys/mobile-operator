<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<c:set var="pageListHolder" value="${operators}" scope="session" />
<spring:url value="/admin/operator/l" var="pageurl" />
<body>
    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div>
                            <div>
                                <form name="add-new-operator" action="/admin/operator/add" method="post">
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
                                    <button type="submit" class="btn btn-primary">Add</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div>
    </div>
           <table class="table">
                 <thead>
                   <tr>
                     <th scope="col">Name</th>
                     <th scope="col">Active</th>
                     <th scope="col">Role</th>
                   </tr>
                 </thead>
                <tbody>
                <c:forEach var="operator" items="${pageListHolder.pageList}">
                    <tr>
                    <td> <a href="/admin/operator/${operator.username}" >${operator.username} </a></td>
                    <td>${operator.enabled}</td>
                    <c:forEach items="${operator.authorities}" var="auth">
                       <td> <c:out value="${auth.authority}" /></td>
                    </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
        <span style="float:left;">
        <c:choose>
            <c:when test="${pageListHolder.firstPage}">Prev</c:when>
            <c:otherwise><a href="${pageurl}/prev">Prev</a></c:otherwise>
        </c:choose>
        </span>
        <span>
        <c:forEach begin="0" end="${pageListHolder.pageCount-1}" varStatus="loop">
        &nbsp;&nbsp;
        <c:choose>
            <c:when test="${loop.index == pageListHolder.page}">${loop.index+1}</c:when>
            <c:otherwise><a href="${pageurl}/${loop.index}">${loop.index+1}</a></c:otherwise>
        </c:choose>
        &nbsp;&nbsp;
        </c:forEach>
        </span>
        <span>
        <c:choose>
            <c:when test="${pageListHolder.lastPage}">Next</c:when>
            <c:otherwise><a href="${pageurl}/next">Next</a></c:otherwise>
        </c:choose>
        </span>
        </div>
</body>
</html>