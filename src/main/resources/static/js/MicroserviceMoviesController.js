/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var MicroserviceMoviesController = (function () {

    function clearTables() {
        var content = document.getElementById("content");
        var tables = document.getElementsByClassName("table");
        while (tables.length > 5) {
            content.removeChild(tables[0]);
        }
        var titles = document.getElementsByClassName("table-title");
        while (titles.length > 5) {
            content.removeChild(titles[0]);
        }
    }


    function addMovie(Movie) {
        console.log(Movie)
        
        var tableOrder = document.createElement("table");
        var header = document.createElement("tr");

        var cell = document.createElement("th");
        cell.innerHTML = "Poster";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Plot";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Rated";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Director";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Runtime";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Actors";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Awards";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Country";
        header.appendChild(cell);

        tableOrder.appendChild(header);

        tableOrder.setAttribute("class", "table");
        
        var row = document.createElement("tr");

        var cell = document.createElement("td");
        var image = document.createElement("img");
        
        image.setAttribute("src", Movie.Poster);
        image.setAttribute("width", "60px");
        image.setAttribute("height", "100px");
        cell.appendChild(image);

        row.appendChild(cell);
        
        var cell = document.createElement("td");
        cell.innerHTML = Movie.Plot;
        row.appendChild(cell);

        var cell = document.createElement("td");
        cell.innerHTML = Movie.Rated;
        row.appendChild(cell);

        var cell = document.createElement("td");
        cell.innerHTML = Movie.Director;
        row.appendChild(cell);

        var cell = document.createElement("td");
        cell.innerHTML = Movie.Runtime;
        row.appendChild(cell);

        var cell = document.createElement("td");
        cell.innerHTML = Movie.Actors;
        row.appendChild(cell);

        var cell = document.createElement("td");
        cell.innerHTML = Movie.Awards;
        row.appendChild(cell);

        var cell = document.createElement("td");
        cell.innerHTML = Movie.Country;
        row.appendChild(cell);

        tableOrder.appendChild(row);
            
        var firstTable = document.getElementById("HTMLtable");

        document.getElementById("tablahere").appendChild(tableOrder);
        
    }

    function getSelected(id) {
        var selectedTable = document.getElementById(id);
        var tableLabel = selectedTable.options[selectedTable.selectedIndex].value;
        return tableLabel;
    }


    function loadMovie() {
        clearTables();
        loadData(addMovie);
    }

    function loadData(callback) {
        axios.all([filterSelected()])
                .then(axios.spread(function (movie) {
                    callback(movie);
                }));
    }

    function filterSelected() {
        var name = document.getElementById("name").value;
        var year = document.getElementById("year").value;
        
        return MicroserviceMoviesRESTController.getMovie(name, year);
    }


    return {
        loadMovie: loadMovie
    };
})();