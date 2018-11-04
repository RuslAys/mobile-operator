<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    ${user}
    <a href="${terminalDevice.phoneNumber.number}/tariff">Tariff management</a>
    <a href="${terminalDevice.phoneNumber.number}/lock">Lock management</a>
    <a href="${terminalDevice.phoneNumber.number}/options">Options management</a>
    ${terminalDevice.phoneNumber.number}
    ${terminalDevice.personalAccount}
    ${terminalDevice.options}
    ${terminalDevice.tariffPlan}
    ${terminalDevice.locks}
</body>
</html>