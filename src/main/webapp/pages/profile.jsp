<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <div>
        <h3>Customer:</h3>
        <h5> Name: ${terminalDevice.personalAccount.contract.customer.firstName} </h5>
        <h5> Last Name: ${terminalDevice.personalAccount.contract.customer.lastName} </h5>
        <h5> Birth date: ${terminalDevice.personalAccount.contract.customer.birthDate} </h5>
    </div>
    <ul>
        <li>
            <a href="${terminalDevice.phoneNumber.number}/tariff">Tariff management</a>
        </li>
        <li>
            <a href="${terminalDevice.phoneNumber.number}/lock">Lock management</a>
        </li>
        <li>
            <a href="${terminalDevice.phoneNumber.number}/option">Options management</a>
        </li>
    </ul>

    <div>
        <h3>Number:</h3> ${terminalDevice.phoneNumber.number}
    </div>

    <div>
        <h3>Money:</h3> ${terminalDevice.personalAccount.money}
    </div>

    <div>
        <h3>Current options:</h3>
        <c:forEach items="${terminalDevice.options}" var="option" varStatus="loop">
            <h5>${option.name}</h5>
        </c:forEach>
    </div>

    <div>
        <h3>Tariff plan:</h3>
        ${terminalDevice.tariffPlan.name}
        <h4>Tariff price:</h4>
        ${terminalDevice.tariffPlan.price}
    </div>

    <div>
        <h3>Locks:</h3>
        <c:forEach items="${terminalDeviceLocks.terminalDeviceLocks}" var="tdl" varStatus="loop">
                    <h5>${tdl.lock.name}</h5>
        </c:forEach>
    </div>
    <jsp:include page="parts/footer.jsp" />
</body>
</html>