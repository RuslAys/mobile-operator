<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin/tariff">Tariff editor</a>
    </li>
    <li class="breadcrumb-item active">${tariff.name}</li>
</ol>

<div class="content-wrapper">
    <div class="pricing-header px-2 py-2 pt-sm-2 pb-sm-3 mx-auto text-center">
        <h1 class="display-4">Tariff plan editor</h1>
    </div>
    <div class="container">
        <div>
            <div>
                <form name="remove-option" action="${rootUrl}/admin/tariff/${tariff.id}/remove" method="post">
                    <div class="form-group">
                        <label for="tariffOptionsField">Tariff plan`s options</label>
                        <select class="form-control" id="tariffOptionsField"
                                name="optionId">
                            <c:forEach items="${tariff.options}" var="option">
                                <option value="${option.id}"> <c:out value="${option}"/> </option>
                            </c:forEach>
                        </select>
                    </div>
                    <c:if test="${tariff.archival == true}">
                        <button type="submit" class="btn btn-primary" disabled>Archival tariff plan</button>
                    </c:if>
                    <c:if test="${tariff.archival == false}">
                        <button type="submit" class="btn btn-primary">Remove</button>
                    </c:if>
                </form>
            </div>
        </div>
        <div>
            <form name="add-new-option" action="${rootUrl}/admin/tariff/${tariff.id}/add" method="post">
                <div class="form-group">
                    <label for="freeOptionsField">Add options</label>
                    <select class="form-control" id="freeOptionsField"
                            name="optionId">
                        <c:forEach items="${freeOptions}" var="option">
                            <option value="${option.id}"> <c:out value="${option}"/> </option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${tariff.archival == true}">
                    <button type="submit" class="btn btn-primary" disabled>Archival tariff plan</button>
                </c:if>
                <c:if test="${tariff.archival == false}">
                    <button type="submit" class="btn btn-primary">Add</button>
                </c:if>
            </form>
        </div>
    </div>
</div>

</div>
</body>
</html>