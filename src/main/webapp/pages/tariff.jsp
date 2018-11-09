<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Tariff editor</h1>
    <h2>Add new tariff</h2>
    <div>
        <div>
            <form name="add-new-tariff" action="tariff/add" method="post">
                <div class="form-group">
                    <label for="tariffNameField">Tariff plan name</label>
                    <input type="text" class="form-control" name="name"
                        id="tariffNameField" placeholder="Tariff name">
                </div>
                 <div class="form-group">
                    <label for="tariffPriceField">Tariff plan name</label>
                    <input type="text" class="form-control" name="price"
                        id="tariffPriceField" placeholder="Tariff price">
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

                </tbody>
            </table>
        </div>

        <c:forEach items="${tariffPlans}" var="tariffPlan">
            <c:if test="${tariffPlan.archival == false}">
                <form name="remove-tariff" action="tariff/remove" method="post">
                   <label for="tariffs">Tariff</label>
                   <input type="hidden" class="form-control" name="tariffId" value="${tariffPlan.id}" id="tariffs">
                   <a href="tariff/${tariffPlan.id}"><c:out value="${tariffPlan}" /> </a>
                   <button type="submit" class="btn btn-primary">Remove</button>
                </form>
            </c:if>
        </c:forEach>

        <h1> Archival tariff plans </h1>
        <c:forEach items="${tariffPlans}" var="tariffPlan">
            <c:if test="${tariffPlan.archival == true}">
                ${tariffPlan}
            </c:if>
        </c:forEach>
    </div>
</body>
</html>