<jsp:include page="parts/header.jsp" />
<body>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/admin">Admin panel</a>
    </security:authorize>
	<h2>${message}</h2>
</body>
</html>