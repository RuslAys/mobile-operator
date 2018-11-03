<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    ${user}
    <a href="${terminalDevice.phoneNumber.number}/tariff">Change tariff</a>
    ${terminalDevice.phoneNumber.number}
    ${terminalDevice.options}
    ${terminalDevice.tariffPlan}
</body>
</html>