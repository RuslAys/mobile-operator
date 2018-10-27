<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Option editor</h1>
    <h2>Add new option</h2>
    <div>
        <div>
            <form name="add-new-option" action="option/add" method="post">
                <div class="form-group">
                    <label for="optionNameField">Username</label>
                    <input type="text" class="form-control" name="name"
                        id="optionNameField" placeholder="Option`s name">
                </div>
                <div class="form-group">
                    <label for="inclusiveOptionsField">Inclusive options</label>
                    <select multiple class="form-control" id="inclusiveOptionsField"
                        name="inclusiveOptions">
                        <c:forEach items="${options}" var="option">
                            <option value="${option.id}"> <c:out value="${option}"/> </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="exclusiveOptionsField">Exclusive options</label>
                    <select multiple class="form-control" id="exclusiveOptionsField"
                          name="exclusiveOptions">
                        <c:forEach items="${options}" var="option">
                            <option value="${option.id}"> <c:out value="${option}"/> </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${options}" var="option">
                        <tr>
                            <td><a href="/option/${option.name}">
                                <c:out value="${option.name}" /> </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>