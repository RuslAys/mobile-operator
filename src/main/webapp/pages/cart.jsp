<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body id= "page-top">
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item active">Cart</li>
</ol>
<div id="content-wrapper">
    <div class="container-fluid">
        <div class="col-sm-6 mx-auto mb-1">
            <div class="card mb-10">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Cart</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="cartItemsTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Number</th>
                                <th>Item</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${cart.cartItems}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>
                                        <c:if test="${item.cartItemOperationType != 'LOCK'}">
                                            ${item.cartItemOperationType.toString()}
                                        </c:if>
                                            ${item.title}
                                    </td>
                                    <td>
                                        <form name="confirm" action="cart/remove" method="post">
                                            <input type="hidden" class="form-control" name="itemId"
                                                   id="itemField" value="${item.id}" readonly/>
                                            <button type="submit" class="btn btn-primary">Remove</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <form name="confirm" action="cart/confirm" method="post">
                    <button type="submit" class="btn btn-primary float-right mb-1 mr-1">Confirm all</button>
                </form>
            </div>
        </div>

        <div class="col-sm-6 mx-auto">
            <div class="card mb-10">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Results</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="resultItemsTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Number</th>
                                <th>Item</th>
                                <th>Result</th>
                                <th>Message</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${cartResult.cartItems}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>
                                        <c:if test="${item.cartItemOperationType != 'LOCK'}">
                                            ${item.cartItemOperationType.toString()}
                                        </c:if>
                                            ${item.title}
                                    </td>
                                    <td>${item.result}</td>
                                    <td>${item.resultMessage}</td>
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
    $(document).ready(function(){
        $('#cartItemsTable').DataTable();
        $('#resultItemsTable').DataTable();
    });
</script>