<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body id="page-top">
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin">Admin</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/admin/option">Option editor</a>
    </li>
    <li class="breadcrumb-item active">${option.name}</li>
</ol>
${error}
${option.inclusiveOptions}
${option.exclusiveOptions}
${option.parentInclusive}
</body>
