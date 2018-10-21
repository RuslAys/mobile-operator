<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MSS</title>
</head>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/admin">Admin panel</a>
    </security:authorize>
	<h2>${message}</h2>
	<form action="/logout" method="post">
		<input value="Logout" type="submit">
	</form>
</body>
</html>