<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Option management</h1>
    ${tariffPlan}
    <form name="add-option" action="option/add" method="post">
        <div class="form-group">
            <input type="hidden" class="form-control" name="terminalDeviceId"
                        value = "${terminalDevice.id}"id="terminalDeviceId" placeholder="${terminalDevice.id}">
            <label for="freeOptionsField">Add option</label>
            <select class="form-control" id="freeOptionsField"
                name="optionId">
                <c:forEach items="${freeOptions}" var="option">
                    <option value="${option.id}"> <c:out value="${option.name} Price: ${option.price} Connection cost ${option.connectionCost}"/> </option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary">Add</button>
        </div>
    </form>

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
            <button type="submit" class="btn btn-primary">Remove</button>
        </div>
    </form>
</body>