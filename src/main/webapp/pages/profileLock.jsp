<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Lock management</h1>
    ${terminalDevice.phoneNumber.number}
    <c:forEach items="${terminalDevice.locks}" var="lock" varStatus="loop">
        <form name="add-lock-${loop.count}" action="lock/remove" method="post">
            <input class="form-control" type=text value="${lock.name}"readonly placeholder="${lock.name}">
            <security:authorize access="hasRole('ROLE_USER')">
                <c:if test="${lock.canBeDeletedByUser == true}">
                    <button type="button" class="btn btn-primary">Remove</button>
                </c:if>
                <c:if test="${lock.canBeDeletedByUser == false}">
                    <button type="button" class="btn btn-primary" disabled>Can be removed only by operator</button>
                </c:if>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_OPERATOR')">
                <button type="submit" class="btn btn-primary">Remove</button>
            </security:authorize>

        </form>
    </c:forEach>

    <form name="add-lock" action="lock/add" method="post">
        <div class="form-group">
            <input type="hidden" class="form-control" name="terminalDeviceId"
                        value = "${terminalDevice.id}"id="terminalDeviceId" placeholder="${terminalDevice.id}">
            <label for="freeLocksField">Add locks</label>
            <select multiple class="form-control" id="freeLocksField"
                name="freeLocks">
                <c:forEach items="${freeLocks}" var="lock">
                    <option value="${lock.id}"> <c:out value="${lock.name} Can be deleted by user ${lock.canBeDeletedByUser}"/> </option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary">Add</button>
        </div>
    </form>
</body>