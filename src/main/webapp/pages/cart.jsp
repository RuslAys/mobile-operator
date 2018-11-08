<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
    <h1>Cart</h1>
    <ul>
        <c:forEach items="${cart.cartItems}" var="cartItem">
            <li>
                ${cartItem.operationType}
            </li>
        </c:forEach>
        <form name="confirm" action="cart/confirm" method="post">
           <button type="submit" class="btn btn-primary">Confirm all</button>
        </form>
    </ul>
</body>