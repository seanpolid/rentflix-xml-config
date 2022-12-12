"use strict";

const browse = {
	
	main : document.querySelector("main"),
    searchField : document.querySelector(".search input[type*=search]"),
    submit : document.querySelector(".search input[type*=submit]"),
    movieList : document.querySelector(".browse ul"),

    init : function() {
        this.submit.addEventListener("click", this.getRelevantMovies, false);
    },

    getRelevantMovies : function() {
        const searchValue = browse.searchField.value.toLowerCase();
        if (searchValue !== "") {
            browse.searchMovies(searchValue).then(ratings => {
                let relevantMovies = [];
                for (let rating of ratings) {
                    relevantMovies.push(rating["name"]);
                }

                let movieNames = document.querySelectorAll(".movieName");
                for (let movieName of movieNames) {
                    if (relevantMovies.find(relevantMovie => relevantMovie === movieName.textContent) !== undefined) {
                        browse.movieList.insertBefore(movieName.parentNode, browse.movieList.children[0]);
                    } else {
                        movieName.parentElement.classList.add("hide");
                    }
                }
            });
        } else {
            for (let movie of browse.movieList.children) {
                if (movie.classList.contains("hide")) {
                    movie.classList.remove("hide");
                }
            }
        }
        
    },

    searchMovies : async function(searchValue) {
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
    }
};

browse.init();