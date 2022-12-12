"use strict";

const browse = {
	
	main : document.querySelector("main"),
    searchField : document.querySelector(".search input[type*=search]"),
    submit : document.querySelector(".search input[type*=submit]"),
    movieList : document.querySelector(".browse ul"),

    init : function() {
        this.submit.addEventListener("click", this.filterMovies, false);
        this.movieList.addEventListener("click", this.showMovieInfo, false);
        this.movieList.addEventListener("click", event => {event.preventDefault()});
    },

    filterMovies : function() {
        const searchValue = browse.searchField.value.toLowerCase();
        if (searchValue !== "") {
            browse.getRelevantMovies(searchValue).then(ratings => {
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

    showMovieInfo : function(event) {
        if (document.querySelector(".movieInfo")) {
            document.querySelector(".movieInfo").remove();
        }
        const movieName = event.target.getAttribute("data-name");
        browse.getMovieInfo(movieName).then(result => {
            const isAdmin = browse.isAdmin()
            const isCustomer = browse.isCustomer();
            const numRemainingCopies = browse.getNumRemainingCopies(result);
            let container = browse.createContainer(result, movieName, isAdmin, isCustomer, numRemainingCopies);
            container.classList.add("movieInfo");
            browse.main.append(container);
        })
    },

    isAdmin : function() {
        if (document.querySelector(".admin")) {
            return true;
        } else {
            return false;
        }
    },

    isCustomer : function() {
        if (document.querySelector(".customer")) {
            return true;
        } else {
            return false;
        }
    },

    getNumRemainingCopies : function(result) {
        const movieId = result["id"];
        let remainingCopies = result["totalCopies"];
        if (remainingCopies > 0) {
            browse.getInvoiceMovies(movieId)
            .then(result => {
                for (const invoice of result) {
                    if (invoice["returnDate"] != null) {
                        remainingCopies -= 1;
                    }
                }
            });
        }
        return remainingCopies;
    },

    getInvoiceMovies : async function(movieId) {
        const response = await fetch("./api/invoicemovies/" + movieId);
        return await response.json();
    },

    getMovieInfo : async function(movieName) {
        const response = await fetch("./api/movies/" +  movieName);
        return await response.json();
    },

    createContainer : function(result, movieName, isAdmin, isCustomer, numRemainingCopies) {
        console.log(numRemainingCopies);
        const remainingCopies = document.createTextNode("# Remaining Copies: " + numRemainingCopies);
        const description = document.createTextNode("Description:\n" + result["description"]);
        const yearMade = document.createTextNode("Year made: " + result["yearMade"]);
        const releaseDate = document.createTextNode("Release date: " + result["releaseDate"]);
        const cost = document.createTextNode("Cost: $" + result["cost"]);
        const length = document.createTextNode("Length (minutes): " + result["length"]);
        const rating = document.createTextNode("Rating: " + result["rating"]["name"]);
        const genre = document.createTextNode("Genre: " + result["genre"]["name"]);

        let container = document.createElement("ul");
        
        let movieNameContainer = document.createElement("li");
        let remainingCopiesContainer = document.createElement("li");
        let descriptionContainer = document.createElement("li");
        let yearMadeContainer = document.createElement("li");
        let releaseDateContainer = document.createElement("li");
        let costContainer = document.createElement("li");
        let lengthContainer = document.createElement("li");
        let ratingContainer = document.createElement("li");
        let genreContainer = document.createElement("li"); 
        let closeButton = document.createElement("button");

        let cartButton;
        if (isCustomer && !isAdmin) {
            cartButton = document.createElement("button");
            cartButton.classList.add("cartButton");

            if (numRemainingCopies > 0) {
                cartButton.appendChild(document.createTextNode("Add to cart"));
                cartButton.addEventListener("click", browse.addToCart, false);
                cartButton.setAttribute("data-movieId", `${result["id"]}`);

            }  else {
                cartButton.appendChild(document.createTextNode("Sold out"));
                cartButton.setAttribute("disabled", "true");
            }
        }

        movieNameContainer.appendChild(document.createTextNode(movieName));
        remainingCopiesContainer.appendChild(remainingCopies);
        descriptionContainer.appendChild(description);
        yearMadeContainer.appendChild(yearMade);
        releaseDateContainer.appendChild(releaseDate);
        costContainer.appendChild(cost);
        lengthContainer.appendChild(length);
        ratingContainer.appendChild(rating);
        genreContainer.appendChild(genre);
        closeButton.appendChild(document.createTextNode("X"));
        closeButton.addEventListener("click", () => {
            document.querySelector(".movieInfo").remove();
        }, false);

        container.append(closeButton, movieNameContainer, yearMadeContainer, releaseDateContainer, genreContainer, 
            ratingContainer, lengthContainer, costContainer, descriptionContainer);
        if (cartButton) {
            container.append(cartButton);
        }
        if (isAdmin) {
            container.insertBefore(remainingCopiesContainer, costContainer);
        }
        return container;
    },

    addToCart : async function(event) {
        console.log("adding to cart");
        console.log(event.target);
        const movieId = event.target.getAttribute("data-movieId");
        fetch(`./api/cart/${movieId}`, {method:'POST'})
            .then(() => {
                // Add functionality to update checkout nav styling
            })
            .catch((error) => {console.log("Error:", error)});
    }
};

browse.init();