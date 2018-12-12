<%@include file ="parts/common.jsp"%>
<jsp:include page="parts/header.jsp" />
<body id="page-top">
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="${rootUrl}/">Profile</a>
    </li>
    <li class="breadcrumb-item">
        <a href="${rootUrl}/profile/${contract.phoneNumber.number}">${contract.phoneNumber.number}</a>
    </li>
    <li class="breadcrumb-item active">Options management</li>
</ol>

<div id="content-wrapper">
    <div class="container-fluid">
        <div class="container target">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Options on contract</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="optionsOnContractTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${options}" var="option">
                                <tr>
                                    <td>${option.name}</td>
                                    <td>${option.price}</td>
                                    <td>
                                        <form name="add-option" action="${rootUrl}/profile/${contract.phoneNumber.number}/option/remove" method="post">
                                                <input type="hidden" class="form-control" name="contractId"
                                                       value = "${contract.id}"id="contractId" placeholder="${contract.id}">
                                                <input type="hidden" class="form-control" name="optionId"
                                                       value = "${option.id}"id="optionId" placeholder="${option.id}">
                                            <button type="button" class="btn btn-primary" onclick="showOptionsToDelete(${option.id}, '${option.name}')">Remove</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-table"></i>
                    Available options</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="availableOptions" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${freeOptions}" var="option">
                                <tr>
                                    <td>${option.name}</td>
                                    <td>${option.price}</td>
                                    <td>
                                        <form name="add-option" action="${rootUrl}/profile/${contract.phoneNumber.number}/option/add" method="post">
                                            <input type="hidden" class="form-control" name="contractId"
                                                   value = "${contract.id}"id="freeOptionsContractId" placeholder="${contract.id}">
                                            <input type="hidden" class="form-control" name="optionId"
                                                   value = "${option.id}"id="freeOptionsOptionId" placeholder="${option.id}">
                                            <button type="button" class="btn btn-primary" onclick="showOptionsToAdd(${option.id}, '${option.name}')">Add</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Modal to add -->
<div class="modal fade" id="modal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Actions</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body" id="modalBody">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    function createFormToAdd(id, body) {
        var url = '${rootUrl}/profile/${contract.phoneNumber.number}/option/add';
        createForm(url, id, body);
    }

    function createFormToRemove(id, body){
        var url = '${rootUrl}/profile/${contract.phoneNumber.number}/option/remove';
        createForm(url, id, body);
    }

    function createForm(url, id, body) {
        var form = document.createElement('form');
        form.setAttribute('action', url);
        form.setAttribute('method', 'post');

        console.log(form);

        var contractInput = document.createElement('input');
        contractInput.setAttribute('type', 'hidden');
        contractInput.setAttribute('name', 'contractId');
        contractInput.setAttribute('value', '${contract.id}');
        contractInput.setAttribute('placeholder', '${contract.id}');

        var optionInput = document.createElement('input');
        optionInput.setAttribute('type', 'hidden');
        optionInput.setAttribute('name', 'optionId');
        optionInput.setAttribute('value', id);
        optionInput.setAttribute('placeholder', id);

        var bConfirm = document.createElement('button');
        bConfirm.setAttribute('type', 'submit');
        bConfirm.setAttribute('content', 'test content');
        bConfirm.setAttribute('class', 'btn btn-primary mr-3 mt-1');
        bConfirm.setAttribute('name', 'confirm');
        bConfirm.innerHTML = 'Confirm';

        var bAddToCart = document.createElement('button');
        bAddToCart.setAttribute('content', 'test content');
        bAddToCart.setAttribute('type', 'submit');
        bAddToCart.setAttribute('class', 'btn btn-primary mt-1');
        bAddToCart.setAttribute('name', 'add_to_cart');
        bAddToCart.innerHTML = 'Add to cart';

        form.appendChild(bConfirm);
        form.appendChild(bAddToCart);
        form.appendChild(contractInput);
        form.appendChild(optionInput);

        var wrapper = document.getElementById(body);
        wrapper.appendChild(form);
    }
</script>

<script>
    function fillListToDelete(body, data) {
        console.log('call fillListToDelete');
        var list = document.createElement('ul');
        list.setAttribute('class', 'list-group');

        var header = document.createElement('li');
        header.setAttribute('class', 'list-group-item text-muted');
        header.setAttribute('contenteditable', 'false');
        header.innerHTML="Following options will be deleted";

        list.appendChild(header);

        for (var i = 0; i < data.length; i++) {
            var option = data[i];
            var element = document.createElement('li');
            element.setAttribute('class', 'list-group-item');
            element.innerHTML=option.name;
            list.appendChild(element);
        }

        var wrapper = document.getElementById(body);
        wrapper.appendChild(list);
    }

    function fillListToAdd(body, data) {
        var list = document.createElement('ul');
        list.setAttribute('class', 'list-group');

        var header = document.createElement('li');
        header.setAttribute('class', 'list-group-item text-muted');
        header.setAttribute('contenteditable', 'false');
        header.innerHTML="Following options will be added";

        list.appendChild(header);

        for (var i = 0; i < data.length; i++) {
            var option = data[i];
            var element = document.createElement('li');
            element.setAttribute('class', 'list-group-item');
            element.innerHTML=option.name;
            list.appendChild(element);
        }

        var wrapper = document.getElementById(body);
        wrapper.appendChild(list);
    }

    function createTextWithAdding(body, name) {
        var p = document.createElement('p');
        p.innerHTML = "Following option will be added: " + name;

        var wrapper = document.getElementById(body);
        wrapper.appendChild(p);
    }

    function createTextWithRemoving(body, name) {
        var p = document.createElement('p');
        p.innerHTML = "Following option will be deleted: " + name;

        var wrapper = document.getElementById(body);
        wrapper.appendChild(p);
    }
</script>

<script>
    $(document).ready(function() {
        $('#optionsOnContractTable').DataTable();
        $('#availableOptions').DataTable();
    });

    var modalId = "#modal";
    var bodyName = 'modalBody';

    function showOptionsToDelete(id, optionName) {
        var url = "${rootUrl}/rest/options/${contract.phoneNumber.number}/contract/inclusive/delete?contractId=" + ${contract.id} + "&optionIds=" + id;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                var json = JSON.parse(data);

                // var bodyName = 'modalBody';
                var body = document.getElementById(bodyName).innerText="";

                createTextWithRemoving(bodyName, optionName);
                if(json != null && json.length != 0){
                    fillListToDelete(bodyName, json);
                    createFormToRemove(id, bodyName);
                }
            }
        });
        $(modalId).modal();
    }

    function showOptionsToAdd(id, optionName) {
        var url = "${rootUrl}/rest/options/${contract.phoneNumber.number}/contract/inclusive/add?contractId=" + ${contract.id} + "&optionIds=" + id;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                var json = JSON.parse(data);
                console.log(json);

                // var bodyName = 'modalBody';
                var body = document.getElementById(bodyName).innerText="";

                createTextWithAdding(bodyName, optionName);
                if(json != null && json.length != 0){
                    fillListToAdd(bodyName, json);
                }
                showExclusiveOptions(id, optionName);
            }
        });
        $(modalId).modal();
    }

    function showExclusiveOptions(id, optionName) {
        var url = "${rootUrl}/rest/options/${contract.phoneNumber.number}/contract/exclusive?contractId=" + ${contract.id} + "&optionIds=" + id;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                var json = JSON.parse(data);

                if(json != null && json.length != 0){
                    fillListToDelete(bodyName, json);
                }
                createFormToAdd(id, bodyName);
            }
        });
    }
</script>