<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Admin Panel</h1>
    <h2>Add new operator</h2>
    <form name="add_new_operator" action="admin/add" method="post">
        <table>
              <tr>
                <td>Username</td>
                <td><input type="text" name="username"></td>
              </tr>
              <tr>
                <td>Password</td>
                <td><input type="password" name="password"></td>
              </tr>
              <tr>
                 <td>Confirm password</td>
                 <td><input type="password" name="confirmPassword"></td>
                 </tr>
              <tr>
                <td><button type="submit">Add Operator</button></td>
              </tr>
            </table>
    </form>
    <div> ${message} </div>
</body>
</html>