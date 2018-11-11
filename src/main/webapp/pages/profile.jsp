<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <div>
        <div class="container py-4">
            <h3>Number: ${terminalDevice.phoneNumber.number}</h3>
            <h3>Money: ${terminalDevice.personalAccount.money}</h3>
            <div class="row">
                <div class="col">
                    <ul class="list-group">
                        <li class="list-group-item">
                            Name: ${terminalDevice.personalAccount.contract.customer.firstName}
                        </li>
                        <li class="list-group-item">
                            Last Name: ${terminalDevice.personalAccount.contract.customer.lastName}
                        </li>
                        <li class="list-group-item">
                            Birth date: <fmt:formatDate value="${terminalDevice.personalAccount.contract.customer.birthDate}"
                            pattern="dd.MM.yyyy"/>
                        </li>
                        <li class="list-group-item">
                            Passport: ${terminalDevice.personalAccount.contract.customer.passport}
                        </li>
                        <li class="list-group-item">
                            City: ${terminalDevice.personalAccount.contract.customer.address.city}
                        </li>
                        <li class="list-group-item">
                            Street: ${terminalDevice.personalAccount.contract.customer.address.street}
                        </li>
                        <li class="list-group-item">
                            House: ${terminalDevice.personalAccount.contract.customer.address.houseNumber}
                        </li>
                        <li class="list-group-item">
                            Email: ${terminalDevice.personalAccount.contract.customer.email}
                        </li>
                    </ul>
                </div>
                <div class="col">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="${terminalDevice.phoneNumber.number}/tariff">Tariff management</a>
                        </li>
                        <li class="list-group-item">
                            <a href="${terminalDevice.phoneNumber.number}/lock">Lock management</a>
                        </li>
                        <li class="list-group-item">
                            <a href="${terminalDevice.phoneNumber.number}/option">Options management</a>
                        </li>
                    </ul>
                </div>
                <security:authorize access="hasRole('ROLE_OPERATOR')">
                    <div class = "col">
                        <ul class="list-group">
                            <c:forEach items="${anotherTerminalDevices}" var="td" varStatus="loop">
                                <li class="list-group-item">
                                    <a class="nav-link" href="/profile/${td.phoneNumber.number}">${td.phoneNumber.number}</a>
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
                            <c:forEach items="${terminalDevice.options}" var="option" varStatus="loop">
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
                            ${terminalDevice.tariffPlan.name}
                        </li>
                    </ul>
                </div>
                <div class="col">
                    <ul class="list-group">
                        <h3>Locks:</h3>
                        <li class="list-group-item">
                            <c:forEach items="${terminalDeviceLocks.terminalDeviceLocks}" var="tdl" varStatus="loop">
                                ${tdl.lock.name}
                            </c:forEach>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>