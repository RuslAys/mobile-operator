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
    <li class="breadcrumb-item active">Options management</li>
</ol>

<div id="content-wrapper">
    <div class="container-fluid">
        <div class="container target">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Options on contract</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="optionsOnContractTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${options}" var="option">
                                <tr>
                                    <td>${option.name}</td>
                                    <td>${option.price}</td>
                                    <td>
                                        <form name="add-option" action="${rootUrl}/profile/${contract.phoneNumber.number}/option/remove" method="post">
                                                <input type="hidden" class="form-control" name="contractId"
                                                       value = "${contract.id}"id="contractId" placeholder="${contract.id}">
                                                <input type="hidden" class="form-control" name="optionId"
                                                       value = "${option.id}"id="optionId" placeholder="${option.id}">
                                            <button type="submit" name="confirm" class="btn btn-primary">Confirm removing</button>
                                            <button type="submit" name="add_to_cart" class="btn btn-primary">Add removing to cart</button>
                                            <button type="button" class="btn btn-primary" onclick="showOptionsToDelete(${option.id})">Show options</button>
                                        </form>
                                    </td>
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
                    Available options</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="availableOptions" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${freeOptions}" var="option">
                                <tr>
                                    <td>${option.name}</td>
                                    <td>${option.price}</td>
                                    <td>
                                        <form name="add-option" action="${rootUrl}/profile/${contract.phoneNumber.number}/option/add" method="post">
                                            <input type="hidden" class="form-control" name="contractId"
                                                   value = "${contract.id}"id="freeOptionsContractId" placeholder="${contract.id}">
                                            <input type="hidden" class="form-control" name="optionId"
                                                   value = "${option.id}"id="freeOptionsOptionId" placeholder="${option.id}">
                                            <button type="submit" name="confirm" class="btn btn-primary">Confirm adding</button>
                                            <button type="submit" name="add_to_cart" class="btn btn-primary">Add adding to cart</button>
                                            <button type="button" class="btn btn-primary" onclick="showOptionsToAdd(${option.id})">Show options</button>
                                            <button type="button" class="btn btn-primary" onclick="showExclusiveOptions(${option.id})">Show ex options</button>
                                        </form>
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
    <%--<div class="container py-4">--%>
        <%--<div class="row">--%>
            <%--<div class="col-md-12">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-5 mx-auto">--%>
                        <%--<form name="add-option" action="option/add" method="post">--%>
                            <%--<div class="form-group">--%>
                                <%--<input type="hidden" class="form-control" name="contractId"--%>
                                    <%--value = "${terminalDevice.id}"id="contractId" placeholder="${terminalDevice.id}">--%>
                                <%--<input type="hidden" class="form-control" name="username"--%>
                                    <%--value = "${terminalDevice.phoneNumber.number}"id="phoneNumber" placeholder="${terminalDevice.phoneNumber.number}">--%>
                                <%--<label for="freeOptionsField">Add option</label>--%>
                                <%--<select class="form-control" id="freeOptionsField"--%>
                                    <%--name="optionId">--%>
                                    <%--<c:forEach items="${freeOptions}" var="option">--%>
                                        <%--<option value="${option.id}"> <c:out value="${option.name} Price: ${option.price} Connection cost ${option.connectionCost}"/> </option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                             <%--</div>--%>
                            <%--<button type="submit" name="confirm" class="btn btn-primary">Confirm</button>--%>
                            <%--<button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>--%>
                        <%--</form>--%>
                    <%--</div>--%>

    <%--&lt;%&ndash;<div class="container py-4">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="col-md-5 mx-auto">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<form name="remove-option" action="option/remove" method="post">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<input type="hidden" class="form-control" name="contractId"&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;value = "${terminalDevice.id}"id="contractId" placeholder="${terminalDevice.id}">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<label for="optionsField">Delete option</label>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<select class="form-control" id="optionsField"&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;name="optionId">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<c:forEach items="${options}" var="option">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<option value="${option.id}"> <c:out value="${option.name} Price: ${option.price}"/> </option>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<button type="submit" name="confirm" class="btn btn-primary">Confirm</button>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>
<script>
    $(document).ready(function() {
        $('#optionsOnContractTable').DataTable();
        $('#availableOptions').DataTable();
    });

    function showOptionsToDelete(id) {
        var url = "${rootUrl}/rest/options/contract/inclusive/delete?contractId=" + ${contract.id} + "&optionIds=" + id;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
            }
        });
    }

    function showOptionsToAdd(id) {
        var url = "${rootUrl}/rest/options/contract/inclusive/add?contractId=" + ${contract.id} + "&optionIds=" + id;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
            }
        });
    }

    function showExclusiveOptions(id) {
        var url = "${rootUrl}/rest/options/contract/exclusive?contractId=" + ${contract.id} + "&optionIds=" + id;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
            }
        });
    }
</script>