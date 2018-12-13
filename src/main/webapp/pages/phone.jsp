<%@include file="parts/common.jsp" %>
<jsp:include page="parts/header.jsp"/>
<spring:url value="${rootUrl}/admin/phone" var="pageurl"/>
<body id="page-top">
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item active">Phone number editor</li>
</ol>
<div class="container py-4">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div>
                        <form name="add-new-phone" action="${rootUrl}/admin/phone/add" method="post">
                            <div class="form-group">
                                <label for="numberField">Phone</label>
                                <input type="text" class="form-control" name="number"
                                       id="numberField" placeholder="Number">
                            </div>
                            <button type="submit" class="btn btn-primary mb-1 float-right">Add</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <div class="col-sm-9 mx-auto">
            <div class="card mb-10 ">
                <div class="card-header">
                    <i class="fas fa-table "></i>
                    Phones
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="phoneNumbersTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Number</th>
                                <th>Phone</th>
                                <th>In using</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="phone" items="${phones}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${phone.number}</td>
                                    <td>
                                        <c:if test="${phone.contract == null}">
                                            NO
                                        </c:if>
                                        <c:if test="${phone.contract != null}">
                                            YES
                                        </c:if>
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

<jsp:include page="parts/footer.jsp"/>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

</body>
</html>
<script>
    $(document).ready(function () {
        $('#phoneNumbersTable').DataTable();
    });
</script>