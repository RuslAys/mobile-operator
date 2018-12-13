<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
<%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/css/gijgo.min.css" rel="stylesheet" type="text/css" />
<!-- -->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item active">Sale</li>
</ol>

    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
                        <div>
                            <form id="search-form" action="${rootUrl}/sale/search" method="post">
                                <input type="text" class="form-control form-control-lg rounded-1" name="number"
                                        id="phoneNumberField" placeholder="Search by phone number" required>
                                <button type="submit" class="btn btn-primary btn-md float-right mt-1 mb-1">Search</button>
                            </form>
                        </div>
                        <c:if test="${message != null}" >
                            <div>
                                ${message}
                            </div>
                        </c:if>
                        <c:if test="${contract != null}" >
                            <div>
                                <form name="add-new-customer" action="${rootUrl}/sale/confirm/customer" method="post">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="firstName"
                                            id="firstNameField" value="${contract.customer.firstName}"
                                            readonly/>
                                        <div class="invalid-feedback">Oops, you missed this one.</div>
                                    </div>

                                    <div class="form-group">
                                        <input type="text" class="form-control" name="lastName"
                                            id="lastNameField" value="${contract.customer.lastName}"
                                            readonly/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="birthDate"
                                            id="birthDateField" value="<fmt:formatDate value="${contract.customer.birthDate}"
                                                                                      pattern="dd.MM.yyyy"/>"
                                            readonly/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="city"
                                            id="cityField" value="${contract.customer.address.city}"
                                            readonly/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="street"
                                            id="streetField" value="${contract.customer.address.street}"
                                            readonly/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="houseNumber"
                                            id="houseField" value="${contract.customer.address.houseNumber}"
                                            readonly/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="email"
                                            id="emailField" value="${contract.customer.email}"
                                            readonly/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="passport"
                                            id="passportField" value="${contract.customer.passport}"
                                            readonly/>
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" name="customerId"
                                            id="customerIdField" value="${contract.customer.id}"
                                            readonly/>
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
                                                <option value="${phoneNumber.id}"> <c:out value="${phoneNumber.number}"/> </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-md float-right">Confirm</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${contract == null}" >
            <div>
                <form name="add-new-customer" action="sale/confirm" method="post" commandName="customerDto">
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName" path="fristName"
                            id="firstNameField" placeholder="First Name" required/>
                        <div class="invalid-feedback">Oops, you missed this one.</div>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName" path="lastName"
                            id="lastNameField" placeholder="Last Name" required/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="birthDate" path="birthDate"
                           id="birthDateField" placeholder="Birth date"
                           value="<fmt:formatDate value="${cForm.birthDate}" pattern="dd-MM-yyyy" />" required/>
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
                        <input type="text" class="form-control" name="houseNumber"
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
                                <option value="${phoneNumber.id}"> <c:out value="${phoneNumber.number}"/> </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-md float-right">Confirm</button>
                </form>
            </div>
        </c:if>
    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>