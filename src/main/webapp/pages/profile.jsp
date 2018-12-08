<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<style>
    #content-wrapper{
        overflow-y: hidden;
    }
</style>
<body id="page-top">
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item active">${contract.phoneNumber.number}</li>
</ol>
<div id="content-wrapper">
    <div class="container-fluid">
        <hr class="">
        <div class="container target">
            <div class="row">
                <div class="col-sm-3">
                    <h2 class="">${contract.phoneNumber.number}</h2>
                </div>
                <form class="form" role="form" id="formLock" action="${rootUrl}/profile/${contract.phoneNumber.number}/lock" method="post">
                    <input hidden class="form-control form-control-lg rounded-1" name="contractId" value="${contract.id}">
                    <button type="submit" class="btn btn-success" id="btnLock">Lock / Unlock</button>
                </form>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <ul class="list-group">
                        <li class="list-group-item text-muted" contenteditable="false">Profile</li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">Name:</strong></span>
                            ${contract.customer.firstName}
                        </li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">Last Name:</strong></span>
                            ${contract.customer.lastName}
                        </li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">Birth date:</strong></span>
                            <fmt:formatDate value="${contract.customer.birthDate}"
                                            pattern="dd.MM.yyyy"/>
                        </li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">Passport:</strong></span>
                            ${contract.customer.passport}
                        </li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">City:</strong></span>
                            ${contract.customer.address.city}
                        </li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">Street:</strong></span>
                            ${contract.customer.address.street}
                        </li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">House:</strong></span>
                            ${contract.customer.address.houseNumber}
                        </li>
                        <li class="list-group-item">
                            <span class="pull-left"><strong class="">Email:</strong></span>
                            ${contract.customer.email}
                        </li>
                    </ul>
                </div>
                <div class="col-sm-9" contenteditable="false">
                    <div class="row">
                        <div class="col-xl-3 col-sm-6 mb-3">
                            <div class="card text-white bg-primary o-hidden h-100">
                                <div class="card-body">
                                    <div class="mr-5">Options: ${fn:length(contract.options)}</div>
                                </div>
                                <a class="card-footer text-white clearfix small z-1" href="${rootUrl}/profile/${contract.phoneNumber.number}/option">
                                    <span class="float-left">Option management</span>
                                    <span class="float-right"><i class="fas fa-angle-right"></i></span>
                                </a>
                            </div>
                        </div>
                        <c:if test="${contract.tariffPlan.archival==false}">
                            <div class="col-xl-3 col-sm-6 mb-3">
                                <div class="card text-white bg-primary o-hidden h-100 ">
                                    <div class="card-body">
                                        <div class="mr-5">Tariff plan: ${contract.tariffPlan.name}</div>
                                    </div>
                                    <a class="card-footer text-white clearfix small z-1" href="${rootUrl}/profile/${contract.phoneNumber.number}/tariff">
                                        <span class="float-left">Change tariff plan</span>
                                        <span class="float-right"><i class="fas fa-angle-right"></i></span>
                                    </a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${contract.tariffPlan.archival==true}">
                            <div class="col-xl-3 col-sm-6 mb-3">
                                <div class="card text-white bg-danger o-hidden h-100">
                                    <div class="card-body">
                                        <div class="mr-5">Tariff plan: ${contract.tariffPlan.name}
                                            <h3>Tariff plan is archival</h3>
                                        </div>
                                    </div>
                                    <a class="card-footer text-white clearfix small z-1" href="${rootUrl}/profile/${contract.phoneNumber.number}/tariff">
                                        <span class="float-left">Change tariff plan</span>
                                        <span class="float-right"><i class="fas fa-angle-right"></i></span>
                                    </a>
                                </div>
                            </div>
                        </c:if>
                        <div class="col-xl-3 col-sm-6 mb-3">
                            <div class="card text-white bg-success o-hidden h-100">
                                <div class="card-body">
                                    <div class="mr-5">Balance: ${contract.balance}</div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${contract.locked==true}">
                            <div class="col-xl-3 col-sm-6 mb-3">
                                <div class="card text-white bg-danger o-hidden h-100">
                                    <div class="card-body">
                                        <div class="mr-5">Locked</div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="row">
                        <div class="col-sm-8">
                            <div class="card mb-10">
                                <div class="card-header">
                                    <i class="fas fa-table"></i>
                                    Current options</div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="optionsTable" width="100%" cellspacing="0">
                                            <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Price</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="option" items="${contract.options}">
                                                <tr>
                                                    <td>${option.name}</td>
                                                    <td>${option.price}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <security:authorize access="hasRole('ROLE_OPERATOR')">
                            <div class = "col-sm-4">
                                <ul class="list-group">
                                    <c:forEach items="${anotherContracts}" var="cntr" varStatus="loop">
                                        <li class="list-group-item">
                                            <a class="nav-link" href="/profile/${cntr.phoneNumber.number}">${cntr.phoneNumber.number}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </security:authorize>
                    </div>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Chart of account changes
                </div>
                <div class="card-body">
                    <canvas id="myAreaChart" width="100%" height="30"></canvas>
                </div>
                <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
            </div>

            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Invoices</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="billsTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Position</th>
                                <th>Office</th>
                                <th>Age</th>
                                <th>Start date</th>
                                <th>Salary</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Position</th>
                                <th>Office</th>
                                <th>Age</th>
                                <th>Start date</th>
                                <th>Salary</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td>Tiger Nixon</td>
                                <td>System Architect</td>
                                <td>Edinburgh</td>
                                <td>61</td>
                                <td>2011/04/25</td>
                                <td>$320,800</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
            </div>
        </div>
        <jsp:include page="parts/footer.jsp" />
    </div>
</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>

<script src='<spring:url value="/resources/js/demo/chart-area-demo.js"/>'></script>
<script src='<spring:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js"/>'></script>
</html>