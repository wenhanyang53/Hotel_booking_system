$(function () {
    console.log("response");
    $.ajax({
        url: "https://test.api.amadeus.com/v1/security/oauth2/token",
        // beforeSend: function(xhr) {
        //     xhr.setRequestHeader("Content-Type", "application/json");
        //     xhr.setRequestHeader("Accept", "application/json");
        // },
        // dataType: "json",
        data: {
            client_id: "ALaBO5MxAAcPmus2jbZqaDSB3Q85GEAx",
            client_secret: "LjeNBhLC0wbRTx2L",
            grant_type: "client_credentials"
        },
        type: "POST",
        success: function (response) {
            console.log(response);
            token = response.access_token;
            expiresIn = response.expires_in;
            $.ajax({
                type: "GET", //GET, POST, PUT
                // xhrFields: { withCredentials: true},
                url: 'https://test.api.amadeus.com/v2/shopping/hotel-offers?cityCode=NYC&adults=1&radius=5&radiusUnit=KM&paymentPolicy=NONE&includeClosed=false&bestRateOnly=true&view=FULL&sort=PRICE',  //the url to call
                // headers: { Authorization: $`Bearer ${localStorage.getItem("py1QecQgn8rjOrnpZSTKy6II2zfi")}`},
                headers: {'Authorization': 'Bearer ' + token,
                    // 'X-PINGOTHER': 'pingpong',
                    // 'Access-Control-Allow-Credentials' : 'true',
                    // "Access-Control-Expose-Headers" : "X-Total-Pages",
                    // 'Access-Control-Request-Method' : 'GET',
                    // "Access-Control-Request-Headers": "origin, x-requested-with",
                    // "Access-Control-Allow-Origin" : "http://localhost:8080/hotel",
                }
                // beforeSend: function (xhr) {
                //     xhr.setRequestHeader("Authorization", "Bearer" + token);
                //     xhr.setRequestHeader('X-PINGOTHER', 'pingpong');
                //     xhr.setRequestHeader('Content-Type', 'application/xml');}
            }).done(function (response) {
                console.log(response);
                //Response ok. Work with the data returned
                $.each(response.data,function(index,value){
                    var hotelName = value.hotel.name;
                    var hotelAddress = value.hotel.address.lines + " " + value.hotel.address.cityName + " " +
                        value.hotel.address.postalCode;
                    var price = value.offers[0].price.total + " " + value.offers[0].price.currency;
                    $('.row').append('<div class="col-md-4">\n' +
                        '\t\t\t\t\t<div class="hotel-content">\n' +
                        '\t\t\t\t\t\t<div class="hotel-grid" style="background-image: url(images/image-1.jpg);">\n' +
                        '\t\t\t\t\t\t\t<small>For as low as</small><div class="price">' + price + '</div>\n' +
                        '\t\t\t\t\t\t\t<a class="book-now text-center" th:href="@{/booking}"><i class="ti-calendar"></i> Book Now</a>\n' +
                        '\t\t\t\t\t\t</div>\n' +
                        '\t\t\t\t\t\t<div class="desc">\n' +
                        '\t\t\t\t\t\t\t<p> Hotel Name : <span class="hotelName">' + hotelName + '</span> </p>\n' +
                        '\t\t\t\t\t\t\t<p> Hotel address : <span class="hotelAddress">' + hotelAddress + '</span> </p>\n' +
                        '\t\t\t\t\t\t</div>\n' +
                        '\t\t\t\t\t</div>\n' +
                        '\t\t\t\t</div>');
                })
            });
        },
        error: function (errorThrown) {
            console.log("error");
        }
    });

})
