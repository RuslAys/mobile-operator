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
    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <form name="add-option" action="option/add" method="post">
                            <div class="form-group">
                                <input type="hidden" class="form-control" name="terminalDeviceId"
                                    value = "${terminalDevice.id}"id="terminalDeviceId" placeholder="${terminalDevice.id}">
                                <input type="hidden" class="form-control" name="username"
                                    value = "${terminalDevice.phoneNumber.number}"id="phoneNumber" placeholder="${terminalDevice.phoneNumber.number}">
                                <label for="freeOptionsField">Add option</label>
                                <select class="form-control" id="freeOptionsField"
                                    name="optionId">
                                    <c:forEach items="${freeOptions}" var="option">
                                        <option value="${option.id}"> <c:out value="${option.name} Price: ${option.price} Connection cost ${option.connectionCost}"/> </option>
                                    </c:forEach>
                                </select>
                             </div>
                            <button type="submit" name="confirm" class="btn btn-primary">Confirm</button>
                            <button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>
                        </form>
                    </div>

    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <form name="remove-option" action="option/remove" method="post">
                            <div class="form-group">
                                <input type="hidden" class="form-control" name="terminalDeviceId"
                                            value = "${terminalDevice.id}"id="terminalDeviceId" placeholder="${terminalDevice.id}">
                                <label for="optionsField">Delete option</label>
                                <select class="form-control" id="optionsField"
                                    name="optionId">
                                    <c:forEach items="${options}" var="option">
                                        <option value="${option.id}"> <c:out value="${option.name} Price: ${option.price}"/> </option>
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
                </div>
            </div>
        </div>
    </div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
</body>