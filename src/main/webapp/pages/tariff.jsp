<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<c:set var="pageListHolder" value="${tariffs}" scope="session" />
<spring:url value="/admin/tariff" var="pageurl" />
<body>
    <div class="container py-4">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 mx-auto">
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
                                <button type="submit" class="btn btn-primary">Add new tariff plan</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <h3>Tariff plans</h3>
                            <table class="table">
                                  <thead class = "thead-light">
                                    <tr>
                                      <th scope="col">Name</th>
                                      <th scope="col">Price</th>
                                      <th scope="col"></th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                      <c:forEach items="${pageListHolder.pageList}" var="tariffPlan">
                                          <c:if test="${tariffPlan.archival == false}">
                                              <tr>
                                                  <form name="remove-tariff" action="tariff/remove" method="post">
                                                     <input type="hidden" class="form-control" name="tariffId" value="${tariffPlan.id}" id="tariffs">
                                                     <td>
                                                        <a href="tariff/${tariffPlan.id}"><c:out value="${tariffPlan.name}" /> </a>
                                                     </td>
                                                     <td>
                                                        <h2> ${tariffPlan.price} </h2>
                                                      </td>
                                                     <td>
                                                        <button type="submit" class="btn btn-primary">Remove</button>
                                                     </td>
                                                  </form>
                                              </tr>
                                          </c:if>
                                      </c:forEach>
                                  </tbody>
                            </table>
                            </div>
                            <div>
                            <span style="float:left;">
                            <c:choose>
                                <c:when test="${pageListHolder.firstPage}">Prev</c:when>
                                <c:otherwise><a href="${pageurl}/prev">Prev</a></c:otherwise>
                            </c:choose>
                            </span>
                            <span>
                            <c:forEach begin="0" end="${pageListHolder.pageCount-1}" varStatus="loop">
                            &nbsp;&nbsp;
                            <c:choose>
                                <c:when test="${loop.index == pageListHolder.page}">${loop.index+1}</c:when>
                                <c:otherwise><a href="${pageurl}/${loop.index}">${loop.index+1}</a></c:otherwise>
                            </c:choose>
                            &nbsp;&nbsp;
                            </c:forEach>
                            </span>
                            <span>
                            <c:choose>
                                <c:when test="${pageListHolder.lastPage}">Next</c:when>
                                <c:otherwise><a href="${pageurl}/next">Next</a></c:otherwise>
                            </c:choose>
                            </span>
                            </div>
        </div>



<!--
        <h3>Archival tariff plans</h3>
                <table class="table">
                      <thead class = "thead-light">
                        <tr>
                          <th scope="col">Name</th>
                          <th scope="col">Price</th>
                        </tr>
                      </thead>
                      <tbody>
                          <c:forEach items="${tariffPlans}" var="tariffPlan">
                              <c:if test="${tariffPlan.archival == true}">
                                  <tr>
                                     <td>
                                        ${tariffPlan.name}
                                     </td>
                                     <td>
                                        ${tariffPlan.price}
                                      </td>
                                  </tr>
                              </c:if>
                          </c:forEach>
                      </tbody>
                </table>

                -->
    </div>
</body>
</html>