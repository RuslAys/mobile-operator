<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body>
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item active">Admin</li>
</ol>
    <h1 id = "adminPanelHeader">Admin panel</h1>
    <ul>
        <li>
            <a href="${rootUrl}/admin/operator">Operator editor</a>
        </li>
        <li>
            <a href="${rootUrl}/admin/tariff">Tariff editor</a>
        </li>
        <li>
            <a href="${rootUrl}/admin/option">Option editor</a>
        </li>
        <li>
            <a href="${rootUrl}/admin/phone">Phone number editor</a>
        </li>
    </ul>
</body>