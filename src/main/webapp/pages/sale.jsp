<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Sale contract</h1>
    <h2>Add new customer</h2>
    <div>
        <div>
            <form name="add-new-customer" action="sale/confirm" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="firstName"
                        id="firstNameField" placeholder="First Name"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="lastName"
                        id="lastNameField" placeholder="Last Name"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="birthDate"
                        id="birthDateField" placeholder="Birth date"
                        value=
                        "<fmt:formatDate value="${cForm.birthDate}" pattern="dd-MM-yyyy" />"/>
                    <script>
                        $('#birthDateField').datepicker({
                            format: 'dd-mm-yyyy',
                            startDate: '-3d',
                            uiLibrary: 'bootstrap4'
                        });
                    </script>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="city"
                        id="cityField" placeholder="City"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="street"
                        id="streetField" placeholder="Street"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="house"
                        id="houseField" placeholder="House"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="email"
                        id="emailField" placeholder="Email"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="passport"
                        id="passportField" placeholder="Passport"/>
                </div>
                <div class="form-group">
                    <label for="tariffField">Choose tariff</label>
                    <select class="form-control" id="tariffField"
                        name="tariff">
                        <c:forEach items="${tariffs}" var="tariff">
                            <c:if test="${tariff.archival == false}" >
                                <option value="${tariff.id}"> <c:out value="${tariff}"/> </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="numberField">Choose number</label>
                    <select class="form-control" id="numberField"
                        name="number">
                        <c:forEach items="${numbers}" var="phoneNumber">
                            <option value="${phoneNumber.id}"> <c:out value="${phoneNumber}"/> </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" name="confirm" class="btn btn-primary">Confirm</button>
                <button type="submit" name="add_to_cart" class="btn btn-primary">Add to cart</button>
            </form>
        </div>
    </div>
    <jsp:include page="parts/footer.jsp" />
</body>

</html>