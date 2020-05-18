function initStore() {
    var cityCode = "PAR";
    var adults_num = 2;
    $.ajax({
        // search orange store by zipcode
        // use the API Orange Store Locator
        // see https://developer.orange.com/apis/store/
        // TODO : fix the url to call using zipCode as a param
        url: "https://test.api.amadeus.com/v2/shopping/hotel-offers?" +
        "cityCode=" + cityCode +
        "adults=" + adults_num,
        // Authorization key
        // See https://developer.orange.com/tech_guide/2-legged-oauth/
        // TODO : generate and set the Authorization token
        headers: { "Authorization": "Bearer BlDbmuv6Vu7o36TV4fpQGAxoNKWz"}
    }).then(function(data) {
        // TODO : set html fields for storeName, storeAddress, Zipcode, and city
        console.log(data);
        $('.hotelName').html(data[0].hotel.name);
        $('.hotelAddress').html(data[0].hotel.address);
        $('.check_in').html(data[0].offers.checkInDate);
        $('.check_out').html(data[0].offers.checkOutDate);
        $('.price').html(data[0].offers.price);
        // TODO : call the initMap function with appropriate params
        // initMap(data[0].longitude, data[0].latitude, data[0].name);
    });         
    
}