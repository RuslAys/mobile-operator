<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
	<h4>Login Form</h4>

	<form action="/loginAction" method="post">
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
        <td><button type="submit">Login</button></td>
      </tr>
    </table>
  </form>
  <br/>
  ${SPRING_SECURITY_LAST_EXCEPTION.message}
</body>
</html>