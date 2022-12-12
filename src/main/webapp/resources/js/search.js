"use strict";

const search = {

    main : document.querySelector("main"),
    searchField : document.querySelector(".search input[type*=search]"),
    submit : document.querySelector(".search input[type*=submit]"),
    movieList : document.querySelector(".movieContainer"),

    init : function() {
        this.submit.addEventListener("click", this.filterMovies, false);
    },

    filterMovies : function() {
        const searchValue = search.searchField.value.toLowerCase();
        if (searchValue !== "") {
            search.getRelevantMovies(searchValue).then(ratings => {
                let relevantMovies = [];
                for (let rating of ratings) {
                    relevantMovies.push(rating["name"]);
                }

                let movieNames = document.querySelectorAll(".movieName");
                for (let movieName of movieNames) {
                    if (relevantMovies.find(relevantMovie => relevantMovie === movieName.textContent) !== undefined) {
                        search.movieList.insertBefore(movieName.parentNode, search.movieList.children[0]);
                    } else {
                        movieName.parentElement.classList.add("hide");
                    }
                }
            });
        } else {
            for (let movie of search.movieList.children) {
                if (movie.classList.contains("hide")) {
                    movie.classList.remove("hide");
                }
            }
        }
        
    },

    getRelevantMovies : async function(searchValue) {
        let ratings = [];
        const response = await fetch("./api/movies");
        const movies = await response.json();
        for (const movie of movies) {
            let movieName = movie["name"];
            let description = movie["description"];
            const movieNameMatch = movieName.toLowerCase().split(searchValue).length - 1;
            const descriptionMatch = description.toLowerCase().split(searchValue).length - 1;
            const searchRating = movieNameMatch + descriptionMatch;

            if (searchRating > 0) {
                let result = {
					"name" : movieName,
					"searchRating" : searchRating
				};
				ratings.push(result);
			}
        }
        return ratings;
    },
}

search.init();