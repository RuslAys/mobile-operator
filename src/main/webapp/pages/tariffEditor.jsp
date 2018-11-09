<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Tariff editor</h1>
    <div>
       <div>
           <form name="remove-option" action="${tariff.id}/remove" method="post">
               <div class="form-group">
                   <label for="tariffOptionsField">Tariff options</label>
                   <select class="form-control" id="tariffOptionsField"
                       name="optionId">
                       <c:forEach items="${tariff.options}" var="option">
                           <option value="${option.id}"> <c:out value="${option}"/> </option>
                       </c:forEach>
                   </select>
               </div>
               <button type="submit" class="btn btn-primary">Remove</button>
           </form>
       </div>
    </div>
    <div>
       <form name="add-new-option" action="${tariff.id}/add" method="post">
           <div class="form-group">
               <label for="freeOptionsField">Add options</label>
               <select class="form-control" id="freeOptionsField"
                   name="optionId">
                   <c:forEach items="${freeOptions}" var="option">
                       <option value="${option.id}"> <c:out value="${option}"/> </option>
                   </c:forEach>
               </select>
           </div>
           <button type="submit" class="btn btn-primary">Add</button>
       </form>
   </div>
</div>
</body>
</html>