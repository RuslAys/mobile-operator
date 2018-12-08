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
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">Current tariff plan: ${contract.tariffPlan.name}</h1>
    </div>
    <div class="container">
        <div class="card-deck mb-3 text-center">
            <c:forEach items="${freeTariffs}" var="tariffPlan">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-bold">${tariffPlan.name}</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">Price: ${tariffPlan.price}</h1>
                    </div>
                    <button type="button" onclick="chooseTariff(${tariffPlan.id})" class="btn btn-lg btn-block btn-primary">Choose</button>
                </div>
            </c:forEach>
        </div>
        <div>
            <div class="card mb-3">
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

<div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <form name="change-tariff" action="tariff/change" method="post">
                            <div class="form-group">
                                <input type="hidden" class="form-control" name="terminalDeviceId"
                                            value = "${terminalDevice.id}"id="terminalDeviceId" placeholder="${terminalDevice.id}">
                                <input type="hidden" class="form-control" name="tariffPlanId"
                                            value = "${tariffPlan.id}"id="tariffPlanId" placeholder="${tariffPlan.id}">
                                <label for="freeLocksField">Change tariff</label>
                                <select class="form-control" id="freeLocksField"
                                    name="newTariffId">
                                    <c:forEach items="${freeTariffs}" var="tariffPlan">
                                        <option value="${tariffPlan.id}"> <c:out value="${tariffPlan.name} Price: ${tariffPlan.price}"/> </option>
                                    </c:forEach>
                                </select>
                              </div>
                                <button type="submit" name="confirm" class="btn btn-primary">Confirm</button>
                                <button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
</div>
</body>
<script type="text/javascript">
    function chooseTariff(id) {
        var tariffUrl = "${rootUrl}/rest/tariffs/" + id;
        var optionsTable = $('#optionsTable').DataTable({
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