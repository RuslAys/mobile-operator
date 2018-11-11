<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
<nav class="nav">
  <a class="nav-link active" href="/profile/${terminalDevice.phoneNumber.number}">Back to user ${terminalDevice.phoneNumber.number}</a>
</nav>
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
</body>