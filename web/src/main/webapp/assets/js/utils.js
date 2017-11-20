$(document).ready(function () {
    $('#productAdd').click(function () {
        addProduct();
    });
    $('#productUpdate').click(function () {
        updateProduct();
    });
    $('#productDelete').click(function () {
        deleteProduct();
    });

    $('#userAdd').click(function () {
        addUser();
    });
    $('#userUpdate').click(function () {
        updateUser();
    });
    $('#userDelete').click(function () {
        deleteUser();
    });
});

function addProduct() {
    // alert($("#name").val() + '/'  + $("#details").val() + '/'  + $("#price").val());
    var name = $("#name").val();
    var details = $("#details").val();
    var price = $("#price").val();
    var url = postUrl + "/add_product";
    // alert(name + '/'  + details + '/'  + price  + '/'  + url);

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    if (name && details && price) {
        var product = {
            name: name,
            details: details,
            price: price
        };
        // alert(token + "/" + header);
        $.ajax({
            headers: "Accept: application/json",
            type: "POST",
            url: url,
            // url: '${pageContext.request.contextPath}/editproducts/add_product'
            // url: ${pageContext.request.contextPath} + '/editproducts/add_product'
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            // data: product,
            // data: {product:product},
            // always: function() {
            //     alert(name + '/'  + details + '/'  + price  + '/'  + url);
            // var el = '<div id="' + product.id + '">'+ product.id+'    '+product.name +'    '+product.details+'    '+product.price+'</div>';
            // $(".productTable").append("<div>FUCK!!!</div>");
            // $(".productTable").append(el);

            // success: function() {
            //     var el = '<div id="' + product.id + '">'+ product.id+'    '+product.name +'    '+product.details+'    '+product.price+'</div>';
            //     $(".productTable").append(el);
            // },
            // error: function(data){
            //     if ( console && console.log ) {
            //         console.log( "Error data:", data);
            //     }
            // }

            data: product
        }).done(function (data) {
            var el = '<div id="' + data.id + '">' + data.id + '    ' + data.name + '    ' + data.details + '    ' + data.price + '</div>';
            $(".productTable").append(el);
        }).fail(function (data) {
            if (console && console.log) {
                console.log("Error data:", data);
            }
        });
    }
}

function deleteProduct() {
    var id = $("#id").val();
    var url = postUrl + "/delete_product/" + id;

    $.ajax({
        headers: "Accept: application/json",
        type: "GET",
        url: url
    }).done(function (data) {
        $("#" + id).remove();
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function updateProduct() {
    var id = $("#id").val();
    var name = $("#name").val();
    var details = $("#details").val();
    var price = $("#price").val();
    var url = postUrl + "/update_product";

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    if (id && name && details && price) {
        var product = {
            id: id,
            name: name,
            details: details,
            price: price
        };
        $.ajax({
            headers: "Accept: application/json",
            type: "POST",
            url: url,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: product
        }).done(function (data) {
            var el = '<div id="' + data.id + '">' + data.id + '    ' + data.name + '    ' + data.details + '    ' + data.price + '</div>';
            $(".productTable").append(el);
        }).fail(function (data) {
            if (console && console.log) {
                console.log("Error data:", data);
            }
        });
    }
}

function addUser() {
    var name = $("#name").val();
    var surname = $("#surname").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var url = postUrl + "/add_user";

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    if (name && surname && phone && address && email && password) {
        var user = {
            name: name,
            surname: surname,
            phone: phone,
            address: address,
            email: email,
            password: password
        };
        $.ajax({
            headers: "Accept: application/json",
            type: "POST",
            url: url,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: user
        }).done(function (data) {
            var el = '<div id="' + data.id + '">' + data.id + '    ' + data.name + '    ' + data.surname + '    ' + data.phone + '    ' + data.address + '    ' + data.email + '    ' + data.password + '</div>';
            $(".userTable").append(el);
        }).fail(function (data) {
            if (console && console.log) {
                console.log("Error data:", data);
            }
        });
    }
}

function deleteUser() {
    var id = $("#id").val();
    var url = postUrl + "/delete_user/" + id;

    $.ajax({
        headers: "Accept: application/json",
        type: "GET",
        url: url
    }).done(function (data) {
        $("#" + id).remove();
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function updateUser() {
    var id = $("#id").val();
    var name = $("#name").val();
    var surname = $("#surname").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var type = $("#type").val();
    var url = postUrl + "/update_user";

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    if (id && name && surname && phone && address && email && password) {
        var user = {
            id: id,
            name: name,
            surname: surname,
            phone: phone,
            address: address,
            email: email,
            password: password,
            type: type
        };
        $.ajax({
            headers: "Accept: application/json",
            type: "POST",
            url: url,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: user
        }).done(function (data) {
            var el = '<div id="' + data.id + '">' + data.id + '    ' + data.name + '    ' + data.surname + '    ' + data.phone + '    ' + data.address + '    ' + data.email + '    ' + data.password + '</div>';
            $(".userTable").append(el);
        }).fail(function (data) {
            if (console && console.log) {
                console.log("Error data:", data);
            }
        });
    }
}