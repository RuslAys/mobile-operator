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
                <div class="col-sm-4">
                    <h2 class="">${contract.phoneNumber.number}</h2>
                </div>
                <form class="form" role="form" id="formLock" action="${rootUrl}/profile/${contract.phoneNumber.number}/lock" method="post">
                    <input hidden class="form-control form-control-lg rounded-1" name="contractId" value="${contract.id}">
                    <button type="submit" class="btn btn-success" id="btnLockConfirm" name="confirm">Confirm Lock / Unlock</button>
                    <button type="submit" class="btn btn-success" id="btnLockAddToCart" name="add_to_cart">Add to cart Lock / Unlock</button>
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
                                            <h5>Tariff plan is archival</h5>
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
                        <div class="col-sm-9">
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
                            <div class = "col-sm-3">
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
            <div class="card mb-3 border-top">
                <div class="card-header">
                    <i class="fas fa-chart-area"></i>
                    Chart of account changes
                </div>
                <div class="card-body">
                    <canvas id="balanceChart" width="100%" height="30"></canvas>
                </div>
                <div id="balanceChartDate" class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
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
                                <th>Date</th>
                                <th>Difference</th>
                                <th>Operation</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Date</th>
                                <th>Difference</th>
                                <th>Operation</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="billsTableDate" class="card-footer small text-muted"></div>
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

<script>
    $(document).ready(function() {
        var billsUrl = "${rootUrl}/rest/bills/${contract.phoneNumber.number}?contractId=${contract.id}";
        $('#billsTable').DataTable({
            destroy:true,
            processing: true,
            ajax: {
                url: billsUrl,
                dataSrc: ""
            },
            columns: [
                {
                    data: "date",
                    render: function (data) {
                        var date = new Date(data);
                        var month = date.getMonth() + 1;
                        var zero = ((date.getMinutes() < 10) ? "0" : "");
                        return date.getDate() + "/" + month + "/" + date.getFullYear() + " "
                            + date.getHours() + ":" + zero +date.getMinutes();
                    }
                },
                {data: "difference"},
                {data: "operation"}
            ]
        });

        var date = new Date();
        var month = date.getMonth() + 1;
        var zero = ((date.getMinutes() < 10) ? "0" : "");
        $("#billsTableDate").html("Updated: " + date.getDate() + "/" + month + "/" + date.getFullYear() + " "
            + date.getHours() + ":" + zero + date.getMinutes());

        $('#optionsTable').DataTable();
    });
</script>
<script>
    $(document).ready(function(){
        var billsUrl = "${rootUrl}/rest/bills/${contract.phoneNumber.number}?contractId=${contract.id}";
        $.ajax({
            type: 'GET',
            url: billsUrl,
            success: function(data){
                var bills = JSON.parse(data);

                //TODO need to refactor:

                var pos = [];
                for(var i = 0; i < bills.length; i++){
                    var billDate = new Date(bills[i].date);
                    var month = billDate.getMonth() + 1;
                    var zero = ((billDate.getMinutes() < 10) ? "0" : "");
                    var stringDate = billDate.getDate() + "/" + month + "/" + billDate.getFullYear() + " "
                    + billDate.getHours() + ":" + zero + billDate.getMinutes();
                    pos.push({
                        key: stringDate,
                        value: bills[i].balance,
                    });
                }

                var res = pos.reduce((acc, cur) => ({...acc, [cur.key]: cur.value}), {});

                // Set new default font family and font color to mimic Bootstrap's default styling
                Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
                Chart.defaults.global.defaultFontColor = '#292b2c';

                var ctx = document.getElementById("balanceChart");
                var myLineChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels:  Object.keys(res), /*["Mar 1", "Mar 2", "Mar 3", "Mar 4", "Mar 5", "Mar 6", "Mar 7", "Mar 8", "Mar 9", "Mar 10", "Mar 11", "Mar 12", "Mar 13"]*/
                        datasets: [{
                            label: "Balance",
                            lineTension: 0.3,
                            backgroundColor: "rgba(2,117,216,0.2)",
                            borderColor: "rgba(2,117,216,1)",
                            pointRadius: 5,
                            pointBackgroundColor: "rgba(2,117,216,1)",
                            pointBorderColor: "rgba(255,255,255,0.8)",
                            pointHoverRadius: 5,
                            pointHoverBackgroundColor: "rgba(2,117,216,1)",
                            pointHitRadius: 50,
                            pointBorderWidth: 2,
                            data:  Object.values(res),
                        }],
                    },
                    options: {
                        scales: {
                            xAxes: [{
                                time: {
                                    unit: 'date'
                                },
                                gridLines: {
                                    display: false
                                },
                                ticks: {
                                    maxTicksLimit: 7
                                }
                            }],
                            yAxes: [{
                                ticks: {
                                    min: -1000,
                                    max: 1000,
                                    maxTicksLimit: 5
                                },
                                gridLines: {
                                    color: "rgba(0, 0, 0, .125)",
                                }
                            }],
                        },
                        legend: {
                            display: false
                        }
                    }
                });
            }
        });

    });

    var date = new Date();
    var month = date.getMonth() + 1;
    var zero = ((date.getMinutes() < 10) ? "0" : "");
    $("#balanceChartDate").html("Updated: " + date.getDate() + "/" + month + "/" + date.getFullYear() + " "
        + date.getHours() + ":" + zero + date.getMinutes());
</script>
</html>