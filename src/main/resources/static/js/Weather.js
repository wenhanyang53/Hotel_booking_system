$(function(){
    var apiKey = '00e429d3c3c1cc6ca9bdd735993eb90b';
    //var baseUrl = 'https://api.openweathermap.org/data/2.5/weather?';
    var baseUrl = 'https://api.openweathermap.org/data/2.5/weather?q=Toulouse&appid=00e429d3c3c1cc6ca9bdd735993eb90b&units=metric';
    // console.log(baseUrl);
    // $("#submit button").click(function (e) {
    //      e.preventDefault();
    // })
    // $('submit button').click(function(e){
    //    e.printDefault();
        //var cityValue = $('#city').val();
        var params = {
            url :baseUrl,
            //url :baseUrl + '&q=' + cityValue + '&appid=00e429d3c3c1cc6ca9bdd735993eb90b&units=metric',
            method: 'GET'
        };
        $.ajax(params).done(function(response){
            console.log(response);
            $('.card-title').text(response.name);

            var temp = Math.round(response.main.temp);
            var tempMax = Math.round(response.main.temp_max);
            var tempMin = Math.round(response.main.temp_min);
            $('.temp-weather').text(temp);
            $('.temp-max-weather').text(tempMax);
            $('.temp-min-weather').text(tempMin);
            //console.log('Success')
        })
            .fail(function(){
               console.error('Erreur');
            });
    // })
});
