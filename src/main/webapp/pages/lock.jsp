<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<c:set var="pageListHolder" value="${locks}" scope="session" />
<spring:url value="/admin/lock" var="pageurl" />
<body>
    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div>
                            <form name="add-new-lock" action="/admin/lock/add" method="post">
                                <div>
                                    <input type="text" class="form-control" name="name"
                                       id="lockField" placeholder="Lock name">
                                </div>
                               <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                        </div>
                            <table class="table">
                                <thead class = "thead-light">
                                    <tr>
                                        <th scope="col">Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="lock" items="${pageListHolder.pageList}">
                                        <tr>
                                        <td>${lock.name}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>