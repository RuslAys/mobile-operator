<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% String curUrl=request.getServletPath().toString(); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MSS</title>
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
                <td><button type="submit">Login</button></td>
              </tr>
            </table>
    </form>
    <div> ${message} </div>
</body>
</head>
</html>