<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>User editor</h1>
    <div>
       <h5>Username: <c:out value="${user.username}" /></h5>
       <h5>Role:
          <c:forEach items="${user.authorities}" var="auth">
             <c:out value="${auth.authority}" />
          </c:forEach>
       </h5>

        <form name="edit-operator" action="/admin/editOperator" method="post">
            <input type="hidden" class="form-control" name="username"
                value = "${user.username}"id="usernameField" placeholder="${user.username}">
           <div class="form-check">
             <input class="form-check-input" name="enabled" type="checkbox" id="defaultCheck1"
             <c:if test="${user.enabled==true}">checked="checked"</c:if>
             <c:if test="${user.enabled==false}">unchecked="checked"</c:if>
             />
             <label class="form-check-label" for="defaultCheck1">
               Active
             </label>
           </div>
           <button type="submit" class="btn btn-primary">Edit</button>
        </form>

    </div>
    <div>
        <!--
        <form name="edit-operator" action="admin/editOperator" method="post">
            //TODO
            Сделать возможность редактировать пароль пользователю самому
            ???Нужно ли редактирование имени???
            <div class="form-group">
                <label for="usernameField">Username</label>
                <input type="text" class="form-control" name="username"
                    id="usernameField" placeholder="${user.username}">
            </div>
            <div class="form-group">
                <label for="passwordField">Password</label>
                <input type="password" class="form-control" name="password"
                    id="passwordField" placeholder="Password">
            </div><div class="form-group">
                <label for="passwordField">Confirm password</label>
                <input type="password" class="form-control" name="confirmPassword"
                    id="passwordField" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary">Edit</button>
            -->
        </form>
    </div>
</body>
</html>