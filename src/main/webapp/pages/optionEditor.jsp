<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body id="page-top">
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin/option">Option editor</a>
    </li>
    <li class="breadcrumb-item active">${option.name}</li>
</ol>
${error}
<div id="content-wrapper">
    <div class="container-fluid">
        <div class="container target">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Parent options for ${option.name}</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="parentOptionsTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Connection cost</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Connection cost</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${option.inclusiveOptions}" var="option">
                                <tr>
                                    <td>${option.name}</td>
                                    <td>${option.price}</td>
                                    <td>${option.connectionCost}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Child options for ${option.name}</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="childOptionsTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Connection cost</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Connection cost</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${option.parentInclusive}" var="option">
                                <tr>
                                    <td>${option.name}</td>
                                    <td>${option.price}</td>
                                    <td>${option.connectionCost}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Incompatible options for ${option.name}</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="incompatibleOptionsTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Connection cost</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Connection cost</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${option.exclusiveOptions}" var="option">
                                <tr>
                                    <td>${option.name}</td>
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

<jsp:include page="parts/footer.jsp"/>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>
<script>
    $(document).ready(function() {
        $('#parentOptionsTable').DataTable();
        $('#childOptionsTable').DataTable();
        $('#incompatibleOptionsTable').DataTable();
    });
</script>