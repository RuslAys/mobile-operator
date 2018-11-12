<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<c:set var="pageListHolder" value="${numbers}" scope="session" />
<spring:url value="/admin/phone" var="pageurl" />
<body>
    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div>
                            <form name="add-new-phone" action="/admin/phone/add" method="post">
                                <div class="form-group">
                                    <label for="numberField">Phone</label>
                                    <input type="text" class="form-control" name="number"
                                        id="numberField" placeholder="Number">
                                </div>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
        <table class="table">
             <thead class = "thead-light">
               <tr>
                 <th scope="col">Phones</th>
               </tr>
             </thead>
            <tbody>
            <c:forEach var="phoneNumber" items="${pageListHolder.pageList}">
                <tr>
                    <td>${phoneNumber.number}</td>
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
        </div>
    </div>

</body>
</html>