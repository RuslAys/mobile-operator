<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Cart</h1>
    <div class="container py-2">
        <div class = "row">
            <div class = "col">
                <ul class="list-group">
                    <c:forEach items="${cart.cartItems}" var="cartItem">
                            <li class="list-group-item">
                                ${cartItem.operationType.toString()}
                                <form name="confirm" action="cart/remove" method="post">
                                <input type="hidden" class="form-control" name="itemId"
                                    id="itemField" value="${cartItem.id}" readonly/>
                                <button type="submit" class="btn btn-primary">Remove</button>
                                </form>
                            </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class = "row">
            <div class = "col">
                <form name="confirm" action="cart/confirm" method="post">
                   <button type="submit" class="btn btn-primary">Confirm all</button>
                </form>
            </div>
        </div>
    </div>
</body>