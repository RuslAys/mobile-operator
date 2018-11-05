<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Tariff plan management</h1>
    ${tariffPlan}
    ${options}
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
            <button type="submit" class="btn btn-primary">Change</button>
        </div>
    </form>
</body>