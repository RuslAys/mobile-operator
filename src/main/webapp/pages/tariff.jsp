<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<spring:url value="${rootUrl}/admin/tariff/l" var="pageurl" />
<body id="page-top">
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item active">Tariff editor</li>
</ol>
    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div>
                            <form name="add-new-tariff" action="${rootUrl}/admin/tariff/add" method="post" commandName="tariffPlanDto">
                                <div class="form-group">
                                    <label for="tariffNameField">Tariff plan name</label>
                                    <input type="text" class="form-control" name="name"
                                        id="tariffNameField" placeholder="Tariff name">
                                </div>
                                 <div class="form-group">
                                    <label for="tariffPriceField">Tariff price</label>
                                    <input type="text" class="form-control" name="price"
                                        id="tariffPriceField" placeholder="Tariff price">
                                </div>
                                <div class="form-group">
                                    <label for="optionsField">Add options</label>
                                    <select multiple class="form-control" id="optionsField"
                                        name="optionIds">
                                        <c:forEach items="${options}" var="option">
                                            <option value="${option.id}"> <c:out value="${option}"/> </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary float-right mb-1">Add new tariff plan</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9 mx-auto">
                <div class="card mb-10 ">
                    <div class="card-header">
                        <i class="fas fa-table "></i>
                        Tariffs</div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="tariffsTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Archival</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tariff" items="${tariffs}">
                                    <tr>
                                        <form name="remove-tariff" action="${rootUrl}/admin/tariff/remove" method="post">
                                            <input type="hidden" class="form-control" name="tariffId" value="${tariffPlan.id}" id="tariffId">
                                            <td>
                                                <a href="${rootUrl}/admin/tariff/${tariff.id}"><c:out value="${tariff.name}"/></a>
                                            </td>
                                            <td>${tariff.price}</td>
                                            <td>${tariff.archival}</td>
                                            <td>
                                                <button type="submit" class="btn btn-primary">Remove</button>
                                            </td>
                                        </form>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="parts/footer.jsp" />
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
</body>
</html>
<script>
    $(document).ready(function(){
        $('#tariffsTable').DataTable();
    });
</script>