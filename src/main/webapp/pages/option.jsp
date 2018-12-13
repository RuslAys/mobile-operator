<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<spring:url value="${rootUrl}/admin/option/l" var="pageurl" />
<body id="page-top">
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item active">Option editor</li>
</ol>
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
                                                id="optionNameField" placeholder="Option`s name" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="price"
                                                id="priceField" placeholder="Price" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="connectionCost"
                                                id="connectionCostField" placeholder="Connection cost" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="inclusiveOptionsField">Inclusive options</label>
                                            <select multiple class="form-control" id="inclusiveOptionsField"
                                                name="inclusiveOptions">
                                                <c:forEach items="${options}" var="option">
                                                    <option value="${option.id}"> <c:out value="${option}"/> </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="exclusiveOptionsField">Exclusive options</label>
                                            <select multiple class="form-control" id="exclusiveOptionsField"
                                                  name="exclusiveOptions">
                                                <c:forEach items="${options}" var="option">
                                                    <option value="${option.id}"> <c:out value="${option}"/> </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary float-right mb-1">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div>
            <div class="row">
                <div class="col-sm-9 mx-auto">
                    <div class="card mb-10 ">
                        <div class="card-header">
                            <i class="fas fa-table "></i>
                            Options</div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="optionsTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Connection cost</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="option" items="${options}">
                                        <tr>
                                            <td>
                                                <a href="${rootUrl}/admin/option/${option.id}"><c:out value="${option   .name}"/></a>
                                            </td>
                                            <td>${option.price}</td>
                                            <td>${option.connectionCost}</td>
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
    </div>
<jsp:include page="parts/footer.jsp"/>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>
</html>
<script>
    $(document).ready(function(){
        $('#optionsTable').DataTable();
    });
</script>