<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<c:set var="pageListHolder" value="${numbers}" scope="session" />
<spring:url value="${rootUrl}/admin/phone" var="pageurl" />
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item active">Phone number editor</li>
</ol>
    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div>
                            <form name="add-new-phone" action="${rootUrl}/admin/phone/add" method="post">
                                <div class="form-group">
                                    <label for="numberField">Phone</label>
                                    <input type="text" class="form-control" name="number"
                                        id="numberField" placeholder="Number">
                                </div>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
        <table class="table">
             <thead class = "thead-light">
               <tr>
                 <th scope="col">Phones</th>
               </tr>
             </thead>
            <tbody>
            <c:forEach var="phoneNumber" items="${phones}">
                <tr>
                    <td>${phoneNumber.number}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            </div>
        </div>
    </div>

</body>
</html>