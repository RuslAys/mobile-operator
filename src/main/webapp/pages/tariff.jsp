<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Tariff editor</h1>
    <h2>Add new tariff</h2>
    <div>
        <div>
            <form name="add-new-tariff" action="tariff/add" method="post">
                <div class="form-group">
                    <label for="optionNameField">Tariff plan name</label>
                    <input type="text" class="form-control" name="name"
                        id="optionNameField" placeholder="Tariff`s name">
                </div>
                <div class="form-group">
                    <label for="optionsField">Add options</label>
                    <select multiple class="form-control" id="optionsField"
                        name="options">
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
                    <th>Tariffs</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tariffPlans}" var="tariffPlan">
                        <tr>
                            <td><a href="/tariff/${tariffPlan.name}">
                                <c:out value="${tariffPlan.name}" /> </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>