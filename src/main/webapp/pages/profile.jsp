<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <div>
        <div class="container py-4">
            <h3>Number: ${contract.phoneNumber.number}</h3>
            <h3>Balance: ${contract.balance}</h3>
            <div class="row">
                <div class="col">
                    <ul class="list-group">
                        <li class="list-group-item">
                            Name: ${contract.customer.firstName}
                        </li>
                        <li class="list-group-item">
                            Last Name: ${contract.customer.lastName}
                        </li>
                        <li class="list-group-item">
                            Birth date: <fmt:formatDate value="${terminalDevice.personalAccount.contract.customer.birthDate}"
                            pattern="dd.MM.yyyy"/>
                        </li>
                        <li class="list-group-item">
                            Passport: ${contract.customer.passport}
                        </li>
                        <li class="list-group-item">
                            City: ${contract.customer.address.city}
                        </li>
                        <li class="list-group-item">
                            Street: ${contract.customer.address.street}
                        </li>
                        <li class="list-group-item">
                            House: ${contract.customer.address.houseNumber}
                        </li>
                        <li class="list-group-item">
                            Email: ${contract.customer.email}
                        </li>
                    </ul>
                </div>
                <div class="col">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="${contract.phoneNumber.number}/tariff">Tariff management</a>
                        </li>
                        <li class="list-group-item">
                            <a href="${contract.phoneNumber.number}/lock">Lock management</a>
                        </li>
                        <li class="list-group-item">
                            <a href="${contract.phoneNumber.number}/option">Options management</a>
                        </li>
                    </ul>
                </div>
                <security:authorize access="hasRole('ROLE_OPERATOR')">
                    <div class = "col">
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
            <div class="row">
                <div class="col">
                    <ul class="list-group">
                        <h3>Current options:</h3>
                            <c:forEach items="${contract.options}" var="option" varStatus="loop">
                                <li class="list-group-item">
                                    ${option.name}
                                </li>
                            </c:forEach>
                    </ul>
                </div>
                <div class="col">
                    <ul class="list-group">
                        <h3>Tariff plan:</h3>
                        <li class="list-group-item">
                            ${contract.tariffPlan.name}
                        </li>
                    </ul>
                </div>
                <div class="col">
                    <h3>Lock: ${contract.locked}</h3>
                    <%--<li class="list-group-item">--%>
                    <%--<c:forEach items="${terminalDeviceLocks.terminalDeviceLocks}" var="tdl" varStatus="loop">--%>
                    <%--${tdl.lock.name}--%>
                    <%--</c:forEach>--%>
                    <%--</li>--%>
                    <form class="form" role="form" id="formLock" action="${rootUrl}/profile/${contract.phoneNumber.number}/lock" method="post">
                        <div class="form-group">
                            <input hidden class="form-control form-control-lg rounded-1" name="contractId" value="${contract.id}">
                        </div>
                        <button type="submit" class="btn btn-success btn-lg float-right" id="btnLogin">Lock / Unlock</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>