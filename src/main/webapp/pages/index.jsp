<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body id="page-top">
    <ol class="breadcrumb">
        <li class="breadcrumb-item active">Profile</li>
    </ol>
    <div id="content-wrapper">
        <div class="container-fluid">
            <div class="container target">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fas fa-table"></i>
                        Customers</div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="customerTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Birth Date</th>
                                    <th>Contract</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Birth Date</th>
                                    <th>Contract</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${customers}" var="customer">
                                    <%--<option value="${option.id}"> <c:out value="${option.name} Price: ${option.price} Connection cost ${option.connectionCost}"/> </option>--%>
                                    <tr>
                                        <td>${customer.firstName}</td>
                                        <td>${customer.lastName}</td>
                                        <td><fmt:formatDate value="${customer.birthDate}"
                                                            pattern="dd.MM.yyyy"/></td>
                                        <td>
                                            <ul class="list-group">
                                                <c:forEach items="${customer.contracts}" var="contract">
                                                    <li class="list-group-item">
                                                        <a href="${rootUrl}/profile/${contract.phoneNumber.number}">${contract.phoneNumber.number}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </td>
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
<script>
    $(document).ready(function() {
        $('#customerTable').DataTable();
    });
</script>
</html>