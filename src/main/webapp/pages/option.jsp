<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<c:set var="pageListHolder" value="${options}" scope="session" />
<spring:url value="${rootUrl}/admin/option" var="pageurl" />
<body>
    <div class="container py-4">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-5 mx-auto">
                            <div>
                                <div>
                                    <form name="add-new-option" action="${rootUrl}/admin/option/add" method="post">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="name"
                                                id="optionNameField" placeholder="Option`s name">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="price"
                                                id="priceField" placeholder="Price">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="connectionCost"
                                                id="connectionCostField" placeholder="Connection cost">
                                        </div>
                                        <div class="form-group">
                                            <label for="inclusiveOptionsField">Inclusive options</label>
                                            <select multiple class="form-control" id="inclusiveOptionsField"
                                                name="inclusiveOptions">
                                                <c:forEach items="${pageListHolder.source}" var="option">
                                                    <option value="${option.id}"> <c:out value="${option}"/> </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="exclusiveOptionsField">Exclusive options</label>
                                            <select multiple class="form-control" id="exclusiveOptionsField"
                                                  name="exclusiveOptions">
                                                <c:forEach items="${pageListHolder.source}" var="option">
                                                    <option value="${option.id}"> <c:out value="${option}"/> </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div>

        <table class="table">
            <thead class = "thead-light">
                <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Price</th>
                  <th scope="col">Connection cost</th>
                </tr>
              </thead>
            <tbody>
            <c:forEach var="option" items="${pageListHolder.pageList}">
                <tr>
                <td>${option.name}</td>
                <td>${option.price}</td>
                <td>${option.connectionCost}</td>
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
</body>
</html>