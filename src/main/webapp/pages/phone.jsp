<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Phone number editor</h1>
    <h2>Add new phone number</h2>
    <div>
        <div>
            <form name="add-new-phone" action="phone/add" method="post">
                <div class="form-group">
                    <label for="numberField">Phone</label>
                    <input type="text" class="form-control" name="number"
                        id="numberField" placeholder="Number">
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Phones</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${numbers}" var="phoneNumber">
                        <tr>
                            <td><a href="/phone/${phoneNumber.number}">
                                <c:out value="${phoneNumber.number}" /> </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>