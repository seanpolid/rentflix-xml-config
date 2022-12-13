"use strict";

const returnMovies = {
    
    submitButton : document.querySelector("input[type*=submit]"),
    checkboxes : document.querySelectorAll(".checkbox"),

    init : function() {
        if (this.submitButton) {
            this.submitButton.addEventListener("click", this.returnMovies, false);
        }
    },

    returnMovies : async function() {
        let moviesToReturn = [];
        for (const checkbox of returnMovies.checkboxes) {
            if (checkbox.checked) {
                const movieId = checkbox.getAttribute("data-movieId");
                const invoiceMovieId = checkbox.getAttribute("data-invoiceMovieId");
                console.log(movieId, invoiceMovieId);
                moviesToReturn.push({"movieId": movieId, "invoiceMovieId": invoiceMovieId});
            }
        }

        fetch("./api/return", {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(moviesToReturn),
        })
        .then(() => {
			for (const checkbox of returnMovies.checkboxes) {
				if (checkbox.checked) {
					checkbox.parentElement.parentElement.remove();
				}
			}
		})
        .catch(error => (console.log("Error", error)));
    }


}

returnMovies.init();