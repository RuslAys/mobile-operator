<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Lock editor</h1>
    <h2>Add new lock</h2>
    <div>
        <div>
            <form name="add-new-lock" action="/admin/lock/add" method="post">
                <input type="text" class="form-control" name="name"
                   id="lockField" placeholder="Lock name">
               <div class="form-check">
                 <input class="form-check-input" name="deletedByUser" type="checkbox" id="defaultCheck1"
                 unchecked/>
                 <label class="form-check-label" for="defaultCheck1">
                   Can be deleted by user
                 </label>
               </div>
               <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Lock</th>
                    <th>Can Be Deleted By User</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${locks}" var="lock">
                        <tr>
                            <td><a href="/lock/${lock.id}">
                                <c:out value="${lock.name}" /> </a>
                            </td>
                            <td> <c:out value="${lock.canBeDeletedByUser}" /> </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>