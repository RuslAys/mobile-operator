<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body id="page-top">
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/profile/${contract.phoneNumber.number}">${contract.phoneNumber.number}</a>
    </li>
    <li class="breadcrumb-item active">Change tariff plan</li>
</ol>
<div class="content-wrapper">
    <div class="pricing-header px-2 py-2 pt-sm-2 pb-sm-3 mx-auto text-center">
        <h1 class="display-4">Current tariff plan: ${contract.tariffPlan.name}</h1>
    </div>
    <div class="container">
        <div class="card-columns" id="freeTariffs">
            <c:forEach items="${freeTariffs}" var="tariffPlan">
                <div class="card shadow-sm p-2 col-sm-4 border-right">
                    <div class="card-header">
                        <h5 class="my-0 font-weight-bold">${tariffPlan.name}</h5>
                    </div>
                    <div class="card-body">
                        <h3 class="card-title pricing-card-title">Price: ${tariffPlan.price}</h3>
                    </div>
                    <button type="button" onclick="chooseTariff(${tariffPlan.id})" class="btn btn-md border-bottom der-bot btn-primary scroll-to-bot rounded" href="#optionsTable">Show options</button>
                    <form name="change-tariff" action="${rootUrl}/profile/${contract.phoneNumber.number}/tariff/change" method="post">
                        <input type="hidden" class="form-control" name="contractId"
                               value = "${contract.id}"id="contractId" placeholder="${contract.id}">
                        <input type="hidden" class="form-control" name="newTariffId"
                               value = "${tariffPlan.id}"id="tariffPlanId" placeholder="${tariffPlan.id}">
                        <%--<button type="submit" class="btn btn-md border-top btn-primary">Change</button>--%>
                        <button type="submit" name="confirm" class="btn btn-md border-top btn-primary">Confirm changing</button>
                        <button type="submit" name="add_to_cart" class="btn btn-md border-top btn-primary">Add changing to cart</button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Tariff options</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="optionsTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>
<script src='<spring:url value="/resources/js/senzill-pagination.js"/>'></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#optionsTable').DataTable();
        $(document).ready(function(){
            $('#freeTariffs').senzill({
                elPerPage: 3
            });
        });
    });
    function chooseTariff(id) {
        var tariffUrl = "${rootUrl}/rest/tariffs/" + id;
        $('#optionsTable').DataTable({
            destroy:true,
            processing: true,
            ajax: {
                url: tariffUrl,
                dataSrc: "options",
            },
            columns: [
                {data: "name"},
                {data: "price"}
            ]
        });
    }
</script>