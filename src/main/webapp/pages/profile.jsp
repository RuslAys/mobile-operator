<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    ${user}
    ${terminalDevice.phoneNumber.number}
    ${terminalDevice.options}
    ${terminalDevice.tariffPlan}
</body>
</html>