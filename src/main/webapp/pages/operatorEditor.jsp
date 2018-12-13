<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin/operator">Operator editor</a>
    </li>
    <li class="breadcrumb-item active">${user.username}</li>
</ol>
    <div>
       <h5>Username: <c:out value="${user.username}" /></h5>
       <h5>Role:
          <c:forEach items="${user.authorities}" var="auth">
             <c:out value="${auth.authority}" />
          </c:forEach>
       </h5>

        <form name="edit-operator" action="${rootUrl}/admin/operator/editOperator" method="post">
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
</body>
</html>