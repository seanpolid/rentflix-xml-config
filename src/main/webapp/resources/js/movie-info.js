"use strict";

const movieInfo = {
	
	main : document.querySelector("main"),
    movieList : document.querySelector(".movieContainer"),

    init : function() {
        this.movieList.addEventListener("click", this.showMovieInfo, false);
        this.movieList.addEventListener("click", event => {event.preventDefault()});
    },

    showMovieInfo : function(event) {
        if (document.querySelector(".movieInfo")) {
            document.querySelector(".movieInfo").remove();
        }
        const movieName = event.target.getAttribute("data-name");
        movieInfo.getMovieInfo(movieName)
        .then((info) => {
			movieInfo.getInvoiceMovies(info["id"])
			.then((invoiceMovies) => {
				let remainingCopies = info["totalCopies"];
	            for (const invoice of invoiceMovies) {
	                if (invoice["returnDate"] == null) {
	                    remainingCopies -= 1;
	                }
	            }
				return [info, remainingCopies];
			})
			.then((array) => {
				const info = array[0];
				const numRemainingCopies = array[1];
	            let container = movieInfo.createContainer(info, movieName, numRemainingCopies);
	            container.classList.add("movieInfo");
	            movieInfo.main.append(container);
			})
		});
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

    getInvoiceMovies : async function(movieId) {
        const response = await fetch("./api/invoicemovies/" + movieId);
        return await response.json();
    },

    getMovieInfo : async function(movieName) {
        const response = await fetch("./api/movies/" +  movieName);
        return await response.json();
    },

    createContainer : function(result, movieName, numRemainingCopies) {
        const containerData = movieInfo.createContainerData(movieName, result, numRemainingCopies);
        
        let container = document.createElement("ul");
        for (const data of containerData) {
            const listItem = movieInfo.createListItem(data);

            const explanation = data["explanation"].toLowerCase();
            if (explanation.split("copies").length - 1 > 0 && !movieInfo.isAdmin()) {
                continue;
            }
            container.appendChild(listItem);
        }
        
        let closeButton = movieInfo.createCloseButton();
        container.appendChild(closeButton);

        let cartButton = movieInfo.createCartButton(result["id"], numRemainingCopies);
        if (cartButton) {
            container.append(cartButton);
        }
        return container;
    },

    createContainerData : function(movieName, result, numRemainingCopies) {
        const containerData = [
            {"explanation": "Name: ", "data": movieName},
            {"explanation": "Year made: ", "data": result["yearMade"]},
            {"explanation": "Release date: ", "data": result["releaseDate"]},
            {"explanation": "Genre: ", "data": result["genre"]["name"]},
            {"explanation": "Rating: ", "data": result["rating"]["name"]},
            {"explanation": "Length (minutes): ", "data": result["length"]},
            {"explanation": "# Remaining Copies: ", "data": numRemainingCopies},
            {"explanation": "Cost: $", "data": result["cost"]},
            {"explanation": "Description:\n", "data": result["description"]}
        ];
        return containerData;
    },

    createListItem : function(data) {
        let listItem = document.createElement("li");
        listItem.appendChild(document.createTextNode(data["explanation"] + data["data"]));
        return listItem;
    },

    createCloseButton : function() {
        let closeButton = document.createElement("button");
        closeButton.appendChild(document.createTextNode("X"));
        closeButton.addEventListener("click", () => {
            document.querySelector(".movieInfo").remove();
        }, false);
        return closeButton;
    },

    createCartButton : function(movieId, numRemainingCopies) {
        let cartButton;
        if (movieInfo.isCustomer() && !movieInfo.isAdmin()) {
            cartButton = document.createElement("button");
            cartButton.classList.add("cartButton");

            if (numRemainingCopies > 0) {
                cartButton.appendChild(document.createTextNode("Add to cart"));
                cartButton.addEventListener("click", movieInfo.addToCart, false);
                cartButton.setAttribute("data-movieId", movieId);

            }  else {
                cartButton.appendChild(document.createTextNode("Sold out"));
                cartButton.setAttribute("disabled", "true");
            }
        }
        return cartButton;
    },
 
    addToCart : async function(event) {
        const movieId = event.target.getAttribute("data-movieId");
        fetch(`./api/cart/${movieId}`, {method:'POST'})
            .then(() => {
                // Add functionality to update checkout nav styling
            })
            .catch((error) => {console.log("Error:", error)});
    }
};

movieInfo.init();