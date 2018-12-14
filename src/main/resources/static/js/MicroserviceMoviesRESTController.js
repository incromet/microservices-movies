/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var MicroserviceMoviesRESTController = (function () {
    var url = '';

    function getMovie(movie, year) {
        return axios.get(url + "/movies/" + movie + "/" + year).then(function (response) {
            return response.data;
        })
    }

    return {
        getMovie: getMovie
    };
})();