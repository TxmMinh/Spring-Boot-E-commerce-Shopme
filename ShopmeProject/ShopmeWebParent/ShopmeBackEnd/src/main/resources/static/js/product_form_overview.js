dropdownBrands = $("#brand");
dropdownCategories = $("#category");

$(document).ready(function () {
    $("#shortDescription").richText();
    $("#fullDescription").richText();

    dropdownBrands.change(function () {
        dropdownCategories.empty();
        getCategories();
    });
    getCategories();
});

function getCategories() {
    // alert(dropdownBrands.val());
    brandId = dropdownBrands.val();
    url = brandModuleURL + "/" + brandId + "/categories";

    $.get(url, function (responseJson) {
        // alert(responseJson);
        $.each(responseJson, function (index, category) {
            // alert(category.name);
            $("<option>").val(category.id).text(category.name).appendTo(dropdownCategories);
        });
    });
}

function checkUnique(form) {
    productId = $("#id").val();
    productName = $("#name").val();
    csrfValue = $("input[name='_csrf']").val();

    params = {id: productId, name: productName, _csrf: csrfValue};

    $.post(checkUniqueUrl, params, function(response) {
        if (response == "OK") {
            form.submit();
        } else if (response == "Duplicate") {
            showWarningModal("There is another product having same name " + productName);
        } else {
            showErrorModal("Unknown response from server");
        }
    }).fail(function() {
        showErrorModal("Could not connect to the server");
    });

    return false;
}