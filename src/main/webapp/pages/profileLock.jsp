<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
<nav class="nav">
  <a class="nav-link active" href="/profile/${terminalDevice.phoneNumber.number}">Back to user ${terminalDevice.phoneNumber.number}</a>
</nav>
    <c:forEach items="${terminalDevice.terminalDeviceLocks}" var="tdlock" varStatus="loop">
        <div class="container py-4">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-5 mx-auto">
                            <blockquote class="blockquote text-center">
                                <p class="mb-0"> Remove locks</p>
                            </blockquote>
                            <form name="remove-lock-${loop.count}" action="lock/remove" method="post">
                                <div>
                                    <input type="hidden" class="form-control" name="terminalDeviceId"
                                        value = "${terminalDevice.id}"id="terminalDeviceId" placeholder="${terminalDevice.id}">
                                    <input class="form-control-plaintext" type="hidden" name="lockId"
                                        value="${tdlock.lock.id}" readonly > ${tdlock.lock.name} </input>
                                </div>
                                <security:authorize access="hasRole('ROLE_USER')">
                                    <c:if test="${tdlock.canBeDeletedByUser == true}">
                                        <button type="submit" name="confirm" class="btn btn-primary">Confirm</button>
                                        <button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>
                                    </c:if>
                                    <c:if test="${tdlock.canBeDeletedByUser == false}">
                                        <button type="submit" class="btn btn-primary" disabled>Can be removed only by operator</button>
                                    </c:if>
                                </security:authorize>
                                <security:authorize access="hasRole('ROLE_OPERATOR')">
                                    <button type="submit" name="confirm" class="btn btn-primary">Confirm</button>
                                    <button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>
                                </security:authorize>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="container py-4">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-5 mx-auto">
                                <form name="add-lock" action="lock/add" method="post">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" name="terminalDeviceId"
                                                    value = "${terminalDevice.id}"id="terminalDeviceId" placeholder="${terminalDevice.id}">
                                        <label for="freeLocksField">Add locks</label>
                                        <select class="form-control" id="freeLocksField"
                                                 name="lockId">
                                            <c:forEach items="${freeLocks}" var="lock">
                                                <option value="${lock.id}"> <c:out value="${lock.name}"/> </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" name="confirm" class="btn btn-primary">Confirm</button>
                                    <button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>

                                </form>
                            <div>
                        <div>
                    <div>
                <div>
</body>